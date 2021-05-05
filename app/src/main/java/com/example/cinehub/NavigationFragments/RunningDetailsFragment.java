package com.example.cinehub.NavigationFragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
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
import android.widget.Toast;

import com.example.cinehub.Movie.MovieModel;
import com.example.cinehub.R;
import com.example.cinehub.SharedBetweenFragments;
import com.example.cinehub.databinding.FragmentRunningDetailsBinding;

import java.util.Calendar;


public class RunningDetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentRunningDetailsBinding databinding;
    private ArrayAdapter<CharSequence> adapter;
    private Spinner spinner;
    private Button addToDbButton;
    private String spinnerValue;
    private Button dateButton;
    private DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_running_details, container, false);

//        initSpinner();
        initDatePickerButton();
        Toast.makeText(getContext(), "data selectata " + dateButton.getText(), Toast.LENGTH_LONG).show();

        MovieModel movie = SharedBetweenFragments.getInstance().getMovieToAddDisplayData();
        databinding.runningDetailsMovieTitle.setText(movie.getTitle());

        addToDbButton = databinding.addToDbButton;
        initButtonClickAction();

//        Toast.makeText(getContext(), movie.getTitle() + "was added to data base", Toast.LENGTH_LONG).show();

        return databinding.getRoot();
    }

    private void initDatePickerButton() {
        initDatePicker();
        dateButton = databinding.datePickerButton;
        dateButton.setText(getTodaysDate());
        initDateButtonClickAction();
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

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
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

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

//    private void initSpinner(){
//        spinner = databinding.daysOfWeekSpinner;
//        adapter = ArrayAdapter.createFromResource(getContext(), R.array.days_of_week, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);
//    }

    private void initDateButtonClickAction () {
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(v);
            }
        });
    }

    private void initButtonClickAction () {
        addToDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerValue =  parent.getItemAtPosition(position).toString();
        Toast.makeText(getContext(), spinnerValue, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}