package com.example.cinehub.NavigationFragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cinehub.API.ServerAPIBuilder;
import com.example.cinehub.Movie.MovieDTO;
import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.R;
import com.example.cinehub.SharedBetweenFragments;
import com.example.cinehub.databinding.FragmentRunningDetailsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RunningDetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentRunningDetailsBinding dataBinding;
    private ArrayAdapter<CharSequence> adapter;
    private Spinner spinner;
    private Button addToDbButton;



    private Button dateButton;
    private Button timeButton;
    private DatePickerDialog datePickerDialog;

    private TimePickerDialog timePickerDialog;


    private boolean dateWasPicked = false;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private MovieModel movie = SharedBetweenFragments.getInstance().getMovieToAddDisplayData();

    private MovieDTO movieDTO;
    private LocalDate datePicked;
    private LocalTime timePicked;
    private String hallPicked;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_running_details, container, false);

        initSpinner();
        initDatePickerButton();
        initTimePickerButton();


        MovieModel movie = SharedBetweenFragments.getInstance().getMovieToAddDisplayData();
        dataBinding.runningDetailsMovieTitle.setText(movie.getTitle());

        addToDbButton = dataBinding.addToDbButton;
        initAddToDbButton();

//        Toast.makeText(getContext(), movie.getTitle() + "was added to data base", Toast.LENGTH_LONG).show();

        return dataBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initDatePickerButton() {
        initDatePicker();
        dateButton = dataBinding.datePickerButton;
        dateButton.setText(getTodaysDate());
        initDateButtonClickAction();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initTimePickerButton() {
        initTimePicker();
        timeButton = dataBinding.timePickerButton;
        initTimeButtonClickAction();
    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String monthString = "";
                if (month < 10) {
                    monthString = "0" + month;
                } else {
                    monthString = "" + month;
                }

                String dayString = "";
                if (day < 10) {
                    dayString = "0" + day;
                } else {
                    dayString = "" + day;
                }


                String dateAsString = year + "-" + monthString + "-" + dayString;

                datePicked = LocalDate.parse(dateAsString);
                dateWasPicked = true;

                String date = makeDateString(day, month, year);
                Toast.makeText(getContext(), "data selectata " + datePicked, Toast.LENGTH_LONG).show();
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        datePicked = LocalDate.now();

    }

    private String makeDateString(int day, int month, int year)
    {
        return  day +" " + getMonthFormat(month)+ " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker()
    {
        datePickerDialog.show();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                String hourString = "";
                if (selectedHour < 10) {
                    hourString = "0" + selectedHour;
                } else {
                    hourString = "" + selectedHour;
                }

                String minuteString = "";
                if (selectedMinute < 10) {
                    minuteString = "0" + selectedMinute;
                } else {
                    minuteString = "" + selectedMinute;
                }

                timePicked = LocalTime.parse(hourString + ":" + minuteString);
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
                Toast.makeText(getContext(), "ora selectata " + timePicked, Toast.LENGTH_LONG).show();
            }
        };
        int style = AlertDialog.THEME_HOLO_LIGHT;
        timePicked = LocalTime.parse("12:00");
        initTimePickerDialog(onTimeSetListener, style);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initTimePickerDialog(TimePickerDialog.OnTimeSetListener onTimeSetListener, int style) {
        timePickerDialog = new TimePickerDialog(getContext(), style, onTimeSetListener, timePicked.getHour(), timePicked.getMinute(), true);
    }

    public void openTimePiker () {
        timePickerDialog.show();
    }


    private void initSpinner(){
        spinner = dataBinding.hallNumber;
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.halls_numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void initDateButtonClickAction () {
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });
    }

    private void initTimeButtonClickAction () {
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePiker();
            }
        });
    }


    private void initAddToDbButton() {
        addToDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMovieToDB();

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("Movies");
//                reference.child(movie.getImdbID()).setValue(movie);

                //o verificare ca filmul nu e in db

//                Toast.makeText(getContext(), movie.getTitle() + "was added to data base", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendMovieToDB() {
        movieDTO = createMovieDTO(movie);
        Call<MovieDTO>call = ServerAPIBuilder.getInstance().addMovie(movieDTO);
        call.enqueue(new Callback<MovieDTO>() {
            @Override
            public void onResponse(Call<MovieDTO> call, Response<MovieDTO> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Response Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getContext(), "Movie was successfully sent to the server", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MovieDTO> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        hallPicked =  parent.getItemAtPosition(position).toString();
//        Toast.makeText(getContext(), hallPicked, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private MovieDTO createMovieDTO(MovieModel current) {
        MovieDTO dto = new MovieDTO();
        dto.setImdbID(current.getImdbID());
        dto.setTitle(current.getTitle());
        dto.setReleased(current.getReleased());
        dto.setDuration(current.getRuntime());
        dto.setGenre(current.getGenre());
        dto.setDirector(current.getDirector());
        dto.setWriter(current.getWriter());
        dto.setActors(current.getActors());
        dto.setPlot(current.getPlot());
        dto.setLanguage(current.getLanguage());
        dto.setAwards(current.getAwards());
        dto.setPoster(current.getPoster());
        dto.setRatings(current.getRatings());
        dto.setImdbRating(current.getImdbRating());
        dto.setBoxOffice(current.getBoxOffice());

        dto.setRunningDate(datePicked.toString());
        dto.setRunningTime(timePicked.toString());
        dto.setHallNumber(hallPicked);

        return dto;
    }
}