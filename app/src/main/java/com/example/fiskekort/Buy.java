package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiskekort.LocalDB.LocalDatabaseAdapter;
import com.example.fiskekort.LocalDB.LocalDatabaseHelper;

import java.util.Arrays;
import java.util.Date;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class Buy extends AppCompatActivity {

    private Button oneDayButton;
    private Button threeMonthButton;
    private Button sixMonthButton;
    private Button oneYearButton;
    private Duration duration;
    private RadioButton rbMunicipality;
    private RadioButton rbLake;
    private Button btnProceed;
    private Location location;
    private EditText date;
    DatePickerDialog datePickerDialog;
    String selectedDate = "";

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


        btnProceed = (Button) findViewById(R.id.b_Proceed);
        date = (EditText) findViewById(R.id.date);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Buy.this,
                        (view, year, monthOfYear, dayOfMonth) -> {
                            // set day of month , month and year value in the edit text
                            date.setText(dayOfMonth + "/"
                                    + (monthOfYear) + "/" + year);
                            //here create a variable
                            selectedDate = (String.valueOf(year)).concat("-").concat(String.valueOf(monthOfYear)).concat("-").concat(String.valueOf(dayOfMonth));

                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        final RadioGroup rgMun = (RadioGroup) findViewById(R.id.rg_municipality);
        final RadioGroup rgLake = (RadioGroup) findViewById(R.id.rg_lake);
        final TextView hiddenText_mun = (TextView) findViewById(R.id.invisible_mun);

        String[] municipalities = location.getAllMunicipalityNames();
        createRadioButton(municipalities, rgMun);

        radioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group, checkedId) -> {
            RadioButton radioBtn = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

            if (radioBtn.getText().equals("Municipality")) {
                rgMun.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group1, checkedId1) -> {
                    rgLake.setVisibility(View.GONE);
                    hiddenText_mun.setText("");

                    RadioButton selected0 = (RadioButton) findViewById(rgMun.getCheckedRadioButtonId());
                    hiddenText_mun.setText(selected0.getText());
                });
            } else {
                rgLake.setVisibility(VISIBLE);
                rgMun.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group12, checkedId12) -> {
                    hiddenText_mun.setText("");
                    RadioButton selected = (RadioButton) findViewById(rgMun.getCheckedRadioButtonId());
                    hiddenText_mun.setText(selected.getText());

                    String[] lakes = location.getLakesNamesByArea(String.valueOf(hiddenText_mun.getText()));
                    createRadioButton(lakes, rgLake);
                });
            }

        });


        btnProceed.setOnClickListener((View.OnClickListener) v -> {

            RadioButton radioBtnArea = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            RadioButton radioBtnMun = (RadioButton) findViewById(rgMun.getCheckedRadioButtonId());
            RadioButton radioBtnLake = (RadioButton) findViewById(rgLake.getCheckedRadioButtonId());
            String[] choises = new String[3];  // 0- area, 1 - mun, 2 - lake

            if (String.valueOf(date.getText()).isEmpty()) {
                Toast.makeText(Buy.this, R.string.warning_missing_date, Toast.LENGTH_SHORT).show();
            } else if (radioBtnMun == null) {
                //  System.out.println(String.valueOf(date.getText()));
                Toast.makeText(Buy.this, R.string.warning_missing_municipality, Toast.LENGTH_SHORT).show();
            } else if (radioBtnArea.getText().equals("Single Lake")) {
                if (radioBtnLake == null) {
                    Toast.makeText(Buy.this, R.string.warning_missing_lake, Toast.LENGTH_SHORT).show();
                } else {
                    //FishingCard(String startDate, String finishDate, LocationType locationType, Municipality municipality, Lake lake)
                    choises[0] = String.valueOf(radioBtnArea.getText());
                    choises[1] = String.valueOf(radioBtnMun.getText());
                    choises[2] = String.valueOf(radioBtnLake.getText());

                    Log.e("array: ", Arrays.toString(choises));


                    Municipality municipality = new Municipality(radioBtnMun.getText().toString());
                    //Need to get the lake from UI
                    Lake lake = new Lake("Bröna Sjö", 56.42187103024562, 13.690213257532003);
                    LocationType locationType = LocationType.WATER;

                    //Create a card and save it in the LocalDatabase.
                    FishingCard fishingCard = new FishingCard(selectedDate, "20202020", locationType, municipality, lake);
                    LocalDatabaseAdapter localDatabaseAdapter = new LocalDatabaseAdapter(getApplicationContext());
                    localDatabaseAdapter.insertDataAsObject(fishingCard);

                    // Create a toast to check if the card is created.
                    String data = localDatabaseAdapter.getDataAsObject();
                    create_toast(this, data);

                }
            } else if (radioBtnArea.getText().equals("Municipality")) {
                choises[0] = String.valueOf(radioBtnArea.getText());
                choises[1] = String.valueOf(radioBtnMun.getText());
                choises[2] = " ";


                Municipality municipality = new Municipality(radioBtnMun.getText().toString());
                LocationType locationType = LocationType.MUNICIPALITY;

                //Need to get the lake from UI
                Lake lake = new Lake("Bröna Sjö", 56.42187103024562, 13.690213257532003);

                FishingCard fishingCard = new FishingCard(selectedDate, "20202020", locationType, municipality, lake);

                //Create a card and save it in the LocalDatabase.
                LocalDatabaseAdapter localDatabaseAdapter = new LocalDatabaseAdapter(getApplicationContext());
                localDatabaseAdapter.insertDataAsObject(fishingCard);

                // Create a toast to check if the card is created.
                String data = localDatabaseAdapter.getDataAsObject();
                create_toast(this, data);

                Log.e("array: ", Arrays.toString(choises));
            }

        });

    }


/*
    public void oneYear(){
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }*/

    private void createRadioButton(String[] strings, RadioGroup rgMun) {
        rgMun.removeAllViewsInLayout();
        for (int i = 0; i < strings.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            if (i == 0) {
                radioButton.setSelected(true);
            }
            radioButton.setText(strings[i]);
            radioButton.setId(i);
            rgMun.addView(radioButton);
        }
    }

    public void create_toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}