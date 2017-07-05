package com.framgia.fdms.screen.marker;

/**
 * Listens to user actions from the UI ({@link MarkerFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
public class MarkerPresenter implements MarkerContract.Presenter {
    private static final String TAG = MarkerPresenter.class.getName();

    private final MarkerContract.ViewModel mViewModel;

    public MarkerPresenter(MarkerContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
