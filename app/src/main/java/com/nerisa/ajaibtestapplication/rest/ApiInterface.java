package com.nerisa.ajaibtestapplication.rest;

import com.nerisa.ajaibtestapplication.models.GithubProfileDetail;
import com.nerisa.ajaibtestapplication.models.GithubProfileSearch;
import com.nerisa.ajaibtestapplication.models.GithubUserRepositorySearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("users/{username}")
    Call<GithubProfileDetail> detailUser(@Header("Authorization") String authorization, @Path("username") String username);

    @GET("/search/users")
    Call<GithubProfileSearch> searchUser(@Header("Authorization") String authorization, @Query("q") String username, @Query("page") String page, @Query("per_page") String per_page);

    @GET("users/{username}/repos")
    Call<GithubUserRepositorySearch> searchUserRepository(@Header("Authorization") String authorization, @Path("username") String username);

}
