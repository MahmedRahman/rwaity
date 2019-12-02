package com.atp.rewayti;

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

import com.atp.rewayti.API.ApiManager;
import com.atp.rewayti.ui.base.BaseFragment;
import com.atp.rewayti.ui.nav.aboutUs.AboutUsFragment;
import com.atp.rewayti.ui.nav.auth.forgetpassword.ForgetPasswordFragment;
import com.atp.rewayti.ui.nav.auth.logIn.LogInFragment;
import com.atp.rewayti.ui.nav.auth.regiser.RegisterFragment;
import com.atp.rewayti.ui.nav.chat.ChatFagment;
import com.atp.rewayti.ui.nav.home.HomeFragment;
import com.atp.rewayti.ui.nav.settings.SettingsFragment;
import com.atp.rewayti.util.Constants;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Main2Activity";
    private AppBarLayout appBarLayout;

    FragmentManager fragmentManager ;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        appBarLayout = findViewById(R.id.app_bar);
        BaseFragment.setContext(this);
       initFragmenManager();




        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());

        test();
    }

    private void test() {
        Call<ResponseBody> call =  ApiManager.getAPIs().getCategories(1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.message());
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: "+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    public void changeAppBarSize(int i) {
        ViewGroup.LayoutParams params = appBarLayout.getLayoutParams();
        params.height = i;
        ((ViewGroup) appBarLayout).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        appBarLayout.setLayoutParams(params);
    }

    private void initFragmenManager() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment1 = fragmentManager.getFragments().get(fragmentManager.getFragments().size()-1);
                if (fragment1.getArguments() != null && fragment1.getArguments().getString(Constants.ARG_TITLE) != null ){
                    setToolBarTitle(fragment1.getArguments().getString(Constants.ARG_TITLE));
                    return;
                }
                String title = "";
                if (fragment1 instanceof HomeFragment){
                    title = "الرئيسية";
                }else if (fragment1 instanceof SettingsFragment){
                    title = "الاعدادات";
                }else if (fragment1 instanceof ForgetPasswordFragment){
                    title = "استرجاع الحساب";
                }else if (fragment1 instanceof LogInFragment){
                    title = "تسجيل الدخول";
                }else if (fragment1 instanceof RegisterFragment){
                    title = "تسجيل حساب جديد";
                }else if (fragment1 instanceof AboutUsFragment){
                    title = "معلومات عنا";
                }else if (fragment1 instanceof ChatFagment){
                    title = "الاقتراحات والشكاوي";
                }
                setToolBarTitle(title);
            }

        });
    }

    private void setToolBarTitle(String title) {
        TextView textView = findViewById(R.id.title_texview);
        textView.setText(title);
    }


    public void loadFragment(Fragment fragment ){
        if (fragment.isAdded()){
            fragmentManager.beginTransaction().remove(fragment).commitNow();
            loadFragment(fragment);
            return;
        }


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations( R.anim.pull_in_right , R.anim.push_out_left
                , R.anim.pull_in_left , R.anim.push_out_right );
        fragmentTransaction.replace(R.id.nav_host_fragment , fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_home) {
            for (int i=0 ; i<fragmentManager.getBackStackEntryCount()-1 ; i++){
                fragmentManager.popBackStack();
            }

        } else if (id == R.id.nav_home) {
            loadFragment(new HomeFragment());
        } else if (id == R.id.settings) {
            loadFragment(new SettingsFragment());
        } else if (id == R.id.about_us) {
            loadFragment(new AboutUsFragment());
        } else if (id == R.id.chat) {
            loadFragment(new ChatFagment());
        } else if (id == R.id.log) {
            loadFragment(new LogInFragment());
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() >= 2){
                super.onBackPressed();
            }else {
                askForExsit();
            }
        }
    }

    private void askForExsit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("خروج ؟");
        builder.setMessage("هل تريد الخروج من التطبيق ؟");
        // add the buttons
        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Main2Activity.this.finish();
            }
        });
        builder.setNegativeButton("لا", null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void openNavigationView(View view) {
        drawer.openDrawer(GravityCompat.START);
    }
}
