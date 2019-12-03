package com.atp.rewayti.ui.bookDeails;


import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atp.rewayti.API.model.Deal;
import com.atp.rewayti.R;
import com.atp.rewayti.Base.BaseFragment;
import com.atp.rewayti.util.Constants;
import com.bumptech.glide.Glide;

public class BookDetailsFragment extends BaseFragment {
    private static final String TAG = "BookDetailsFragment";
    private View view;

    public BookDetailsFragment() {
    }

    public static BookDetailsFragment newInstance(String title, Deal deal) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_TITLE, title);
        args.putSerializable("deal" , deal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_book_details, container, false);

        if (getArguments() != null) {
            Deal deal = (Deal) getArguments().getSerializable("deal");
            updateui(deal);
            Log.d(TAG, "onCreateView: "+deal);
        }

        return view;
    }

    private void updateui(Deal deal) {
        TextView author = view.findViewById(R.id.author);
        TextView description = view.findViewById(R.id.description);
        ImageView imageView = view.findViewById(R.id.image_detail);

        author.setText(deal.getAuthor());
        description.setText(Html.fromHtml(deal.getDescription()));

        Glide.with(getActivity()).load(deal.getBookImage()).into(imageView);

    }

}
