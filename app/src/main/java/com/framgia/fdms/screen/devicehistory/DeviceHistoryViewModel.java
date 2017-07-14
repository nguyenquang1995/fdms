package com.framgia.fdms.screen.devicehistory;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fdms.BR;
import com.framgia.fdms.data.model.DeviceUsingHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Devicehistory screen.
 */
public class DeviceHistoryViewModel extends BaseObservable
    implements DeviceHistoryContract.ViewModel {
    private DeviceHistoryContract.Presenter mPresenter;
    private ListUserAdapter mAdapter;
    private List<DeviceUsingHistory> mDeviceUsingHistories = new ArrayList<>();

    public DeviceHistoryViewModel() {
        mAdapter = new ListUserAdapter(this, mDeviceUsingHistories);
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
    public void setPresenter(DeviceHistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public ListUserAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ListUserAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
