package com.example.cinehub.SearchMovieAction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cinehub.R;
import com.example.cinehub.Shared.SearchMovieSharedData;
import com.example.cinehub.databinding.FragmentSearchResultsBinding;

import java.util.List;
/*

nu mai am nevoie dea ea
 */



public class SearchResultsFragment extends Fragment implements OnSearchItemClickListener {

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
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
//
        adapter.submitList(searchMovieSharedData.getSearchResults());//lista pasata de activitate
    }


    private void initAdapter(){
        adapter = new MovieResultAdapter(this);
        dataBinding.container.setLayoutManager(new GridLayoutManager(getContext(), 1));
        dataBinding.container.setAdapter(adapter);
    }

    public void setSearchMovieSharedData (List<Search> searchResults) {
        searchMovieSharedData.setSearchResults(searchResults);
    }

    @Override
    public void onItemClick(Search search) {

    }
}
