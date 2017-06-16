package com.framgia.fdms.screen.profile.export;

import com.framgia.fdms.BasePresenter;
import com.framgia.fdms.BaseViewModel;

/**
 * Created by tuanbg on 6/14/17.
 */

public interface ExportContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void exportPdf();

        void onDestroy();

        void openFilePDF(String filePath);
    }

    interface Presenter extends BasePresenter {

        void exportTask();

        void onDestroy();
    }
}
