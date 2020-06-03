package com.example.projet_pm.presentation.vue;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_pm.R;
import com.example.projet_pm.Singletons;
import com.example.projet_pm.presentation.modele.Match;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;
    private TextView dateDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        txtDetail = findViewById(R.id.detail_txt);
        dateDetail = findViewById(R.id.dateDetail);
        Intent intent = getIntent();
        String matchJson = intent.getStringExtra("matchKey");
        Match match = Singletons.getGson().fromJson(matchJson, Match.class);

        showDetail(match);
    }

    private void showDetail(Match match) {
        txtDetail.setText(match.getTitle());
        dateDetail.setText(match.getDate());
    }


}

