package com.framgia.fdms.screen.vendor;

/**
 * Listens to user actions from the UI ({@link VendorFragment}), retrieves the data and updates
 * the UI as required.
 */
final class VendorPresenter implements VendorContract.Presenter {
    private static final String TAG = VendorPresenter.class.getName();

    private final VendorContract.ViewModel mViewModel;

    public VendorPresenter(VendorContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
