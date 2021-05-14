package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
    private Location location;
    EditText date;
    DatePickerDialog datePickerDialog;

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
        btnProceed=(Button)findViewById(R.id.b_Proceed);
        date = (EditText) findViewById(R.id.date);

        String selectedDate = "";
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
                            selectedDate.concat(String.valueOf(year)).concat("-").concat(String.valueOf( monthOfYear)).concat("-").concat(String.valueOf( dayOfMonth));

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        final RadioGroup rgMun = (RadioGroup) findViewById(R.id.rg_munitipality);
        String[] municipalities = location.getAllMunicipalityNames();
        for (int i = 0; i < municipalities.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(municipalities[i]);
            radioButton.setId(i);
            rgMun.addView(radioButton);
        }

        //set listener to radio button group
        rgMun.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = rgMun.getCheckedRadioButtonId();
                RadioButton radioBtn = (RadioButton) findViewById(checkedRadioButtonId);
                Toast.makeText(Buy.this, radioBtn.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=radioGroup.getCheckedRadioButtonId();
                rbMunicipality=(RadioButton)findViewById(selectedId);
                Toast.makeText(Buy.this,rbMunicipality.getText(),Toast.LENGTH_SHORT).show();



            }
        });


    }



/*
    public void oneYear(){
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }*/

    private RadioGroup createRadioButton(String[] strings) {
        final RadioButton[] rb = new RadioButton[5];
        RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
        for(int i=0; i<strings.length; i++){
            rb[i]  = new RadioButton(this);
            rb[i].setText(" " + strings[i] + " ");
            rb[i].setId(i + 100);
            rg.addView(rb[i]);
        }
     //   ll.addView(rg);//you add the whole RadioGroup to the layout
        return rg;
    }
}