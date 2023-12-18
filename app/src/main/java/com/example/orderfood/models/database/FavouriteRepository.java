package com.example.orderfood.models.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.RequiresApi;

import com.example.orderfood.models.Favorite;
import com.example.orderfood.models.Food;
import com.example.orderfood.models.User;

import java.util.ArrayList;
import java.util.List;

public class FavouriteRepository {

    private final String TABLE_NAME = "Favourite";
    private  FoodRepository foodRepository ;
    private DBHelper dbHelper;


    public FavouriteRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        foodRepository = new FoodRepository(dbHelper);
    }

    public List<Food> getFoodsByUserId(int userId) {
        String statement = "SELECT * FROM " + TABLE_NAME+" WHERE user_id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(statement, new String[]{userId+""});

        List<Favorite> listFavorite = new ArrayList<>();

        while (cursor.moveToNext()) {
            listFavorite.add(new Favorite(
                    cursor.getColumnIndex("user_id"),
                    cursor.getColumnIndex("food_id")
            ));
        }
        List<Food> list=new ArrayList<>();
        for(int i=0;i<listFavorite.size();i++){
            list.add(foodRepository.getFoodByFoodId(listFavorite.get(i).getFoodId()));
        }
        return list;
    }
    public List<Integer> getFavorite(int userId) {
        String statement = "SELECT food_id FROM " + TABLE_NAME+" WHERE user_id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(statement, new String[]{userId+""});

        List<Integer> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add(cursor.getInt(0)
            );
        }
        return list;

    }
    public List<Integer> getFoodId(int userId) {
        String statement = "SELECT food_id FROM " + TABLE_NAME+" WHERE user_id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(statement, new String[]{userId+""});

        List<Integer> idList = new ArrayList<>();

        while (cursor.moveToNext()) {
            idList.add(
                    cursor.getInt(0)
            );
        }
        return idList;
    }
    public void addFavourite(Favorite favorite){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", favorite.getUserId());
        values.put("food_id", favorite.getFoodId());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteFavourite(int userId,int foodId){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME,
                "user_id" + " = ? AND " + "food_id" + " = ?",
                new String[]{String.valueOf(userId), String.valueOf(foodId)});
        db.close();
    }

}
