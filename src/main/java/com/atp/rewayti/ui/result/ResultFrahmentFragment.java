package com.atp.rewayti.ui.result;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atp.rewayti.ui.base.BaseFragment;
import com.atp.rewayti.ui.productDetails.ProductDetailsFragment;
import com.atp.rewayti.ui.result.MyResultFrahmentRecyclerViewAdapter.OnListFragmentInteractionListener;
import com.atp.rewayti.R;
import com.atp.rewayti.ui.result.dummy.DummyContent;
import com.atp.rewayti.ui.result.dummy.DummyContent.DummyItem;
import com.atp.rewayti.util.Constants;

public class ResultFrahmentFragment extends BaseFragment implements OnListFragmentInteractionListener {
    private static final String TAG = "ResultFrahmentFragment";
    private OnListFragmentInteractionListener mListener;

    public ResultFrahmentFragment() {
    }

    public static ResultFrahmentFragment newInstance(String title) {
        ResultFrahmentFragment fragment = new ResultFrahmentFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultfrahment_list, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                View view1 = view.findViewById(R.id.loading_layout);
                view1.setVisibility(View.INVISIBLE);
            }
        } , 10);
        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView =  view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyResultFrahmentRecyclerViewAdapter(DummyContent.ITEMS, this));

        return view;
    }

    @Override
    public void onListFragmentInteraction(DummyItem item) {
        Log.d(TAG, "onListFragmentInteraction: ");
        loadFragment(ProductDetailsFragment.newInstance("product title"));
    }
}
