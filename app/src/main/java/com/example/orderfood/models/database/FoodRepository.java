package com.example.orderfood.models.database;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.models.Food;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class FoodRepository {

    private final String TABLE_NAME = "food";
    private DBHelper dbHelper;

    public FoodRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Food getById(int id) {
        String statement = "SELECT * FROM " + TABLE_NAME + " WHERE user_id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery(statement, new String[]{id + ""});

        return new Food(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4));
    }
<<<<<<< HEAD

    public List<Food> getAll() {
        String statement = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<Food> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(statement, null);
        while (cursor.moveToNext()) {
            list.add(new Food(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3) ));
        }
        return list;
=======
    public List<Food> getFoodByMenuId(int menuId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Food> foodList = new ArrayList<>();

        String[] projection = {
                "food_id",
                "name",
                "price",
                "image",
                "menu_id"
        };

        String selection = "menu_id = ?";
        String[] selectionArgs = {String.valueOf(menuId)};

        try (Cursor cursor = db.query(
                "Food",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {
            while (cursor != null && cursor.moveToNext()) {
                @SuppressLint("Range") Food food = new Food(
                        cursor.getInt(cursor.getColumnIndex("food_id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("price")),
                        cursor.getString(cursor.getColumnIndex("image")),
                        cursor.getInt(cursor.getColumnIndex("menu_id"))
                );
                foodList.add(food);
            }
        } catch (Exception e) {
            // Handle exceptions, log, or throw as needed
            e.printStackTrace();
        }

        return foodList;
>>>>>>> fc582bc63d9a32c19d398ab46dd76058bf3d5934
    }

}
