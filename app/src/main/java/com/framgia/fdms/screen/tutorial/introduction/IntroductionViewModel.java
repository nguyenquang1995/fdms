package com.framgia.fdms.screen.tutorial.introduction;

/**
 * Exposes the data to be used in the Introduction screen.
 */

public class IntroductionViewModel implements IntroductionContract.ViewModel {

    private IntroductionContract.Presenter mPresenter;

    public IntroductionViewModel() {
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(IntroductionContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
