package com.example.projet_pm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
