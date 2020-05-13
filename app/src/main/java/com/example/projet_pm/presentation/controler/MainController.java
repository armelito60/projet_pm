package com.example.projet_pm.presentation.controler;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.projet_pm.Constants;
import com.example.projet_pm.data.MatchAPI;
import com.example.projet_pm.presentation.modele.Match;
import com.example.projet_pm.presentation.vue.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {

        ArrayList<Match> matchList = getDataFromCache();
        if(matchList != null) {
            view.showList(matchList);
        } else {
            makeApiCall();
        }
    }

    private void makeApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        MatchAPI matchAPI = retrofit.create(MatchAPI.class);

        Call<ArrayList<Match>> call = matchAPI.getMatch();
        call.enqueue(new Callback<ArrayList<Match>>() {
            @Override
            public void onResponse(Call<ArrayList<Match>> call, Response<ArrayList<Match>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    ArrayList<Match> match = response.body();
                    saveList(match);
                    view.showList(match);
                }
                else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Match>> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(ArrayList<Match> match) {
        String jsonString = gson.toJson(match);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_MATCH_LIST, jsonString)
                .apply();
        Toast.makeText(view.getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Match> getDataFromCache() {
        String jsonMatch = sharedPreferences.getString(Constants.KEY_MATCH_LIST, null);

        if(jsonMatch == null) {
            return null;
        } else {
            Type listType = new TypeToken<ArrayList<Match>>(){}.getType();
            return gson.fromJson(jsonMatch, listType);
        }

    }

    public void onItemClick(Match match) {

    }

    public void onButtonAClick() {

    }

    public void onButtonBClick() {

    }
}
