package com.example.orderfood.models.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.models.Food;
import com.example.orderfood.models.User;

import java.util.ArrayList;
import java.util.List;

public class FoodRepository {

    private final String TABLE_NAME = "food";
    private DBHelper dbHelper;

    public FoodRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Food getById(int id) {
        String statement = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery(statement, new String[]{id + ""});

        return new Food(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3) );
    }

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
    }

}
