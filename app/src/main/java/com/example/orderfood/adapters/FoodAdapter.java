package com.example.orderfood.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.example.orderfood.IClick;
import com.example.orderfood.IClick;
import com.example.orderfood.R;
import com.example.orderfood.models.Cart;
import com.example.orderfood.models.Favorite;
import com.example.orderfood.models.Food;
import com.example.orderfood.models.database.CartRepository;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FavouriteRepository;
import com.example.orderfood.models.database.FoodRepository;
import com.example.orderfood.models.database.PrefManager;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    List<Food> list;
    Context context;

    CartRepository cartRepository;
    DBHelper dbHelper;
    FavouriteRepository favouriteRepository;
    List<Integer> listId ;
    IClick iClick;

    public FoodAdapter(List<Food> list, Context context,IClick iClick) {
        this.context=context;
        this.list = list;
        dbHelper=new DBHelper(context.getApplicationContext());
        cartRepository=new CartRepository(dbHelper, context);
        favouriteRepository=new FavouriteRepository(dbHelper);
        listId = favouriteRepository.getFoodId(PrefManager.getUserId(context, "username"));
        this.iClick=iClick;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food_item,parent,false);
        return new FoodViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food=list.get(position);
        holder.tvName.setText(food.getName());
        holder.tvPrice.setText(food.getPrice());
        Glide.with(context).load(food.getImage()).into(holder.imgFood);

        holder.imgAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check=cartRepository.checkCart(PrefManager.getUserId(context,"username"),list.get(position).getId());
                if(check==true){
                    Cart cart=cartRepository.getCartByFoodIdUserId(PrefManager.getUserId(context,"username"),list.get(position).getId());
                    cart.setQuantity(cart.getQuantity()+1);
                    cartRepository.updateCart(cart,list.get(position).getId());
                }else{
                    Cart cart=new Cart(PrefManager.getUserId(context,"username"),list.get(position).getId(),1);
                    cartRepository.addCart(cart);
                }
                Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
            }
        });
        if(listId.contains(food.getId())){
            holder.imgFavorite.setImageResource(R.drawable.favorite_icon_checked);
        }else{
            holder.imgFavorite.setImageResource(R.drawable.favorite_icon);
        }
        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listId.contains(food.getId())){
                    holder.imgFavorite.setImageResource(R.drawable.favorite_icon);
                    listId.remove(food.getId());
                    iClick.onClickDeleteFavorite(food.getId(),position);
                    favouriteRepository.deleteFavourite(PrefManager.getUserId(context,"username"), food.getId());
                }else{
                    holder.imgFavorite.setImageResource(R.drawable.favorite_icon_checked);
                    listId.add(food.getId());
                    favouriteRepository.addFavourite(new Favorite(PrefManager.getUserId(context,"username"), food.getId()));
                }
            }
        });
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
