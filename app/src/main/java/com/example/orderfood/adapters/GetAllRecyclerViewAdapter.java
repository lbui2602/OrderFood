package com.example.orderfood.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class GetAllRecyclerViewAdapter extends RecyclerView.Adapter<GetAllRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Food> foodList;
    DBHelper dbHelper;
    FoodRepository foodRepository;
    CartRepository cartRepository;
    FavouriteRepository favouriteRepository;
    List<Integer> listId ;
    IClick iClick;

    public GetAllRecyclerViewAdapter(Context context, List<Food> foodList,IClick iClick) {
        this.context = context;
        this.foodList = foodList;
        dbHelper=new DBHelper(context);
        foodRepository=new FoodRepository(dbHelper);
        cartRepository=new CartRepository(dbHelper,context);
        favouriteRepository=new FavouriteRepository(dbHelper);
        listId = favouriteRepository.getFoodId(PrefManager.getUserId(context, "username"));
        this.iClick=iClick;
    }

    @NonNull
    @Override
    public GetAllRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_item_horizontal, parent, false);
        return new GetAllRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllRecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Food food = foodList.get(position);

        Glide.with(context).load(food.getImage()).into(holder.imageView);

        holder.tv_food_name.setText(food.getName());
        holder.tv_food_price.setText(food.getPrice());
        holder.imgAddToCartAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check=cartRepository.checkCart(PrefManager.getUserId(context,"username"),foodList.get(position).getId());
                if(check==true){
                    Cart cart=cartRepository.getCartByFoodIdUserId(PrefManager.getUserId(context,"username"),foodList.get(position).getId());
                    cart.setQuantity(cart.getQuantity()+1);
                    cartRepository.updateCart(cart,foodList.get(position).getId());
                }else{
                    Cart cart=new Cart(PrefManager.getUserId(context,"username"),foodList.get(position).getId(),1);
                    cartRepository.addCart(cart);
                }
                Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
            }
        });
        if(listId.contains(food.getId())){
            holder.imgFav.setImageResource(R.drawable.favorite_icon_checked);
        }else{
            holder.imgFav.setImageResource(R.drawable.favorite_icon);
        }
        holder.imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listId.contains(food.getId())){
                    holder.imgFav.setImageResource(R.drawable.favorite_icon);
                    iClick.onClickDeleteFavorite(food.getId(),position);
                    favouriteRepository.deleteFavourite(PrefManager.getUserId(context,"username"), food.getId());
                    listId=favouriteRepository.getFoodId(PrefManager.getUserId(context, "username"));
                    Log.d("TAG", "onClick: "+listId.size());
                }else{
                    holder.imgFav.setImageResource(R.drawable.favorite_icon_checked);
                    listId.add(food.getId());
                    favouriteRepository.addFavourite(new Favorite(PrefManager.getUserId(context,"username"), food.getId()));
                }
            }
        });
        holder.llFoodItemGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClick.onClickFoodItem(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_food_name, tv_food_price;
        ImageView imgAddToCartAll,imgFav;
        LinearLayout llFoodItemGetAll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewProduct);
            tv_food_name = itemView.findViewById(R.id.textViewProductName);
            tv_food_price = itemView.findViewById(R.id.textViewProductPrice);
            imgAddToCartAll=itemView.findViewById(R.id.imgAddToCartAll);
            imgFav=itemView.findViewById(R.id.imgFav);
            llFoodItemGetAll=itemView.findViewById(R.id.llFoodItemGetAll);
        }
    }
}
