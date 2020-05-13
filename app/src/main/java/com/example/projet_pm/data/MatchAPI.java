package com.example.projet_pm.data;

import java.util.ArrayList;

import com.example.projet_pm.presentation.modele.Match;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchAPI {
    @GET("/video-api/v1")
    Call<ArrayList<Match>> getMatch();

}