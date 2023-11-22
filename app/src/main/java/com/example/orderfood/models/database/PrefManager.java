package com.example.orderfood.models.database;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    public static final String SHEF_NAME="food";

    static DBHelper dbHelper;
    static UserRepository userRepository;
    public static void saveString(Context context, String key, String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHEF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getString(Context context, String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHEF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
    public static void removeKey(Context context,String key){
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHEF_NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).apply();
    }

    public static int getUserId(Context context,String key){
        dbHelper=new DBHelper(context);
        userRepository=new UserRepository(dbHelper);
        int id=userRepository.getUserByUsername(getString(context,key)).getId();
        return id;
    }
}
