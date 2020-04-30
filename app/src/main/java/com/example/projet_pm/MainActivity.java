package com.example.projet_pm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://www.scorebat.com";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("application_football", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        ArrayList<Match> matchList = getDataFromCache();
        if(matchList != null) {
            showList(matchList);
        } else {
            makeApiCall();
        }

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

    private void showList(ArrayList<Match> matchArrayList) {
        // cherche l'id recycler_view et instanciation avec la variable recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // taille fixe du recyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // on donne la liste input à la liste mAdapter
        mAdapter = new ListAdapter(matchArrayList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
                    showList(match);
                }
                else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Match>> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(ArrayList<Match> match) {
        String jsonString = gson.toJson(match);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_MATCH_LIST, jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}

