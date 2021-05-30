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

import com.example.cinehub.API.ServerAPIBuilder;
import com.example.cinehub.R;
import com.example.cinehub.SharedBetweenFragments;
import com.example.cinehub.databinding.FragmentDeleteMovieBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
                    deleteMovieRequest(movieTitle);
                }
            }

        });
    }

    private void showNoMovieTitleEnteredMessage() {
        Toast.makeText(getContext(), getString(R.string.errors_executing_query), Toast.LENGTH_LONG).show();
    }

    private void deleteMovieRequest(String movieTitle) {
        Call<Void> call = ServerAPIBuilder.getInstance().deleteMovie(movieTitle);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    if (response.code() == SharedBetweenFragments.BOOKINGS_LINKED_TO_MOVIE) {
                        Toast.makeText(getContext(), "You can't delete this movie because it has bookings linked to it.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(), "Response Code: " + response.code(), Toast.LENGTH_LONG).show();
                    }
                    return;
                }
                if (response.code() == SharedBetweenFragments.RESOURCE_NOT_FOUND) {
                    Toast.makeText(getContext(), "Movie was not found in the database", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(), "Movie was deleted successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}