package com.atp.rewayti.ui.nav.auth.forgetpassword;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atp.rewayti.R;
import com.atp.rewayti.ui.base.BaseFragment;

public class ForgetPasswordFragment extends BaseFragment {

    private ForgetPasswordViewModel mViewModel;

    public static ForgetPasswordFragment newInstance() {
        return new ForgetPasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.forget_password_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ForgetPasswordViewModel.class);
        // TODO: Use the ViewModel
    }

}
