package com.example.cinehub.NavigationFragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.R;
import com.example.cinehub.SharedBetweenFragments;
import com.example.cinehub.databinding.FragmentMovieDetailsBinding;

public class MovieDetailsFragment extends Fragment {

    private FragmentMovieDetailsBinding dataBinding;
    private Button bookTicketsButton;
    private MovieModel movie;
    private TextView movieTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        movie = SharedBetweenFragments.getInstance().getMovieToPassForDetailsDisplay();

        initLayoutElements();

        movieTitle.setText(movie.getTitle());
        return dataBinding.getRoot();
    }

    private void initLayoutElements() {
        movieTitle = dataBinding.movieTitle;

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