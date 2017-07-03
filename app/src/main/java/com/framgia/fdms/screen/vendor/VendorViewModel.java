package com.framgia.fdms.screen.vendor;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.framgia.fdms.FDMSApplication;
import com.framgia.fdms.R;
import com.framgia.fdms.data.model.Vendor;
import com.framgia.fdms.utils.Constant;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Vendor screen.
 */

public class VendorViewModel extends BaseObservable implements VendorContract.ViewModel {

    private VendorContract.Presenter mPresenter;
    private ListVendorAdapter mAdapter;
    private List<Vendor> mVendors = new ArrayList<>();
    private AppCompatActivity mActivity;

    public VendorViewModel(Activity activity) {
        mActivity = (AppCompatActivity) activity;
        mAdapter = new ListVendorAdapter(FDMSApplication.getInstant(), this, mVendors);
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
        VendorDialog vendorDialog = VendorDialog.newInstant(vendor);
        vendorDialog.show(mActivity.getSupportFragmentManager(),
                Constant.BundleConstant.BUNDLE_DEVICE);
    }

    @Override
    public void onDeleteVendorClick(Vendor vendor) {

    }

    @Override
    public void onLoadVendorSuccess(List<Vendor> vendors) {
        mVendors.clear();
        mVendors.addAll(vendors);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadVendorFailed() {
        Toast.makeText(FDMSApplication.getInstant(), R.string.msg_load_data_fails,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditSubmitClick(Vendor vendor) {
        // TODO: next task
    }

    @Override
    public void onEditCancelClick(Vendor vendor) {
        // TODO: next task
    }
}
