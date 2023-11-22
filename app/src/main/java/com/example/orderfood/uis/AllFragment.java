package com.example.orderfood.uis;

import static android.view.View.GONE;
import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.orderfood.R;
import com.example.orderfood.adapters.GetAllRecyclerViewAdapter;
import com.example.orderfood.models.Food;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FoodRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DBHelper dbHelper;
    List<Food> foodListOriginal;
    List<Food> foodList;
    GetAllRecyclerViewAdapter adapter;
    SearchView searchView;

    Boolean focusSearchView;

    RecyclerView recyclerView;

    public AllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllFragment newInstance(String param1, String param2) {
        AllFragment fragment = new AllFragment();
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
        View view = inflater.inflate(R.layout.fragment_all, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.get_all_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doSearch(newText);
                return false;
            }
        });
        focusSearchView = getArguments().getBoolean("focusSearchView");
        if (focusSearchView) {
//            String query = getArguments().getString("query");
//            new Handler().postDelayed(() -> {
//                searchView.requestFocus();
////                searchView.setIconified(false);
//            }, 500);

        } else {
            searchView.setVisibility(GONE);
        }

        int searchCloseButtonId = searchView.findViewById(androidx.appcompat.R.id.search_close_btn).getId();
        ImageView closeButton = searchView.findViewById(searchCloseButtonId);
        closeButton.setOnClickListener(v -> {
            if (focusSearchView) {
                NavController navController = NavHostFragment.findNavController(AllFragment.this);
                navController.navigate(R.id.action_allFragment_to_homeFragment);
            } else {
                searchView.setQuery("", false);
                searchView.clearFocus();
            }
        });
        dbHelper = new DBHelper(getContext());
        foodListOriginal = new FoodRepository(dbHelper).getAll();

        foodList = new ArrayList<>(foodListOriginal);
        recyclerView = view.findViewById(R.id.rcvAll);

        adapter = new GetAllRecyclerViewAdapter(getContext(), foodList);
        if(focusSearchView) {
                String query = getArguments().getString("query");
                searchView.setQuery(query, false);
        }
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 20;
//                super.getItemOffsets(outRect, view, parent, state);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void doSearch(String newText) {
        List<Food> list = new ArrayList<>();
        for (Food food : foodListOriginal) {
            if (food.getName().toLowerCase().contains(newText.toLowerCase())) {
                list.add(food);
            }
        }
        foodList.clear();
        foodList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}