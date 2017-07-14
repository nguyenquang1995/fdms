package com.framgia.fdms.screen.devicehistory;

/**
 * Listens to user actions from the UI ({@link DeviceHistoryFragment}), retrieves the data and updates
 * the UI as required.
 */
final class DeviceHistoryPresenter implements DeviceHistoryContract.Presenter {
    private static final String TAG = DeviceHistoryPresenter.class.getName();
    private final DeviceHistoryContract.ViewModel mViewModel;

    public DeviceHistoryPresenter(DeviceHistoryContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
