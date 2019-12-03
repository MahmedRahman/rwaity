package com.atp.rewayti.Base;

import android.content.Context;

import com.atp.rewayti.Base.Main2Activity;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    public static Context context;
    static Main2Activity main2Activity;




    public void loadFragment(Fragment fragment){
        main2Activity.loadFragment(fragment);
    }

    public void changeAppBarSize(int appBarSize){
        main2Activity.changeAppBarSize(appBarSize);
    }

    public static void setContext(Main2Activity activity){
        context = activity;
        main2Activity = activity;
    }
}
