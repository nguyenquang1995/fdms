package com.framgia.fdms.screen.tutorial.introduction;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fdms.R;
import com.framgia.fdms.databinding.FragmentIntroductionBinding;

/**
 * Introduction Screen.
 */
public class IntroductionFragment extends Fragment {

    private IntroductionContract.ViewModel mViewModel;

    public static IntroductionFragment newInstance() {
        return new IntroductionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new IntroductionViewModel();

        IntroductionContract.Presenter presenter = new IntroductionPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentIntroductionBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_introduction, container, false);
        binding.setViewModel((IntroductionViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
