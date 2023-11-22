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
import com.example.orderfood.models.Cart;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FoodRepository;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Cart> list;
    Context context;
    DBHelper dbHelper;
    FoodRepository foodRepository;

    public CartAdapter(List<Cart> list, Context context) {
        this.list = list;
        this.context = context;
        dbHelper=new DBHelper(context);
        foodRepository=new FoodRepository(dbHelper);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart=list.get(position);
        Glide.with(context).load(foodRepository.getFoodByFoodId(cart.getFoodId()).getImage()).into(holder.imgAnhInCart);
        holder.tvNameInCart.setText(foodRepository.getFoodByFoodId(cart.getFoodId()).getName());
        holder.tvQuantity.setText(String.valueOf(cart.getQuantity()));
        holder.tvPriceInCart.setText(foodRepository.getFoodByFoodId(cart.getFoodId()).getPrice());
    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }
        return list.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAnhInCart,imgCong,imgTru;
        TextView tvNameInCart,tvPriceInCart;
        TextView tvQuantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhInCart=itemView.findViewById(R.id.imgInCart);
            tvNameInCart=itemView.findViewById(R.id.tvNameInCart);
            tvQuantity=itemView.findViewById(R.id.tvQuantity);
            imgCong=itemView.findViewById(R.id.imgCong);
            imgTru=itemView.findViewById(R.id.imgTru);
            tvPriceInCart=itemView.findViewById(R.id.tvPriceInCart);
        }
    }
}
