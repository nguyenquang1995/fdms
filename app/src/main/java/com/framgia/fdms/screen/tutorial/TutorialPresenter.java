package com.framgia.fdms.screen.tutorial;

/**
 * Listens to user actions from the UI ({@link TutorialActivity}), retrieves the data and updates
 * the UI as required.
 */
final class TutorialPresenter implements TutorialContract.Presenter {
    private static final String TAG = TutorialPresenter.class.getName();

    private final TutorialContract.ViewModel mViewModel;

    public TutorialPresenter(TutorialContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
