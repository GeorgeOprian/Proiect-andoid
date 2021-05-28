package com.example.cinehub.Movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingDTO {
    @SerializedName("ReservedSeats")
    @Expose
    private List<Integer> listOfReservedSeats;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("RunningId")
    @Expose
    private String runningId;

    public List<Integer> getListOfReservedSeats() {
        return listOfReservedSeats;
    }

    public void setListOfReservedSeats(List<Integer> listOfReservedSeats) {
        this.listOfReservedSeats = listOfReservedSeats;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRunningId() {
        return runningId;
    }

    public void setRunningId(String runningId) {
        this.runningId = runningId;
    }
}
