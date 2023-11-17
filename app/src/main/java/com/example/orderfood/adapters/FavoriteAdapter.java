package com.example.orderfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.R;
import com.example.orderfood.models.Favorite;
import com.example.orderfood.models.Food;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FoodRepository;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private List<Favorite> favoriteItemList;
    private Context context;
    FoodRepository foodRepository;
    DBHelper db;

    public FavoriteAdapter(List<Favorite> favoriteItemList, Context context) {
        this.favoriteItemList = favoriteItemList;
        this.context = context;
        foodRepository = new FoodRepository(db);
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_horizontal, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Favorite favoriteItem = favoriteItemList.get(position);
        Food food = foodRepository.getById(favoriteItem.getFoodId());
        // Set data to views in ViewHolder
        holder.textViewProductName.setText(food.getName());
        holder.textViewProductPrice.setText(food.getPrice());
        // Set other data as needed

        // Set onClickListener for remove favorite button if needed
        holder.imageViewRemoveFavorite.setOnClickListener(v -> {
            // Handle remove favorite action
            // You can update the dataset and notify the adapter here
        });

        // Set onClickListener for Add to Cart button
        holder.btnAddToCart.setOnClickListener(v -> {
            // Handle Add to Cart action
            // You can implement the logic to add the item to the cart here
        });
    }

    @Override
    public int getItemCount() {
        return favoriteItemList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewProduct;
        public TextView textViewProductName;
        public TextView textViewProductPrice;
        public ImageView imageViewRemoveFavorite;
        public Button btnAddToCart;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            imageViewRemoveFavorite = itemView.findViewById(R.id.imageViewRemoveFavorite);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}


