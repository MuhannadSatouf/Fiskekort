package com.example.fiskekort;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fiskekort.LocalDB.LocalDatabaseAdapter;

public class Fishing_card_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private LocalDatabaseAdapter localDatabaseAdapter;
    private Button buyButton, fishData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing_card_activity);
        initialize();

        fishData = findViewById(R.id.fishData);
        fishData.setOnClickListener(v -> {
            Intent fishIntent = new Intent(Fishing_card_activity.this, JsonData.class);
            Fishing_card_activity.this.startActivity(fishIntent);
        });

        //To the SluDataBase
        Button btn = (Button) findViewById(R.id.SluDataBasebt);
        btn.setOnClickListener(v -> {
            Intent myIntent = new Intent(Fishing_card_activity.this, SLUDataBase.class);
            Fishing_card_activity.this.startActivity(myIntent);
        });

        buyButton = findViewById(R.id.buyButton);
        buyButton.setOnClickListener(v -> buy());
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, HomeActivity.class));
        finish();

    }
}