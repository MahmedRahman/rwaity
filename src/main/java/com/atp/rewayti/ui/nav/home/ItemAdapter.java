package com.atp.rewayti.ui.nav.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atp.rewayti.R;
import com.atp.rewayti.API.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private static final String TAG = "ItemAdapter";
    List<Item> itemList = new ArrayList<>();
    OnProductItemClicked onProductItemClicked ;
    int[] images = {
            R.drawable.cate1,
            R.drawable.cate2
    };
    Random random = new Random();
    public ItemAdapter(Context activity, List<Item> itemList , OnProductItemClicked onProductItemClicked) {
        this.itemList = itemList;
        this.onProductItemClicked = onProductItemClicked;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_product_iem , parent , false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Item item = itemList.get(position);
        holder.imageView.setImageResource(images[random.nextInt(2)]);
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
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView3);
        }
    }


    public interface OnProductItemClicked{
        void onProductItemClicked(Item item);
    }
}
