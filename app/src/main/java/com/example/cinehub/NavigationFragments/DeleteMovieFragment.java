package com.example.cinehub.NavigationFragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinehub.R;
import com.example.cinehub.database.MoviesDao;
import com.example.cinehub.databinding.FragmentDeleteMovieBinding;

public class DeleteMovieFragment extends Fragment {

    private FragmentDeleteMovieBinding dataBinding;
    private Button searchButton;
    private EditText searchInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_delete_movie, container, false);

        searchInput = dataBinding.searchMovieInput;
        initSearchButton();

        return dataBinding.getRoot();

    }

    private void initSearchButton() {
        searchButton = dataBinding.searchMovieButton;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieTitle = searchInput.getText().toString();
                if (movieTitle.equals("")) {
                    showNoMovieTitleEnteredMessage();
                } else {
                    String returnMessage = MoviesDao.getInstance().searchMovieByTitle(movieTitle);
                    Toast.makeText(getContext(), returnMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showNoMovieTitleEnteredMessage() {
        Toast.makeText(getContext(), getString(R.string.errors_executing_query), Toast.LENGTH_LONG).show();
    }
}