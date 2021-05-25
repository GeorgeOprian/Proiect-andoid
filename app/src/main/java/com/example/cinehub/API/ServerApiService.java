package com.example.cinehub.API;

import com.example.cinehub.Movie.MovieModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerApiService {
    @GET("all_movies")
    Call<MovieModel> getMovies();

    @POST("add_movie")
    Call<MovieModel> addMovie(@Body MovieModel movieModel);

}
