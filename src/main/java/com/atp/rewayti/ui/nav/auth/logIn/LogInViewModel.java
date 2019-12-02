package com.atp.rewayti.ui.nav.auth.logIn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogInViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LogInViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}