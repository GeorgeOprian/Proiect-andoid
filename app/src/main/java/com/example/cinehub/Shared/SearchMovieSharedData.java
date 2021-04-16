package com.example.cinehub.Shared;



import com.example.cinehub.SearchMovieAction.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieSharedData {
    private List<Search> searchResults;

    private static SearchMovieSharedData instance;

    private SearchMovieSharedData () {
        searchResults = new ArrayList<>();
    }


    public static SearchMovieSharedData getInstance () {
        if (instance == null) {
            instance = new SearchMovieSharedData();
        }
        return instance;
    }

    public List<Search> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Search> searchResults) {
        this.searchResults = searchResults;
    }
}
