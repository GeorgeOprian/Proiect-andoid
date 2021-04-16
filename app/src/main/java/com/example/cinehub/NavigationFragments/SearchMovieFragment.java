package com.example.cinehub.NavigationFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cinehub.API.APIBuilder;
import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.R;
import com.example.cinehub.SearchMovieAction.MovieResultAdapter;
import com.example.cinehub.SearchMovieAction.OnItemClickListener;
import com.example.cinehub.SearchMovieAction.Search;
import com.example.cinehub.SearchMovieAction.SearchResults;
import com.example.cinehub.databinding.FragmentSearchMovieBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieFragment extends Fragment implements OnItemClickListener {

    private FragmentSearchMovieBinding databinding;
    private Button searchButton;
    private EditText searchInput;
    private MovieResultAdapter adapter;

    private SearchResults searchResults;
    private MovieModel movie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_movie, container, false);

        searchInput = databinding.searchMovieInput;
        searchButton = databinding.searchMovieButton;
        initAdapter();

        searchButton.setOnClickListener(new View.OnClickListener() {
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
        return  databinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void searchMovieByTitle(String title) {
        Call<SearchResults> call = APIBuilder.getInstance().searchMoviesByTitle(APIBuilder.API_KEY, title);
        call.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                searchResults = response.body();
//                searchMovieSharedData.setSearchResults(searchResults.getSearch());
//                searchResultsFragment.setSearchMovieSharedData(searchResults.getSearch());
                adapter.submitList(searchResults.getSearch());

                //pasam la fragment

//                searchResultsText.setText(searchResults.getSearch().get(0).toString());
                Log.v("Tag", "########################################");
                Log.v("Tag", searchResults.getTotalResults());
                Log.v("Tag", "########################################");
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                showNoMovieTitleEnteredMessage();
            }
        });
    }

    private void loadMovieByIDMBId(String id) {
        Call<MovieModel> call = APIBuilder.getInstance().getMovieByIMDBId(APIBuilder.API_KEY, id);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                movie = response.body();

                //insert in baza de date


//                searchResultsText.setText(searchResults.getSearch().get(0).toString());
//                Log.v("Tag", "########################################");
//                Log.v("Tag", movie.getGenre());
//                Log.v("Tag", "########################################");
//

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                showNoMovieTitleEnteredMessage();
            }
        });
    }

    @Override
    public void onItemClick(Search search) {
//        String text = "click pe film in fragment";//search.getTitle();
//        Log.v("TAG", text);

        loadMovieByIDMBId(search.getImdbID());;

    }


    private void showNoMovieTitleEnteredMessage() {
        Toast.makeText(getContext(), getString(R.string.errors_executing_query), Toast.LENGTH_LONG).show();
    }

    private void showErrorExecutingQuery() {
        Toast.makeText(getContext(), getString(R.string.enter_movie_title), Toast.LENGTH_LONG).show();
    }

    private void initAdapter(){
        adapter = new MovieResultAdapter(this);
        databinding.container.setLayoutManager(new GridLayoutManager(getContext(), 1));
        databinding.container.setAdapter(adapter);
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
