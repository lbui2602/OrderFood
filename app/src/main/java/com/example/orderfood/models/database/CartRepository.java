package com.example.orderfood.models.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.models.Cart;
import com.example.orderfood.models.CartDTO;
import com.example.orderfood.models.Favorite;
import com.example.orderfood.models.Food;

import java.util.ArrayList;
import java.util.List;

public class CartRepository {

    private final String TABLE_NAME = "cart";
    private  FoodRepository foodRepository ;
    private DBHelper dbHelper;


    public CartRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        foodRepository = new FoodRepository(dbHelper);
    }

    public List<CartDTO> getFoodsInCartByUserId(int userId) {


        String statement = "SELECT food_id, quantity FROM " + TABLE_NAME;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(statement, null);

        List<CartDTO> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add(
                    new CartDTO(foodRepository.getById(cursor.getInt(0)), cursor.getInt(1)));
        }
        return list;
    }
    public void addCart(Cart cart){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", cart.getUserId());
        values.put("food_id", cart.getFoodId());
        values.put("quantity", cart.getQuantity());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void updateCart(Cart cart,int cartId){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", cart.getUserId());
        values.put("food_id", cart.getFoodId());
        values.put("quantity", cart.getQuantity());
        db.update(TABLE_NAME,values,"id = ?",new String[]{cartId+""});
        db.close();
    }
}
