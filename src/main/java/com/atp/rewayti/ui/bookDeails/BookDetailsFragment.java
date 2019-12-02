package com.atp.rewayti.ui.bookDeails;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atp.rewayti.R;
import com.atp.rewayti.ui.base.BaseFragment;
import com.atp.rewayti.util.Constants;

public class BookDetailsFragment extends BaseFragment {
    private static final String TAG = "BookDetailsFragment";

    public BookDetailsFragment() {
    }

    public static BookDetailsFragment newInstance(String title) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultfrahment_list, container, false);


        return view;
    }

}
