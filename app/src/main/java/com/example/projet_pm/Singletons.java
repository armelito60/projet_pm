package com.example.projet_pm;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projet_pm.Constants;
import com.example.projet_pm.data.MatchAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gsonInstance;
    private static MatchAPI matchApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson() {
        if(gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static MatchAPI getMatchAPI() {
        if(matchApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
            matchApiInstance = retrofit.create(MatchAPI.class);
        }
        return matchApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        if(sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences(Constants.KEY_MATCH_STORAGE, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
