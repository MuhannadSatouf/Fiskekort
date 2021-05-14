package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Buy extends AppCompatActivity {

    private Button oneDayButton;
    private Button threeMonthButton;
    private Button sixMonthButton;
    private Button oneYearButton;
    private Duration duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }



/*
    public void oneYear(){
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }*/


}