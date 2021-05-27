package com.example.cinehub.NavigationFragments;

import android.media.Image;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cinehub.Movie.MovieDTO;
import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.R;
import com.example.cinehub.SharedBetweenFragments;
import com.example.cinehub.databinding.FragmentMovieDetailsBinding;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {

    private FragmentMovieDetailsBinding dataBinding;
    private Button bookTicketsButton;
    private MovieDTO movie;
    private TextView movieTitle;
    private TextView released_date;
    private TextView runtime;
    private TextView plot;
    private TextView imdb_rating;
    private TextView actors;
    private  TextView rotten;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        movie = SharedBetweenFragments.getInstance().getMovieToPassForDetailsDisplay();

        initLayoutElements();

        movieTitle.setText(movie.getTitle());
        released_date.setText(movie.getReleased());
        runtime.setText(movie.getRunningTime());
        plot.setText(movie.getPlot());
        imdb_rating.setText(movie.getRatings().get(0).getValue());
        actors.setText(movie.getActors());
        rotten.setText(movie.getRatings().get(1).getValue());

        Picasso.get().load(movie.getPoster()).into(dataBinding.image);

        return dataBinding.getRoot();
    }

    private void initLayoutElements() {
        movieTitle = dataBinding.movieTitle;
        released_date = dataBinding.releasedDate;
        runtime = dataBinding.runtime;
        plot = dataBinding.plot;
        imdb_rating = dataBinding.imdbRating;
        actors = dataBinding.actors;
        rotten = dataBinding.rotten;

        initBookingsButton();
    }

    private void initBookingsButton() {
        bookTicketsButton = dataBinding.bookTickets;
        bookTicketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.bookTicketFragment);
            }
        });
    }
}