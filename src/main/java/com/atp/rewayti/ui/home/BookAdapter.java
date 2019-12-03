package com.atp.rewayti.ui.home;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atp.rewayti.API.model.Deal;
import com.atp.rewayti.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ItemViewHolder> {
    private static final String TAG = "BookAdapter";
    List<Deal> itemList = new ArrayList<>();
    OnProductItemClicked onProductItemClicked ;

    public BookAdapter(Context activity, List<Deal> itemList , OnProductItemClicked onProductItemClicked) {
        this.itemList = itemList;
        this.onProductItemClicked = onProductItemClicked;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_book, parent , false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Deal item = itemList.get(position);
        Glide.with(holder.imageView).load(item.getBookImage()).into(holder.imageView);

        holder.title.setText(item.getBookName());
        holder.description.setText(Html.fromHtml(item.getDescription()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductItemClicked.onProductItemClicked(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title , description;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView3);
            title = itemView.findViewById(R.id.textView3);
            description = itemView.findViewById(R.id.textView4);
        }
    }


    public interface OnProductItemClicked{
        void onProductItemClicked(Deal item);
    }
}
