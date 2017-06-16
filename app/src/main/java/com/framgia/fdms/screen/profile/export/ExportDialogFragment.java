package com.framgia.fdms.screen.profile.export;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.framgia.fdms.data.model.User;
import com.framgia.fdms.databinding.DialogExportBinding;

import static com.framgia.fdms.utils.Constant.BundleConstant.BUNDLE_USER;

/**
 * Created by tuanbg on 6/14/17.
 */

public class ExportDialogFragment extends DialogFragment {
    private DialogExportBinding mBinding;
    private ExportViewModel mViewModel;
    private User mUser;

    public static ExportDialogFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_USER, user);
        ExportDialogFragment fragment = new ExportDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mBinding = DialogExportBinding.inflate(inflater, container, false);
        getDataFromIntent();
        mViewModel = new ExportViewModel(this);
        ExportContract.Presenter presenter = new ExportPresenter(getActivity(), mUser, mViewModel);
        mViewModel.setPresenter(presenter);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mUser = bundle.getParcelable(BUNDLE_USER);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.onDestroy();
    }
}
