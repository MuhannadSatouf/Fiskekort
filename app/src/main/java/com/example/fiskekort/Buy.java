package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Date;

public class Buy extends AppCompatActivity {

    private Button oneDayButton;
    private Button threeMonthButton;
    private Button sixMonthButton;
    private Button oneYearButton;
    private Duration duration;
    private RadioGroup radioGroup;
    private RadioButton rbMunicipality;
    private RadioButton rbLake;
    private Button btnProceed;
    private DatePicker datePicker;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        location = new Location();
        setContentView(R.layout.activity_buy);
        oneDayButton = findViewById(R.id.oneDayButton);
        oneDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duration = Duration.ONE_DAY;
                oneDayButton.setPressed(true);
                threeMonthButton.setPressed(false);
                sixMonthButton.setPressed(false);
                oneYearButton.setPressed(false);
            }
        });
        threeMonthButton = findViewById(R.id.threeMonthButton);
        threeMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duration = Duration.THREE_MONTHS;
                oneDayButton.setPressed(false);
                threeMonthButton.setPressed(true);
                sixMonthButton.setPressed(false);
                oneYearButton.setPressed(false);
            }
        });

        sixMonthButton = findViewById(R.id.sixMonthButton);
        sixMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duration = Duration.SIX_MONTHS;
                oneDayButton.setPressed(false);
                threeMonthButton.setPressed(false);
                sixMonthButton.setPressed(true);
                oneYearButton.setPressed(false);
            }
        });

        oneYearButton = findViewById(R.id.yearButton);
        oneYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duration = Duration.YEAR;
                oneDayButton.setPressed(false);
                threeMonthButton.setPressed(false);
                sixMonthButton.setPressed(false);
                oneYearButton.setPressed(true);
            }
        });

        radioGroup=(RadioGroup)findViewById(R.id.radio_group);
        datePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        btnProceed=(Button)findViewById(R.id.b_Proceed);
        datePicker.setMinDate(System.currentTimeMillis());
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=radioGroup.getCheckedRadioButtonId();
                rbMunicipality=(RadioButton)findViewById(selectedId);
                //Toast.makeText(MainActivity.this,radioSexButton.getText(),Toast.LENGTH_SHORT).show();
                // get the values for day of month , month and year from a date picker
                String day = "Day = " + datePicker.getDayOfMonth();
                String month = "Month = " + (datePicker.getMonth() + 1);
                String year = "Year = " + datePicker.getYear();

                // display the values by using a toast
            }
        });

        String[] items = location.getAllMunicipalityNames();
    }



/*
    public void oneYear(){
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }*/


}