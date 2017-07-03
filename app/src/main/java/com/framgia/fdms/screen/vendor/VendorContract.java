package com.framgia.fdms.screen.vendor;

import com.framgia.fdms.BasePresenter;
import com.framgia.fdms.BaseViewModel;
import com.framgia.fdms.data.model.Vendor;

/**
 * This specifies the contract between the view and the presenter.
 */
interface VendorContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onEditVendorClick(Vendor vendor);

        void onDeleteVendorClick(Vendor vendor);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
