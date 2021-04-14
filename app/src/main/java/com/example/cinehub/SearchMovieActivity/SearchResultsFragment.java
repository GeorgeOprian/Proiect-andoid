package com.example.cinehub.SearchMovieActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cinehub.R;
import com.example.cinehub.Shared.SearchMovieSharedData;
import com.example.cinehub.databinding.FragmentSearchResultsBinding;
import com.example.cinehub.databinding.SearchMovieActivityBinding;

import java.util.List;

public class SearchResultsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private MovieResultAdapter adapter;
    private FragmentSearchResultsBinding dataBinding;
    private SearchMovieSharedData searchMovieSharedData = SearchMovieSharedData.getInstance();

    public SearchResultsFragment() {
        super(R.layout.fragment_search_results);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //it's time to draw to view

        dataBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search_results, container, false);

        View view = dataBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
//
        adapter.submitList(searchMovieSharedData.getSearchResults());//lista pasata de activitate
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    private void initAdapter(){
        adapter = new MovieResultAdapter();
        dataBinding.container.setLayoutManager(new GridLayoutManager(getContext(), 1));
        dataBinding.container.setAdapter(adapter);
    }

    public void setSearchMovieSharedData (List<Search> searchResults) {

    }
}
