package com.example.cinehub.NavigationFragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cinehub.API.ServerAPIBuilder;
import com.example.cinehub.Movie.BookingDTO;
import com.example.cinehub.Movie.MovieDTO;
import com.example.cinehub.R;
import com.example.cinehub.SharedBetweenFragments;
import com.example.cinehub.databinding.FragmentDisplayBookingBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayBookingFragment extends Fragment {

    private FragmentDisplayBookingBinding dataBinding;
    private BookingDTO booking;
    private Button deleteBookingButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_display_booking, container, false);

        booking = SharedBetweenFragments.getInstance().getBookingToBeDisplayed();

        initLayout();

        return dataBinding.getRoot();
    }

    private void initLayout() {
        dataBinding.movieTitle.setText(booking.getMovieTitle());
        Picasso.get().load(booking.getPoster()).into(dataBinding.mooviePoster);
        dataBinding.runningDate.setText(booking.getRunningDate());
        dataBinding.runningTime.setText(booking.getRunningTime());
        dataBinding.listOfReserverdSeats.setText(booking.getListOfReservedSeats().toString());
        dataBinding.bookingId.setText(booking.getBookingId());
        initDeleteMovieButton();
    }

    private void initDeleteMovieButton() {
        deleteBookingButton = dataBinding.deleteBookingButton;
        deleteBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBooking(booking.getBookingId());
            }
        });
    }

    private void deleteBooking(String bookingId) {
        Call<Void> call = ServerAPIBuilder.getInstance().deleteBooking(bookingId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Response Code: " + response.code(), Toast.LENGTH_LONG).show();
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.bookingsFragment);
                    return;
                }
                if (response.code() == SharedBetweenFragments.RESOURCE_NOT_FOUND) {
                    Toast.makeText(getContext(), "Booking was not found in the database", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getContext(), "Booking was deleted successfully", Toast.LENGTH_LONG).show();
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.bookingsFragment);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.bookingsFragment);
            }
        });
    }
}