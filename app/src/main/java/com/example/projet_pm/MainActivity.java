package com.example.projet_pm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
        makeApiCall();
    }

    private void showList() {
        // cherche l'id recycler_view et instanciation avec la variable recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // taille fixe du recyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        List<String> input = new ArrayList<>();
        // créer une liste de 100 éléments
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }

        // on donne la liste input à la liste mAdapter
        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        MatchAPI matchAPI = retrofit.create(MatchAPI.class);

        Call<Racine> call = matchAPI.getRacine();
        call.enqueue(new Callback<Racine>() {
            @Override
            public void onResponse(Call<Racine> call, Response<Racine> response) {
                if(response.isSuccessful() && response.body() != null) {
                    ArrayList<Match> match = response.body().getMatch();
                    Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                }
                else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<Racine> call, Throwable t) {
                showError();
            }
        });
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
