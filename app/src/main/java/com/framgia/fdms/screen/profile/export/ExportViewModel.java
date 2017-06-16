package com.framgia.fdms.screen.profile.export;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import com.framgia.fdms.R;
import java.io.File;

import static com.framgia.fdms.utils.Constant.TYPE_PDF;
import static com.framgia.fdms.utils.permission.PermissionUtil.checkWritePermission;

/**
 * Created by tuanbg on 6/14/17.
 */

public class ExportViewModel implements ExportContract.ViewModel {
    private ExportContract.Presenter mPresenter;
    private DialogFragment mFragment;

    public ExportViewModel(DialogFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ExportContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void exportPdf() {
        if (checkWritePermission((AppCompatActivity) mFragment.getActivity())) {
            mPresenter.exportTask();
            mFragment.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
    }

    @Override
    public void openFilePDF(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return;
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file), TYPE_PDF);
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Intent intent = Intent.createChooser(target, mFragment.getString(R.string.title_open_with));
        mFragment.startActivity(intent);
    }
}
