package com.example.eyd.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private final String PREF_NAME = "EYD_PREF";
    private final String USER_ID = "USER_ID";
    private final String TOKEN = "TOKEN";
    private final String EMAIL = "EMAIL";
    private final String USERNAME = "USERNAME";
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public SharedPreferenceHelper(Context appContext) {
        this.sharedPref = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = this.sharedPref.edit();
    }

    public int getUserId(){
        return sharedPref.getInt(USER_ID, -1);
    }

    public void setUserId(int userId){
        editor.putInt(USER_ID, userId).apply();
    }

    public String getToken(){
        return sharedPref.getString(TOKEN, null);
    }

    public void setToken(String token){
        editor.putString(TOKEN, token).apply();
    }

    public String getEmail(){
        return sharedPref.getString(EMAIL, null);
    }

    public void setEmail(String email){
        editor.putString(EMAIL, email).apply();
    }

    public String getUsername(){
        return sharedPref.getString(USERNAME, null);
    }

    public void setUsername(String username){
        editor.putString(USERNAME, username).apply();
    }
}
