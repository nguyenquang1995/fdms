#!/usr/bin/env bash

echo "********************"
echo "* install gems     *"
echo "********************"
gem install --no-document checkstyle_filter-git saddler saddler-reporter-github findbugs_translate_checkstyle_format android_lint_translate_checkstyle_format pmd_translate_checkstyle_format
gem install chatwork

if [ $? -ne 0 ]; then
    echo 'Failed to install gems.'
    exit 1
fi

echo "********************"
echo "* exec gradle      *"
echo "********************"
./gradlew :app:check -PdisablePreDex

if [ $? -ne 0 ]; then
    echo 'Failed gradle check task.'
    exit 1
fi

echo "********************"
echo "* save outputs     *"
echo "********************"

LINT_RESULT_DIR="$CIRCLE_ARTIFACTS/lint"

mkdir "$LINT_RESULT_DIR"
cp -v "app/build/reports/checkstyle/checkstyle.xml" "$LINT_RESULT_DIR/"
cp -v "app/build/reports/findbugs/findbugs.xml" "$LINT_RESULT_DIR/"
cp -v "app/build/reports/pmd/pmd.xml" "$LINT_RESULT_DIR/"
cp -v "app/build/reports/pmd/cpd.xml" "$LINT_RESULT_DIR/"
cp -v "app/build/outputs/lint-results.xml" "$LINT_RESULT_DIR/"

if [ -z "${CI_PULL_REQUEST}" ]; then
    # when not pull request
    REPORTER=Saddler::Reporter::Github::CommitReviewComment
else
    REPORTER=Saddler::Reporter::Github::PullRequestReviewComment
fi

