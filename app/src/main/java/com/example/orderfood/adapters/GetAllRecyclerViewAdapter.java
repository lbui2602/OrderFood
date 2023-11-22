package com.example.orderfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orderfood.R;
import com.example.orderfood.models.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GetAllRecyclerViewAdapter extends RecyclerView.Adapter<GetAllRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Food> foodList;

    public GetAllRecyclerViewAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public GetAllRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_item_horizontal, parent, false);
        return new GetAllRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllRecyclerViewAdapter.MyViewHolder holder, int position) {
        Food food = foodList.get(position);

        Glide.with(context).load(food.getImage()).into(holder.imageView);

        holder.tv_food_name.setText(food.getName());
        holder.tv_food_price.setText(food.getPrice());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_food_name, tv_food_price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewProduct);
            tv_food_name = itemView.findViewById(R.id.textViewProductName);
            tv_food_price = itemView.findViewById(R.id.textViewProductPrice);
        }
    }
}
