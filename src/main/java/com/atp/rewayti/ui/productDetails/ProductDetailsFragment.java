package com.atp.rewayti.ui.productDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atp.rewayti.R;
import com.atp.rewayti.util.Constants;

public class ProductDetailsFragment extends Fragment {


    public ProductDetailsFragment() {}

    public static ProductDetailsFragment newInstance(String title) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }
}
