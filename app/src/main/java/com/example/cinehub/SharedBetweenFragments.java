package com.example.cinehub;

import com.example.cinehub.Movie.MovieDTO;
import com.example.cinehub.Movie.MovieModel;
import com.google.firebase.auth.FirebaseUser;

public class SharedBetweenFragments {

    private static SharedBetweenFragments instance;
    private MovieModel movieToAddDisplayData;
    private MovieDTO movieToPassForDetailsDisplay;
    public static final int COULD_NOT_INSERT_IN_DB = 512;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int MAX_NUMBER_OF_SEATS_IN_HALL = 50;
    private FirebaseUser user;

    private SharedBetweenFragments() { }

    public static SharedBetweenFragments getInstance() {
        if (instance == null) {
            instance = new SharedBetweenFragments();
        }
        return instance;
    }

    public MovieModel getMovieToAddDisplayData() {
        return movieToAddDisplayData;
    }

    public void setMovieToAddDisplayData(MovieModel movieToAddDisplayData) {
        this.movieToAddDisplayData = movieToAddDisplayData;
    }

    public MovieDTO getMovieToPassForDetailsDisplay() {
        return movieToPassForDetailsDisplay;
    }

    public void setMovieToPassForDetailsDisplay(MovieDTO movieToPassForDetailsDisplay) {
        this.movieToPassForDetailsDisplay = movieToPassForDetailsDisplay;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }
}
