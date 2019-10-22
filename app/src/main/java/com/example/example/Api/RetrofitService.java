package com.example.example.Api;

import com.example.example.Api.Data.Image;
import com.example.example.Api.Data.ImageDocument;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitService {
    @Headers("Authorization: KakaoAK 495f87b7d756c408a50af5c30d5a3892")

    @GET("/v2/search/image")
    Call<JsonObject> requestSearchImage(@Query("query") String keyword);
}
