package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    private int period;
    private boolean spinnerSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        spinner = findViewById(R.id.spinner);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing_card_activity);
        initialize();
        createSpinner();

        //localDatabaseAdapter.deleteAllRows();

        create_fishing_card_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinnerSelected == false) {
                    create_toast(Fishing_card_activity.this, "Please select a period first ");
                } else {
                    createCard();
                }
            }
        });
    }

    public void initialize() {
        create_fishing_card_button = findViewById(R.id.create_card_button);
        localDatabaseAdapter = new LocalDatabaseAdapter(this);

    }


    public void createSpinner() {
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.period, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    //The card number will be unique.
    public void createCard() {

        if (spinner.getSelectedItemPosition() < 0) {
            create_toast(this, "Please select the period first");
        } else {

            long id = localDatabaseAdapter.insertData(startDate(), finishDateForOneMonth());
            if (id <= 0) {
                create_toast(this, "Insertion Unsuccessful");
            } else {
                create_toast(this, "Insertion successful");
            }

            //To show at there is a card for debugging
            String data = localDatabaseAdapter.getData();
            create_toast(this, data);
        }
    }


    public String startDate() {

        calendar = Calendar.getInstance();

        simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        return startDate = simpleDateFormat.format(calendar.getTime());
    }

    public String finishDateForOneMonth() {
        calendar.add(Calendar.DATE, period);
        return finishDate = simpleDateFormat.format(calendar.getTime());
    }

    //This method use for Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerSelected = true;
        String choice = parent.getItemAtPosition(position).toString();

        if (position == 0) {
            period = 30;
        }
        if (position == 1) {
            period = 180;
        }
        if (position == 2) {
            period = 360;
        }
    }

    //This method use for Spinner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void create_toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}