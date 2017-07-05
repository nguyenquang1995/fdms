package com.framgia.fdms.screen.marker;

/**
 * Exposes the data to be used in the MarkerFragment screen.
 */

public class MarkerViewModel implements MarkerContract.ViewModel {

    private MarkerContract.Presenter mPresenter;

    public MarkerViewModel() {
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
    public void setPresenter(MarkerContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
