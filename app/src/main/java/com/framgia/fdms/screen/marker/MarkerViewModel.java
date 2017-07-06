package com.framgia.fdms.screen.marker;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.design.widget.Snackbar;

import com.android.databinding.library.baseAdapters.BR;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.framgia.fdms.R;
import com.framgia.fdms.data.model.Producer;

import java.util.List;

/**
 * Exposes the data to be used in the MarkerFragment screen.
 */
public class MarkerViewModel extends BaseObservable
    implements MarkerContract.ViewModel, FloatingSearchView.OnQueryChangeListener {
    private MarkerContract.Presenter mPresenter;
    private MakerApdater mAdapter;
    private Activity mActivity;

    public MarkerViewModel(Activity activity) {
        mActivity = activity;
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
    public void setPresenter(MarkerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoadMakerFail() {
        Snackbar.make(mActivity.findViewById(android.R.id.content), R.string.error_load_makers,
            Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onLoadMakerSucessfully(List<Producer> makers) {
        mAdapter = new MakerApdater(makers);
        setAdapter(mAdapter);
    }

    @Bindable
    public MakerApdater getAdapter() {
        return mAdapter;
    }

    public void setAdapter(MakerApdater adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onSearchTextChanged(String oldQuery, String newQuery) {
        //// TODO: 06/07/2017 later
    }
}
