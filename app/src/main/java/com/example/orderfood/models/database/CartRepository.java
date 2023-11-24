package com.example.orderfood.models.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
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
    Context context;


    public CartRepository(DBHelper dbHelper,Context context) {
        this.dbHelper = dbHelper;
        foodRepository = new FoodRepository(dbHelper);
        this.context= context;
    }

    @SuppressLint("Range")
    public List<Cart> getFoodsInCartByUserId(int userId) {


        String statement = "SELECT * FROM " + TABLE_NAME+" WHERE user_id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(statement, new String[]{userId+""});

        List<Cart> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add(
                    new Cart(userId,cursor.getInt(cursor.getColumnIndex("food_id")),cursor.getInt(cursor.getColumnIndex("quantity"))));
        }
        return list;
    }
    public int money(){
        List<Cart> list=getFoodsInCartByUserId(PrefManager.getUserId(this.context,"username"));
        int sum=0;
        for(int i=0;i<list.size();i++){
            sum=sum+(Integer.parseInt(foodRepository.getFoodByFoodId(list.get(i).getFoodId()).getPrice())*list.get(i).getQuantity());
        }
        return sum;
    }

    @SuppressLint("Range")
    public Boolean checkCart(int userId, int foodId){
        String statement = "SELECT * FROM " + TABLE_NAME+" WHERE user_id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(statement, new String[]{userId+""});

        List<Cart> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add(
                    new Cart(userId,cursor.getInt(cursor.getColumnIndex("food_id")),cursor.getInt(cursor.getColumnIndex("quantity"))));
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getFoodId()==foodId){
                return true;
            }
        }
        return false;
    }
    @SuppressLint("Range")
    public Cart getCartByFoodIdUserId(int userId,int foodId) {
        String statement = "SELECT * FROM " + TABLE_NAME+" WHERE user_id = ? AND food_id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(statement, new String[]{userId+"",foodId+""});

        Cart cart=null;

        while (cursor.moveToNext()) {
            cart=
                    new Cart(userId,cursor.getInt(cursor.getColumnIndex("food_id")),cursor.getInt(cursor.getColumnIndex("quantity")));
        }
        return cart;
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
    public void updateCart(Cart cart,int foodId){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", cart.getUserId());
        values.put("food_id", cart.getFoodId());
        values.put("quantity", cart.getQuantity());
        db.update(TABLE_NAME,values,"food_id = ?",new String[]{foodId+""});
        db.close();
    }
}
