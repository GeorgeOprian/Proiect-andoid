package com.example.cinehub.NavigationFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cinehub.R;
import com.example.cinehub.databinding.FragmentSearchMovieBinding;
import com.example.cinehub.databinding.FragmentSearchResultsBinding;

public class SearchMovieFragment extends Fragment {

    private FragmentSearchMovieBinding databinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_movie, container, false);

        return  databinding.getRoot();
    }
}

/*
public class WhatchedMoviesFragment extends Fragment {

    private FragmentWatchedMoviesBinding databinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_watched_movies, container, false);

        return  databinding.getRoot();
    }
}
 */
