package com.framgia.fdms.screen.producer.vendor;

import com.framgia.fdms.BasePresenter;
import com.framgia.fdms.BaseViewModel;
import com.framgia.fdms.data.model.Producer;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface VendorContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onEditVendorClick(Producer vendor);

        void onDeleteVendorClick(Producer vendor);

        void onLoadVendorSuccess(List<Producer> vendors);

        void onLoadVendorFailed();

        void onEditSubmitClick(Producer vendor);

        void onEditCancelClick(Producer vendor);

        void onAddVendorClick();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getVendors();
    }
}
