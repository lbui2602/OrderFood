package com.example.orderfood.uis;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.orderfood.R;
import com.example.orderfood.models.Food;
import com.example.orderfood.models.SpecialContainer;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FoodRepository;
import com.example.orderfood.models.database.PrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    CardView cardView;

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

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getSpecialAndRecommend(view);
        setSpecialAndRecommend();

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
        foodList = foodList.stream().filter(food -> food.getName().length() < 12).collect(Collectors.toList());
        final int quantity = 3;
         for(int i = 0; i < quantity; i++) {
             setSpecialDetail(specialList.get(i), getRandomFood());
             //set recommend
             setRecommenDetail(recommendedList.get(i), getRandomFood());
         }
    }

    private Food getRandomFood() {
        int index = (int) (Math.random() * (foodList.size() - 1));
        Food food = foodList.get(index);
        foodList.remove(index);
        return food;
    }

    private void setSpecialDetail(SpecialContainer specialContainer, Food food) {
        Glide.with(this).load(food.getImage()).into(specialContainer.getImageView());
        specialContainer.getFoodName().setText(food.getName());
        specialContainer.getFoodPrice().setText(food.getPrice() + " vnd");
        specialContainer.getSeeDetail().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationToDetail(food.getId());
            }
        });
    }
    private void setRecommenDetail(ImageView imageView, Food food) {
        Glide.with(this).load(food.getImage()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationToDetail(food.getId());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String username=PrefManager.getString(getContext(),"username");
        llChicken=view.findViewById(R.id.llChicken);
        llKorean=view.findViewById(R.id.llKorean);
        llDrink=view.findViewById(R.id.llDrink);

        SearchView searchView = view.findViewById(R.id.home_search_view);

        searchView.setOnClickListener(v -> {

        });
//        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                NavController navController = NavHostFragment.findNavController(HomeFragment.this);
                Bundle bundle = new Bundle();
                bundle.putBoolean("focusSearchView", true);
                bundle.putString("query", query);
                navController.navigate(R.id.action_homeFragment_to_allFragment, bundle);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        cardView= view.findViewById(R.id.get_all);
        cardView.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(HomeFragment.this);
            Bundle bundle = new Bundle();
            bundle.putBoolean("focusSearchView", false);
            navController.navigate(R.id.action_homeFragment_to_allFragment, bundle);
        });

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
//        specialList.get(0).getSeeDetail().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navigationToDetail(4);
//            }
//        });
//        specialList.get(1).getSeeDetail().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navigationToDetail(11);
//            }
//        });
//        specialList.get(2).getSeeDetail().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navigationToDetail(12);
//            }
//        });
//        recommendedList.get(0).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navigationToDetail(35);
//            }
//        });
//        recommendedList.get(1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navigationToDetail(34);
//            }
//        });
//        recommendedList.get(2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navigationToDetail(31);
//            }
//        });
    }

    private void navigationToType(int menuId) {
        Bundle bundle=new Bundle();
        bundle.putInt("menu_id",menuId);
        NavController navController = NavHostFragment.findNavController(HomeFragment.this);
        navController.navigate(R.id.action_homeFragment_to_typeFragment,bundle);
    }
    private void navigationToDetail(int foodId) {
        Bundle bundle=new Bundle();
        bundle.putInt("food_id",foodId);
        NavController navController = NavHostFragment.findNavController(HomeFragment.this);
        navController.navigate(R.id.action_homeFragment_to_detailsFragment,bundle);
    }
}