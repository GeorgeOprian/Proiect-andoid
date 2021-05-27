package com.example.cinehub.API;

import com.example.cinehub.Movie.ResponseGetMovies;
import com.example.cinehub.Movie.MovieDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerApiService {
    @GET("all_movies")
    Call<ResponseGetMovies> getMovies();

    @POST("add_movie")
    Call<MovieDTO> addMovie(@Body MovieDTO movieModel);

}
