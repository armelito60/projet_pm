package com.example.projet_pm;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MatchAPI {
    @GET("/video-api/v1")
    Call<ArrayList<Racine>> getRacine();

}
