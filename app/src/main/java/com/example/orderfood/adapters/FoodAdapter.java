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
import com.example.orderfood.models.Food;
import com.example.orderfood.models.database.CartRepository;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.PrefManager;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    List<Food> list;
    Context context;

    IClick iClick;

    CartRepository cartRepository;
    DBHelper dbHelper;

    public FoodAdapter(List<Food> list, Context context) {
        this.context=context;
        this.list = list;
        dbHelper=new DBHelper(context.getApplicationContext());
        cartRepository=new CartRepository(dbHelper);
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_food_item,parent,false);
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
