package com.example.projet_pm.presentation.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.projet_pm.Singletons;
import com.example.projet_pm.R;
import com.example.projet_pm.presentation.controler.MainController;

import java.util.ArrayList;

import com.example.projet_pm.presentation.modele.Match;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext())
        );
        controller.onStart();

    }

    public void showList(ArrayList<Match> matchArrayList) {
        // cherche l'id recycler_view et instanciation avec la variable recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // taille fixe du recyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // on donne la liste input à la liste mAdapter
        mAdapter = new ListAdapter(matchArrayList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Match item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Match match) {
        Toast.makeText(getApplicationContext(), "TODOO NAVIGATE", Toast.LENGTH_SHORT).show();
    }
}

