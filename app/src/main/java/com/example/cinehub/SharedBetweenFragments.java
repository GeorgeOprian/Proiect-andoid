package com.example.cinehub;

import com.example.cinehub.Movie.MovieModel;

public class SharedBetweenFragments {

    private static SharedBetweenFragments instance;
    private MovieModel movieToAddDisplayData;

    private MovieModel movieToPassForDetailsDisplay;

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

    public MovieModel getMovieToPassForDetailsDisplay() {
        return movieToPassForDetailsDisplay;
    }

    public void setMovieToPassForDetailsDisplay(MovieModel movieToPassForDetailsDisplay) {
        this.movieToPassForDetailsDisplay = movieToPassForDetailsDisplay;
    }
}
