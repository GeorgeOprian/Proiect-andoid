package com.example.cinehub;

import com.example.cinehub.Movie.MovieDTO;
import com.example.cinehub.Movie.MovieModel;

public class SharedBetweenFragments {

    private static SharedBetweenFragments instance;
    private MovieModel movieToAddDisplayData;

    private MovieDTO movieToPassForDetailsDisplay;

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
}
