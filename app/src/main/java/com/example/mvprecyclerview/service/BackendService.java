package com.example.mvprecyclerview.service;

import com.example.mvprecyclerview.service.response.GetPostResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BackendService {

    @GET("/posts")
    Call<GetPostResponse> getPosts();

}
