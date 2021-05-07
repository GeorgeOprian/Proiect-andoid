package com.example.cinehub.database;


import androidx.annotation.NonNull;

import com.example.cinehub.Movie.MovieModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MoviesDao {

    private static MoviesDao instance;

    private static FirebaseDatabase rootNode;
    private static DatabaseReference moviesReference;
    private MovieModel foundMovieByTitle;
    private String returnMessage;

    private MoviesDao() {
        rootNode = FirebaseDatabase.getInstance();
        moviesReference = rootNode.getReference("Movies");
    }

    public static MoviesDao getInstance() {
        if (instance == null) {
            instance = new MoviesDao();
        }
        return instance;
    }

    public String searchMovieByTitle(String title) {
        Query query = moviesReference.orderByChild("title").startAt(title).endAt(title + "\\uf8ff");
        query.addValueEventListener(new ValueEventListener() { //addListenerForSingleValueEvent merge cu asta si for
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1: snapshot.getChildren()) {
                        foundMovieByTitle = snapshot1.getValue(MovieModel.class);
//                        snapshot1.getRef().removeValue();
                        returnMessage = foundMovieByTitle.getTitle() + " was succesfully deleted.";
                        break;
                    }
                    int x = 0;
                     x++;
                } else {
                    returnMessage = foundMovieByTitle.getTitle() + " was not found in the database.";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    returnMessage = "The delete operation was canceled.";
            }
        });
        return returnMessage;
    }

}
