package com.example.cinehub.displayMovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.R;

import com.example.cinehub.databinding.ShowMovieCardBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowMoviesAdapter extends RecyclerView.Adapter<ShowMoviesAdapter.MovieViewHolder> {

    private List<MovieModel> localDataSet;
    public static OnShowItemClickListener itemClickListener;


    public ShowMoviesAdapter(OnShowItemClickListener listener) {
        itemClickListener = listener;
    }

    public void submitList(List<MovieModel> dataSet) {
        localDataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShowMoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowMoviesAdapter.MovieViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.show_movie_card, //de schimbat
                parent,
                false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ShowMoviesAdapter.MovieViewHolder holder, int position) {
        holder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        if(localDataSet == null)
            return 0;
        return localDataSet.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ShowMovieCardBinding binding;

        public MovieViewHolder(ShowMovieCardBinding binding) { //de schimbat layoutul
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieModel item){
            binding.title.setText(item.getTitle());
            binding.year.setText(item.getYear());
            binding.imdbRating.setText(item.getImdbRating());
            Picasso.get().load(item.getPoster()).into(binding.image);

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(item);
                }
            });
        }

    }

}