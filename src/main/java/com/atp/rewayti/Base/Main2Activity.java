package com.atp.rewayti.Base;

import android.animation.LayoutTransition;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.atp.rewayti.R;
import com.atp.rewayti.ui.home.BookAdapter;
import com.atp.rewayti.ui.home.HomeFragment;
import com.atp.rewayti.util.Constants;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";
    private AppBarLayout appBarLayout;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        appBarLayout = findViewById(R.id.app_bar);
        BaseFragment.setContext(this);
        initFragmenManager();


        loadFragment(new HomeFragment());

    }


    public void changeAppBarSize(int i) {
        ViewGroup.LayoutParams params = appBarLayout.getLayoutParams();
        params.height = i;
        ((ViewGroup) appBarLayout).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        appBarLayout.setLayoutParams(params);
    }

    private void initFragmenManager() {
        fragmentManager = getSupportFragmentManager();


        fragmentManager.addOnBackStackChangedListener(() -> {

            Fragment fragment1 = fragmentManager.getFragments().get(fragmentManager.getFragments().size() - 1);
            if (fragment1.getArguments() != null && fragment1.getArguments().getString(Constants.ARG_TITLE) != null) {
                setToolBarTitle(fragment1.getArguments().getString(Constants.ARG_TITLE));
                return;
            }

            if (fragmentManager.getBackStackEntryCount() == 1) {
                setToolBarTitle(getString(R.string.app_name));
            }
        });


    }

    private void setToolBarTitle(String title) {
        TextView textView = findViewById(R.id.title_texview);
        textView.setText(title);
    }


    public void loadFragment(Fragment fragment) {
        if (fragment.isAdded()) {
            fragmentManager.beginTransaction().remove(fragment).commitNow();
            loadFragment(fragment);
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations( R.anim.pull_in_right , R.anim.push_out_left
//                , R.anim.pull_in_left , R.anim.push_out_right );

        fragmentTransaction.add(R.id.nav_host_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() >= 2) {
            super.onBackPressed();
        } else {
            askForExsit();
        }
    }

    private void askForExsit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("خروج ؟");
        builder.setMessage("هل تريد الخروج من التطبيق ؟");
        // add the buttons
        builder.setPositiveButton("نعم", (dialogInterface, i) -> Main2Activity.this.finish());
        builder.setNegativeButton("لا", null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void click(View view) {
    }
}
