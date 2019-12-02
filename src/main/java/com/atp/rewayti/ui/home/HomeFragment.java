package com.atp.rewayti.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.atp.rewayti.API.ApiManager;
import com.atp.rewayti.API.model.Deal;
import com.atp.rewayti.API.model.Deals;
import com.atp.rewayti.R;
import com.atp.rewayti.API.Item;
import com.atp.rewayti.ui.base.BaseFragment;
import com.atp.rewayti.ui.bookDeails.BookDetailsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends BaseFragment implements ItemAdapter.OnProductItemClicked {
    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;
    List<Item> itemList;

    ImageView loading ;
    boolean image1;
    private View view;
    private LinearLayout myLayout;
    private ArrayList<View> result;
    int viewPosition;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        homeViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        getDeals( "رعب");
        getDeals( "حب");
//        getDeals( "رعب");


        return view;
    }

    private void getDeals( String tag) {
        Call<Deals> call =  ApiManager.getAPIs().getDeals("getnovelbytag" , tag);

        call.enqueue(new Callback<Deals>() {
            @Override
            public void onResponse(Call<Deals> call, Response<Deals> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().getDeals().size());
                            addRecylerView(tag , response.body().getDeals());

                } catch (Exception e) {
                    Log.d(TAG, "onResponse: "+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Deals> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        myLayout = view.findViewById(R.id.main);
        ButterKnife.bind(this , view);
//        initLoading();
        initRecyclerView();
//        addRecylerView("ادوات منزلية" , itemList);
//        addRecylerView("اجهزة كهربائية" , itemList);
//        addRecylerView("مكياج وميك أب" , itemList);
//        addRecylerView("ادوات منزلية" , itemList);
//        addRecylerView("اجهزة كهربائية" , itemList);

        result = new ArrayList<View>();

        for (int i = 0; i < myLayout.getChildCount(); i++) {

            ArrayList<View> viewArrayList = new ArrayList<View>();
            View child = myLayout.getChildAt(i);
            viewArrayList.add(child);
            result.addAll(viewArrayList);
        }

        for (View view : result){
            anmateView(view);
        }
    }

    private void anmateView(View view) { }


    private void addRecylerView(String title, List<Deal> list) {
        //title
        TextView titleTextView = new TextView(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.START;
        layoutParams.setMarginStart(100);
        titleTextView.setText(title);
        titleTextView.setTextSize(25);
        titleTextView.setTextColor(getResources().getColor(R.color.primary_text));
        titleTextView.setLayoutParams(layoutParams);
        myLayout.addView(titleTextView);
        //RecyclerView
        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity() , RecyclerView.HORIZONTAL , false));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity() , 2, RecyclerView.HORIZONTAL , false ));
         recyclerView.setAdapter(new ItemAdapter(getActivity() , list  , this));
        myLayout.addView(recyclerView);

    }

    private void initRecyclerView() {
        itemList = new ArrayList<>();
        itemList.add(new Item());itemList.add(new Item());itemList.add(new Item());itemList.add(new Item());
        itemList.add(new Item());itemList.add(new Item());itemList.add(new Item());itemList.add(new Item());
        itemList.add(new Item());itemList.add(new Item());itemList.add(new Item());itemList.add(new Item());
        itemList.add(new Item());itemList.add(new Item());itemList.add(new Item());itemList.add(new Item());
    }

    private void initLoading() {
        loading = view.findViewById(R.id.loading);

        final ScheduledThreadPoolExecutor executor_ = new ScheduledThreadPoolExecutor(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loading.setImageResource(image1 ? R.drawable.load1 : R.drawable.load2);
                image1 = !image1 ;
            }
        };
        executor_.scheduleWithFixedDelay(runnable, 0L, 400, TimeUnit.MILLISECONDS);

        executor_.remove(runnable);
        Log.d(TAG, "onCreateView: "+this.hashCode());
    }

    @Override
    public void onProductItemClicked(Deal item) {
        Log.d(TAG, "onProductItemClicked: ");
        loadFragment(BookDetailsFragment.newInstance("نتائج البحث"));
    }
}