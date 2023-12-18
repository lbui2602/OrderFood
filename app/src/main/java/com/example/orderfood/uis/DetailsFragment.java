package com.example.orderfood.uis;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.orderfood.R;
import com.example.orderfood.models.Cart;
import com.example.orderfood.models.Food;
import com.example.orderfood.models.database.CartRepository;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FoodRepository;
import com.example.orderfood.models.database.PrefManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView imgFoodDetail,imgAddToCartDetail,imgCongDetail,imgTruDetail;
    TextView tvNameDetail,tvPriceDetail,tvQuantityDetail;
    int foodId;
    FoodRepository foodRepository;
    CartRepository cartRepository;
    DBHelper dbHelper;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        foodId=getArguments().getInt("food_id");
        initView(view);
        int quantity=Integer.parseInt(tvQuantityDetail.getText().toString());
        imgCongDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvQuantityDetail.setText(String.valueOf(Integer.parseInt(tvQuantityDetail.getText().toString())+1));
            }
        });
        imgTruDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(tvQuantityDetail.getText().toString())>0){
                    tvQuantityDetail.setText(String.valueOf(Integer.parseInt(tvQuantityDetail.getText().toString())-1));
                }
            }
        });
        imgAddToCartDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check=cartRepository.checkCart(PrefManager.getUserId(getContext(),"username"),foodId);
                if(check==true && Integer.parseInt(tvQuantityDetail.getText().toString()) >0 ){
                    Cart cart=cartRepository.getCartByFoodIdUserId(PrefManager.getUserId(getContext(),"username"),foodId);
                    cart.setQuantity(cart.getQuantity()+Integer.parseInt(tvQuantityDetail.getText().toString()));
                    cartRepository.updateCart(cart,foodId);
                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    if(Integer.parseInt(tvQuantityDetail.getText().toString())>0){
                        Cart cart=new Cart(PrefManager.getUserId(getContext(),"username"),foodId,Integer.parseInt(tvQuantityDetail.getText().toString()));
                        cartRepository.addCart(cart);
                        Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView(View view) {
        imgFoodDetail=view.findViewById(R.id.imgFoodDetail);
        imgCongDetail=view.findViewById(R.id.imgCongDetail);
        imgTruDetail=view.findViewById(R.id.imgTruDetail);
        imgAddToCartDetail=view.findViewById(R.id.imgAddToCartDetail);
        tvNameDetail=view.findViewById(R.id.tvNameDetail);
        tvPriceDetail=view.findViewById(R.id.tvPriceDetail);
        tvQuantityDetail=view.findViewById(R.id.tvQuantityDetail);
        dbHelper=new DBHelper(getContext());
        foodRepository=new FoodRepository(dbHelper);
        cartRepository=new CartRepository(dbHelper,getContext());
        Food food=foodRepository.getFoodByFoodId(foodId);
        Glide.with(getContext()).load(food.getImage()).into(imgFoodDetail);
        tvNameDetail.setText(food.getName());
        tvPriceDetail.setText(food.getPrice());
        tvQuantityDetail.setText("0");
    }
}