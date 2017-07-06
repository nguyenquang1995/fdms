package com.framgia.fdms.screen.producer.marker;

import com.framgia.fdms.BasePresenter;
import com.framgia.fdms.BaseViewModel;
import com.framgia.fdms.data.model.Producer;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MarkerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onLoadMakerFail();
        void onLoadMakerSucessfully(List<Producer> producers);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getMakers(int page, int perPage);
    }
}
