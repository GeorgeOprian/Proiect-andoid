package com.example.cinehub.API;

import com.example.cinehub.Movie.BookingDTO;
import com.example.cinehub.Movie.GetMoviesDTO;
import com.example.cinehub.Movie.MovieDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerApiService {
    @GET("all_movies")
    Call<GetMoviesDTO> getMovies();

    @POST("add_movie")
    Call<MovieDTO> addMovie(@Body MovieDTO movieModel);

    @POST("add_booking")
    Call<BookingDTO> addBooking(@Body BookingDTO booking);

}
