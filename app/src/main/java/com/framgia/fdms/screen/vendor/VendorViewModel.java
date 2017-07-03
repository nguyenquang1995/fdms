package com.framgia.fdms.screen.vendor;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.fdms.data.model.Vendor;

/**
 * Exposes the data to be used in the Vendor screen.
 */

public class VendorViewModel extends BaseObservable implements VendorContract.ViewModel {

    private VendorContract.Presenter mPresenter;
    private ListVendorAdapter mAdapter;

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

    @Bindable
    public ListVendorAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onEditVendorClick(Vendor vendor) {

    }

    @Override
    public void onDeleteVendorClick(Vendor vendor) {

    }
}
