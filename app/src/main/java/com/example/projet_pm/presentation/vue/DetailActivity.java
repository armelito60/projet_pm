package com.example.projet_pm.presentation.vue;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_pm.R;
import com.example.projet_pm.Singletons;
import com.example.projet_pm.presentation.modele.Match;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;
    private TextView dateDetail;
    private TextView competition;
    private TextView scoreMatch;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        intent = getIntent();
        String matchJson = intent.getStringExtra("matchKey");
        Match match = Singletons.getGson().fromJson(matchJson, Match.class);

        txtDetail = findViewById(R.id.detail_txt);
        dateDetail = findViewById(R.id.dateDetail);
        competition = findViewById(R.id.competition);
        scoreMatch = findViewById(R.id.scoreMatch);

        showDetail(match);
    }


    private void showDetail(Match match) {
        txtDetail.setText(match.getTitle());
        dateDetail.setText(match.getDate());
        competition.setText(match.getCompetition().getName());
        scoreMatch.setText("Lien pour accéder au score du match : "+match.getCompetition().getUrl().toString());
    }


}

