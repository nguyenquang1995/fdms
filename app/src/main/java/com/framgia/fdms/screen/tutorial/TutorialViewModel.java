package com.framgia.fdms.screen.tutorial;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.fdms.BR;
import com.framgia.fdms.screen.ViewPagerScroll;

/**
 * Exposes the data to be used in the Tutorial screen.
 */

public class TutorialViewModel extends BaseObservable
        implements TutorialContract.ViewModel, ViewPagerScroll {

    private TutorialContract.Presenter mPresenter;
    private TutorialPagerAdapter mAdapter;

    public TutorialViewModel() {
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
    public void setPresenter(TutorialContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCurrentPosition(int position) {

    }

    @Bindable
    public TutorialPagerAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(TutorialPagerAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
