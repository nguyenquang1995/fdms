package com.framgia.fdms.screen.vendor;

/**
 * Exposes the data to be used in the Vendor screen.
 */

public class VendorViewModel implements VendorContract.ViewModel {

    private VendorContract.Presenter mPresenter;

    public VendorViewModel() {
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
    public void setPresenter(VendorContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
