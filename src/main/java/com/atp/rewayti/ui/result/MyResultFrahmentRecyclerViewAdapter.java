package com.atp.rewayti.ui.result;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atp.rewayti.R;
import com.atp.rewayti.ui.result.dummy.DummyContent.DummyItem;

import java.util.List;

public class MyResultFrahmentRecyclerViewAdapter extends RecyclerView.Adapter<MyResultFrahmentRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyResultFrahmentRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mValues.add(new DummyItem());mValues.add(new DummyItem());mValues.add(new DummyItem());mValues.add(new DummyItem());mValues.add(new DummyItem());
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_resultfrahment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DummyItem item = mValues.get(position);
////        holder.mIdView.setText("");
//        holder.mContentView.setText(mValues.get(position).content);
//
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(item);
                }
            }
        });


//        LayerDrawable stars = (LayerDrawable) holder.ratingBar.getProgressDrawable();
//        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;
        RatingBar ratingBar ;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.title);
            mContentView = (TextView) view.findViewById(R.id.content);
            ratingBar = view.findViewById(R.id.ratingBar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }
}
