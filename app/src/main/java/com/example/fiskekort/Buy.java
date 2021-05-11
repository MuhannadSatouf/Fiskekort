package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Buy extends AppCompatActivity {

    private Button oneDayButton;
    private Button oneMonthButton;
    private Button sixMonthButton;
    private Button oneYearButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        oneDayButton = findViewById(R.id.oneDayButton);
        oneDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneday();
            }
        });
        oneMonthButton = findViewById(R.id.oneMonthButton);
        oneMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneMonth();
            }
        });

        sixMonthButton = findViewById(R.id.sixMonthButton);
        sixMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sixMonth();
            }
        });

        oneYearButton = findViewById(R.id.yearButton);
        oneYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneYear();
            }
        });
    }

    public void oneday(){
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }

    public void oneMonth(){
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }

    public void sixMonth(){
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }

    public void oneYear(){
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }


}