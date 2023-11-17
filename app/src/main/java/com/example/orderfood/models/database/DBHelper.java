package com.example.orderfood.models.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "food.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("TAG", "onCreate: ");
        try {
            InputStream inputStream = context.getAssets().open("database.sql");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            String createScript = stringBuilder.toString();
            bufferedReader.close();
            String[] statements = createScript.split(";");

            for (String statement : statements) {
                Log.d("DatabaseHelper", statement);
                if (statement.trim().length() > 0)
                    sqLiteDatabase.execSQL(statement);
            }
        } catch (IOException e) {
            Log.d("TAG", "Error: " + e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
