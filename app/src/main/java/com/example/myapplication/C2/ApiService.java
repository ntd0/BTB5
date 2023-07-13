package com.example.myapplication.C2;

import com.example.myapplication.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("photos")
    Call<List<Album>> getAlbums();
}
