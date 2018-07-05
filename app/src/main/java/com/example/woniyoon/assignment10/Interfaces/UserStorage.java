package com.example.woniyoon.assignment10.Interfaces;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.woniyoon.assignment10.Models.UserEntity;
import com.google.gson.Gson;

import java.util.Date;

public class UserStorage {

    private static final String USER_ENTITY = "user_entity";

    public static void setUser(Context con, UserEntity userdata) {
        SharedPreferences pref = con.getSharedPreferences(USER_ENTITY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        edit.putInt("userID", userdata.getUserId());
        edit.putString("password", userdata.getPassword());
        edit.putString("firstName", userdata.getFirstName());
        edit.putString("lastName", userdata.getLastName());
        edit.putString("country", userdata.getCountry());
        edit.putString("username", userdata.getUsername());
        edit.putString("token", userdata.getToken());
//        long millis = userdata.getJoined().getTime();
//        edit.putLong("joined", millis);
        edit.apply();
    }

    public static UserEntity getUser(Context con) {
        SharedPreferences pref = con.getSharedPreferences(USER_ENTITY, Context.MODE_PRIVATE);

        return new UserEntity(pref.getString("firstName", ""), pref.getString("lastName", ""), pref.getString("country", ""),
                pref.getString("username", ""), pref.getString("password", ""), pref.getInt("userID", 0),
                0, pref.getString("token", ""));
    }

    public static void remove(Context con){
        SharedPreferences pref = con.getSharedPreferences(USER_ENTITY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.clear();
        edit.apply();
    }
}
