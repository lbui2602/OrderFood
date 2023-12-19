package com.example.orderfood.uis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orderfood.IClick;
import com.example.orderfood.R;
import com.example.orderfood.adapters.FoodAdapter;
import com.example.orderfood.models.Food;
import com.example.orderfood.models.User;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FavouriteRepository;
import com.example.orderfood.models.database.FoodRepository;
import com.example.orderfood.models.database.PrefManager;
import com.example.orderfood.models.database.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment implements IClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<Food> favoriteList;

    FoodAdapter foodAdapter;
    RecyclerView rcv;
    DBHelper dbHelper;
    FavouriteRepository favouriteRepository;
    FoodRepository foodRepository;
    UserRepository userRepository;
    NavController navController;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcvFavoriteFood);
        dbHelper = new DBHelper(getContext());
        navController= NavHostFragment.findNavController(FavoriteFragment.this);
        foodRepository=new FoodRepository(dbHelper);
        favouriteRepository = new FavouriteRepository(dbHelper);
        List<Integer> list=new ArrayList<>();
        list=favouriteRepository.getFavorite(1);
        for(int i=0;i<list.size();i++){
            Log.d("TAG", "favorite: "+list.get(i));
        }
        userRepository=new UserRepository(dbHelper);
        String username = PrefManager.getString(getContext(), "username");
        User user=userRepository.getUserByUsername(username);
//        favoriteList = favouriteRepository.getFoodsByUserId(user.getId());
        favoriteList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            favoriteList.add(foodRepository.getFoodByFoodId(list.get(i)));
        }
        for(int i=0;i<favoriteList.size();i++){
            Log.d("TAG", "fasvorite: "+ favoriteList.get(i).getName());
        }
        foodAdapter = new FoodAdapter(favoriteList,getContext(),FavoriteFragment.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this.getContext(),2);
        rcv.setLayoutManager(gridLayoutManager);
        rcv.setAdapter(foodAdapter);
        foodAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClickDeleteFavorite(int foodId, int pos) {
        favoriteList.remove(pos);
        foodAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickFoodItem(Food food) {
        Bundle bundle=new Bundle();
        bundle.putInt("food_id", food.getId());
        navController.navigate(R.id.action_favoriteFragment_to_detailsFragment,bundle);
    }
}