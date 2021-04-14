package com.example.cinehub.API;

import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.SearchMovieActivity.SearchResults;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("/")
    Call<MovieModel> getMovie(
            @Query("apiKey") String apiKey,
            @Query("t") String search
    );
    @GET("/")
    Call<SearchResults> searchMoviesByTitle(
            @Query("apiKey") String apiKey,
            @Query("s") String search
    );


}
