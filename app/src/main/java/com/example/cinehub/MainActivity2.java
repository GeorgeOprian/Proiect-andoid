package com.example.cinehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinehub.API.APIBuilder;

import com.example.cinehub.SearchMovieActivity.SearchResults;
import com.example.cinehub.SearchMovieActivity.SearchResultsFragment;
import com.example.cinehub.Shared.SearchMovieSharedData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private SearchResults searchResults;

    //layout elements
    private Button searchMovieButton;
    private EditText searchInput;

    private SearchMovieSharedData searchMovieSharedData = SearchMovieSharedData.getInstance();
    private SearchResultsFragment searchResultsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie_activity);



        searchMovieButton = findViewById(R.id.search_movie_button);
        searchInput = findViewById(R.id.search_movie_input);
//        searchResultsText = findViewById(R.id.search_results);

        searchMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieTitle = searchInput.getText().toString();
                if (movieTitle.equals("")) {
                    showNoMovieTitleEnteredMessage();
                } else {
                    searchMovieByTitle(movieTitle.trim());
                }
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.api_search_results, SearchResultsFragment.class, null)
                    .commit();
        }
    }

    private void searchMovieByTitle(String name) {
        Call<SearchResults> call = APIBuilder.getInstance().searchMoviesByTitle(APIBuilder.API_KEY, name);
        call.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                searchResults = response.body();
//                searchMovieSharedData.setSearchResults(searchResults.getSearch());
                searchResultsFragment.setSearchMovieSharedData(searchResults.getSearch());


                //pasam la fragment

//                searchResultsText.setText(searchResults.getSearch().get(0).toString());
//                Log.v("Tag", "########################################");
//                Log.v("Tag", searchResults.getTotalResults());
//                Log.v("Tag", "########################################");
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                showNoMovieTitleEnteredMessage();
            }
        });
    }

//    private void searchMovieByTitle(String name) {
//        Call<MovieModel> call = APIBuilder.getInstance().getMovie(APIBuilder.API_KEY, name);
//        call.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//                foundMovie = response.body();
//                Log.v("Tag", "########################################");
//                Log.v("Tag", foundMovie.getTitle());
//                Log.v("Tag", "########################################");
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//                int x;
//            }
//        });
//    }
//
    private void showNoMovieTitleEnteredMessage() {
        Toast.makeText(this, getString(R.string.errors_executing_query), Toast.LENGTH_LONG).show();
    }

    private void showErrorExecutingQuery() {
        Toast.makeText(this, getString(R.string.enter_movie_title), Toast.LENGTH_LONG).show();
    }


}