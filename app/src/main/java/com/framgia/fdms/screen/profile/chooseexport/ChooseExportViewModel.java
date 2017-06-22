package com.framgia.fdms.screen.profile.chooseexport;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.fdms.R;
import com.framgia.fdms.data.model.Device;
import com.framgia.fdms.data.model.User;
import com.framgia.fdms.screen.device.listdevice.ListDeviceAdapter;
import com.framgia.fdms.screen.profile.export.ExportDialogFragment;
import com.framgia.fdms.utils.navigator.Navigator;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.framgia.fdms.utils.Constant.TYPE_DIALOG;

/**
 * Created by tuanbg on 6/15/17.
 */

public class ChooseExportViewModel extends BaseObservable
        implements ChooseExportContract.ViewModel {
    private ChooseExportContract.Presenter mPresenter;
    private AppCompatActivity mActivity;
    private ListDeviceAdapter mAdapter;
    private User mUser;
    private int mProgressBarVisible = GONE;
    private Navigator mNavigator;

    public ChooseExportViewModel(AppCompatActivity activity, User user) {
        mActivity = activity;
        mUser = user;
        mNavigator = new Navigator(activity);
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
    public void setPresenter(ChooseExportContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressbar() {
        setProgressBarVisible(VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        setProgressBarVisible(GONE);
    }

    @Override
    public void onError(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void onDeviceLoaded(List<Device> devices) {
        setAdapter(new ListDeviceAdapter(mActivity, devices, null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mActivity.getMenuInflater().inflate(R.menu.menu_export, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mActivity.onBackPressed();
                break;
            case R.id.item_using:
                // TODO: 6/15/17 getList Using
                return true;
            case R.id.item_used:
                // TODO: 6/15/17 getList Used
                return true;
        }
        return false;
    }

    @Bindable
    public ListDeviceAdapter getAdapter() {
        return mAdapter;
    }

    public void initToolbar(Toolbar toolbar) {
        mActivity.setSupportActionBar(toolbar);
        if (mActivity.getSupportActionBar() == null) return;
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void exportData() {
        FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
        ExportDialogFragment newFragment = ExportDialogFragment.newInstance(mUser);
        newFragment.show(ft, TYPE_DIALOG);
    }

    @Bindable
    public int getProgressBarVisible() {
        return mProgressBarVisible;
    }

    public void setProgressBarVisible(int progressBarVisible) {
        mProgressBarVisible = progressBarVisible;
        notifyPropertyChanged(BR.progressBarVisible);
    }

    public void setAdapter(ListDeviceAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
