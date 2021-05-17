package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fiskekort.LocalDB.LocalDatabaseAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fishing_card_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String startDate;
    private String finishDate;
    private Button create_fishing_card_button;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private Spinner spinner;
    private LocalDatabaseAdapter localDatabaseAdapter;
    private Button buyButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing_card_activity);
        initialize();



        //To the SluDataBase
        Button btn = (Button) findViewById(R.id.SluDataBasebt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Fishing_card_activity.this, SLUDataBase.class);
                Fishing_card_activity.this.startActivity(myIntent);
            }


        });

        buyButton = findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy();
            }
        });
    }

    public void buy() {
        Intent intent = new Intent(this, Buy.class);
        startActivity(intent);
    }


    public void initialize() {

        localDatabaseAdapter = new LocalDatabaseAdapter(this);

    }



    //This method use for Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    //This method use for Spinner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void create_toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}