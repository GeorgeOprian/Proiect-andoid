package com.example.cinehub.NavigationFragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cinehub.Movie.MovieDTO;
import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.R;
import com.example.cinehub.SharedBetweenFragments;
import com.example.cinehub.databinding.FragmentBookTicketBinding;
import com.example.cinehub.databinding.FragmentBookingsBinding;


public class BookTicketFragment extends Fragment {

    private FragmentBookTicketBinding dataBinding;
    private MovieDTO movie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_ticket, container, false);
        movie = SharedBetweenFragments.getInstance().getMovieToPassForDetailsDisplay();
        return dataBinding.getRoot();
    }
}