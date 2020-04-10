package com.example.projet_pm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MatchAPI {
    @GET("/video-api/v1")
    Call<Racine> getRacine();
}
