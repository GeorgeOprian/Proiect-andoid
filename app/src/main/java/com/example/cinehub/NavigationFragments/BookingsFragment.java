package com.example.cinehub.NavigationFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cinehub.API.ServerAPIBuilder;
import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.R;
import com.example.cinehub.databinding.FragmentBookingsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingsFragment extends Fragment {

    private FragmentBookingsBinding dataBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookings, container, false);
        Call<MovieModel> call = ServerAPIBuilder.getInstance().getMovies(); //just for tests

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code() == 200) {
                    Log.v("ServerReqSucceded", response.body().toString());
                } else if (response.code() == 404){
                    Log.v("ServerReqFailed", "No data found");
                } else {
                    Log.v("ServerReqFailed", "There where some problems with the query");
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.v("ServerReqFailed", t.getMessage());
            }
        });
//        call.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//
//                if (response.code() == 200) {
//                    Log.v("ServerReqSucceded", response.body().toString());
//                } else if (response.code() == 404){
//                    Log.v("ServerReqFailed", "No data found");
//                } else {
//                    Log.v("ServerReqFailed", "There where some problems with the query");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//                Log.v("ServerReqFailed", t.getMessage());
//            }
//        });
        return  dataBinding.getRoot();
    }
}
