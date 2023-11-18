package com.example.orderfood.uis;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.orderfood.R;
import com.example.orderfood.models.Food;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FavouriteRepository;
import com.example.orderfood.models.database.FoodRepository;
import com.example.orderfood.models.database.PrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    DBHelper dbHelper;
    List<Food> foodList;

    List<SpecialContainer> specialList;
    List<ImageView> recommendedList;
    LinearLayout llChicken,llKorean,llDrink;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        dbHelper=new DBHelper(getContext());
        foodList = new FoodRepository(dbHelper).getAll();

        List<Food> list = new ArrayList<>();
        for (Food food : foodList) {
            if (food.getName().length() < 12) {
                list.add(food);
            }
        }
        foodList = list;

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getSpecialAndRecommend(view);
        setSpecialAndRecommend();

        CardView cardView = view.findViewById(R.id.get_all);
        cardView.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), GetAllActivity.class));
        });

        return view;


    }
    private void getSpecialAndRecommend(View view) {
        specialList = new ArrayList<>();

        final int quantity = 3;
        for (int i = 1; i <= quantity; i++) {
            int imageId = getResources().getIdentifier("special_image_" + i, "id", requireContext().getPackageName());
            int nameId = getResources().getIdentifier("special_food_name_" + i, "id", requireContext().getPackageName());
            int priceId = getResources().getIdentifier("special_food_price_" + i, "id", requireContext().getPackageName());
            int seeDetailId = getResources().getIdentifier("special_see_detail_" + i, "id", requireContext().getPackageName());
        specialList.add(
                new SpecialContainer(view.findViewById(imageId),
                view.findViewById(nameId),
                view.findViewById(priceId),
                view.findViewById(seeDetailId)));
        }

        recommendedList = new ArrayList<>();

        for (int i = 1; i <= quantity; i++) {
            int resId = getResources().getIdentifier("recommend_image_" + i, "id", requireContext().getPackageName());
            recommendedList.add(view.findViewById(resId));
        }
    }

    public void setSpecialAndRecommend() {
        final int quantity = 3;
         for(int i = 0; i < quantity; i++) {
             SpecialContainer specialContainer = specialList.get(i);

             Food food = foodList.get(i);
             Picasso.get().load(food.getImage()).into(specialContainer.getImageView());
             Glide.with(this).load(food.getImage()).into(specialContainer.getImageView());


             specialContainer.getFoodName().setText(food.getName());
             specialContainer.getFoodPrice().setText(food.getPrice());
         }
        for(int i = 0; i < quantity; i++) {
            Food food = foodList.get(foodList.size() - i - 1);
            Glide.with(this).load(food.getImage()).into(recommendedList.get(i));
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String username=PrefManager.getString(getContext(),"username");
        llChicken=view.findViewById(R.id.llChicken);
        llKorean=view.findViewById(R.id.llKorean);
        llDrink=view.findViewById(R.id.llDrink);

        llChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationToType(1);
            }
        });
        llKorean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationToType(2);
            }
        });
        llDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationToType(3);
            }
        });
    }

    private void navigationToType(int menuId) {
        Bundle bundle=new Bundle();
        bundle.putInt("menu_id",menuId);
        NavController navController = NavHostFragment.findNavController(HomeFragment.this);
        navController.navigate(R.id.action_homeFragment_to_typeFragment,bundle);
    }
}