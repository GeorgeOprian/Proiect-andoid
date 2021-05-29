package com.example.cinehub.API;

import com.example.cinehub.Movie.BookingDTO;
import com.example.cinehub.Movie.GetBookingsDTO;
import com.example.cinehub.Movie.GetMoviesDTO;
import com.example.cinehub.Movie.MovieDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApiService {
    @GET("all_movies")
    Call<GetMoviesDTO> getMovies();

    @POST("add_movie")
    Call<MovieDTO> addMovie(@Body MovieDTO movieModel);

    @POST("add_booking")
    Call<BookingDTO> addBooking(@Body BookingDTO booking);

    @GET("bookings_for_user")
    Call<GetBookingsDTO> getBookingsForUser(@Query("user_id") String userId);

}
