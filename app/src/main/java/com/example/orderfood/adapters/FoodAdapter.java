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

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    List<Food> list;
    Context context;

    public FoodAdapter(List<Food> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_food_item,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food=list.get(position);
        holder.tvName.setText(food.getName());
        holder.tvPrice.setText(food.getPrice());
        Glide.with(context).load(food.getImage()).into(holder.imgFood);
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvPrice;
        ImageView imgFood,imgFavorite,imgAddToCart;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            imgFood=itemView.findViewById(R.id.imgFood);
            imgFavorite=itemView.findViewById(R.id.imgFavorite);
            imgAddToCart=itemView.findViewById(R.id.imgAddToCart);
        }
    }
}
