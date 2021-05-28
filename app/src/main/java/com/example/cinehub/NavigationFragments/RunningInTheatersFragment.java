package com.example.cinehub.NavigationFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cinehub.API.ServerAPIBuilder;
import com.example.cinehub.Movie.MovieDTO;
import com.example.cinehub.Movie.GetMoviesDTO;
import com.example.cinehub.R;
import com.example.cinehub.SharedBetweenFragments;
import com.example.cinehub.databinding.FragmentRunningInTheatersBinding;
import com.example.cinehub.displayMovies.OnShowItemClickListener;
import com.example.cinehub.displayMovies.ShowMoviesAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RunningInTheatersFragment extends Fragment implements OnShowItemClickListener {

    private FragmentRunningInTheatersBinding dataBinding;

    private ShowMoviesAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_running_in_theaters, container, false);
        initAdapter();

        getMoviesFromDataBase();
        return  dataBinding.getRoot();
    }

    private void initAdapter(){
        adapter = new ShowMoviesAdapter(this);
        dataBinding.container.setLayoutManager(new GridLayoutManager(getContext(), 1));
        dataBinding.container.setAdapter(adapter);
    }

    private void getMoviesFromDataBase() {
        Call<GetMoviesDTO> call = ServerAPIBuilder.getInstance().getMovies(); //just for tests

        call.enqueue(new Callback<GetMoviesDTO>() {
            @Override
            public void onResponse(Call<GetMoviesDTO> call, Response<GetMoviesDTO> response) {
                if (response.code() == 200) {
//                    Log.v("ServerReqSucceded", response.body().toString());
                    adapter.submitList(response.body().getMoviesList());
                } else if (response.code() == 404){
                    Log.v("ServerReqFailed", "No data found");
                } else {
                    Log.v("ServerReqFailed", "There where some problems with the query");
                }
            }

            @Override
            public void onFailure(Call<GetMoviesDTO> call, Throwable t) {
                Log.v("ServerReqFailed", t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(MovieDTO movie) {
        SharedBetweenFragments.getInstance().setMovieToPassForDetailsDisplay(movie);
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.movieDetailsFragment);
        Toast.makeText(getContext(), movie.getTitle(), Toast.LENGTH_LONG).show();
    }
}
