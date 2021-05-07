package com.example.cinehub.NavigationFragments;

import android.os.Bundle;
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

import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.R;
import com.example.cinehub.SearchMovieAction.MovieResultAdapter;
import com.example.cinehub.SearchMovieAction.OnSearchItemClickListener;
import com.example.cinehub.SearchMovieAction.Search;
import com.example.cinehub.SharedBetweenFragments;
import com.example.cinehub.databinding.FragmentRunningInTheatersBinding;
import com.example.cinehub.displayMovies.OnShowItemClickListener;
import com.example.cinehub.displayMovies.ShowMoviesAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RunningInTheatersFragment extends Fragment implements OnShowItemClickListener {

    private FragmentRunningInTheatersBinding dataBinding;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    private ShowMoviesAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_running_in_theaters, container, false);
        initAdapter();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Movies");
        Query query = reference.orderByChild("title");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                   List <MovieModel> movies = new ArrayList<>();
                   for (DataSnapshot snapshot1: snapshot.getChildren()) {
                       movies.add(snapshot1.getValue(MovieModel.class));

                   }
                    adapter.submitList(movies);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return  dataBinding.getRoot();
    }

    private void initAdapter(){
        adapter = new ShowMoviesAdapter(this);
        dataBinding.container.setLayoutManager(new GridLayoutManager(getContext(), 1));
        dataBinding.container.setAdapter(adapter);
    }

    @Override
    public void onItemClick(MovieModel movie) {
        SharedBetweenFragments.getInstance().setMovieToPassForDetailsDisplay(movie);
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.movieDetailsFragment);
        Toast.makeText(getContext(), movie.getTitle(), Toast.LENGTH_LONG).show();
    }
}
