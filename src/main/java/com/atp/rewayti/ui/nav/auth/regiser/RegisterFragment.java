package com.atp.rewayti.ui.nav.auth.regiser;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atp.rewayti.R;
import com.atp.rewayti.ui.base.BaseFragment;
import com.atp.rewayti.util.Constants;

public class RegisterFragment extends BaseFragment {

    private RegisterViewModel mViewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        changeAppBarSize(Constants.APP_BAR_SIZE_HIDE);
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        ButterKnife.bind(this , view);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }

}
