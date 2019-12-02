package com.atp.rewayti.ui.nav.auth.logIn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.atp.rewayti.R;
import com.atp.rewayti.ui.base.BaseFragment;
import com.atp.rewayti.ui.nav.auth.forgetpassword.ForgetPasswordFragment;
import com.atp.rewayti.ui.nav.auth.regiser.RegisterFragment;
import com.atp.rewayti.util.Constants;

public class LogInFragment extends BaseFragment {
    private static final String TAG = "LogInFragment";
    private LogInViewModel shareViewModel;
    EditText name , password ;
    LinearLayout back ;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
        root = inflater.inflate(R.layout.fragment_share, container, false);
        ButterKnife.bind(this , root);
        changeAppBarSize(Constants.APP_BAR_SIZE_HIDE);
        initViews();
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    private void initViews() {
        name = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
    }


    @OnClick(R.id.login)
    public void logIn() {
        Log.d(TAG, "logIn: ");
    }
    @OnClick(R.id.forget_password)
    public void gorgetPassword(View view) {
        loadFragment(new ForgetPasswordFragment());
    }
    @OnClick(R.id.regiser)
    public void Register(View view) {
        loadFragment(new RegisterFragment());
    }


    @Override
    public void onDetach() {
        super.onDetach();
        changeAppBarSize(Constants.APP_BAR_SIZE_WRAP_CONENT);
    }

    @Override
    public void onPause() {
        super.onPause();
        changeAppBarSize(Constants.APP_BAR_SIZE_WRAP_CONENT);
    }
}