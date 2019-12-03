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
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.atp.rewayti.API.ApiManager;
import com.atp.rewayti.API.model.Deal;
import com.atp.rewayti.API.model.Deals;
import com.atp.rewayti.R;
import com.atp.rewayti.Base.BaseFragment;
import com.atp.rewayti.ui.bookDeails.BookDetailsFragment;
import com.atp.rewayti.util.Constants;
import java.util.List;

public class HomeFragment extends BaseFragment implements BookAdapter.OnProductItemClicked {
    private static final String TAG = "HomeFragment";

    private View view;
    private LinearLayout myLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        getDeals( "رعب");
        getDeals( "حب");
        getDeals( "اثارة");


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

    }


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
         recyclerView.setAdapter(new BookAdapter(getActivity() , list  , this));
        myLayout.addView(recyclerView);

    }


    @Override
    public void onProductItemClicked(Deal deal) {
        Log.d(TAG, "onProductItemClicked: ");
        loadFragment(BookDetailsFragment.newInstance(deal.getBookName() , deal));
    }

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_TITLE, "روايتي");
        fragment.setArguments(args);
        return fragment;
    }
}