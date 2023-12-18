package com.example.orderfood;

import com.example.orderfood.models.Cart;
import com.example.orderfood.models.Food;

public interface IClick {
    void onClickDeleteFavorite(int foodId,int pos);
    void onClickFoodItem(Food food);
}
