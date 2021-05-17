package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiskekort.LocalDB.LocalDatabaseAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import static android.view.View.VISIBLE;

public class Buy extends AppCompatActivity {

    private CheckBox oneDayButton;
    private CheckBox threeMonthButton;
    private CheckBox sixMonthButton;
    private CheckBox oneYearButton;
    public static Duration duration;
    private Button btnProceed;
    private Location location;
    private EditText date;
    DatePickerDialog datePickerDialog;
    RadioButton radioBtnArea;
    RadioButton radioBtnMun;
    RadioButton radioBtnLake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        location = new Location();
        setContentView(R.layout.activity_buy);

        oneDayButton = findViewById(R.id.oneDayButton);
        oneDayButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                duration = Duration.ONE_DAY;
                threeMonthButton.setChecked(false);
                sixMonthButton.setChecked(false);
                oneYearButton.setChecked(false);
            }
        });
        threeMonthButton = findViewById(R.id.threeMonthButton);
        threeMonthButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                duration = Duration.THREE_MONTHS;
                oneDayButton.setChecked(false);
                sixMonthButton.setChecked(false);
                oneYearButton.setChecked(false);
            }
        });
        sixMonthButton = findViewById(R.id.sixMonthButton);
        sixMonthButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                duration = Duration.SIX_MONTHS;
                oneDayButton.setChecked(false);
                threeMonthButton.setChecked(false);
                oneYearButton.setChecked(false);
            }
        });
        oneYearButton = findViewById(R.id.yearButton);
        oneYearButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                duration = Duration.YEAR;
                oneDayButton.setChecked(false);
                threeMonthButton.setChecked(false);
                sixMonthButton.setChecked(false);
            }
        });

        btnProceed = (Button) findViewById(R.id.b_Proceed);
        date = (EditText) findViewById(R.id.date);


        date.setOnClickListener(v -> {
            // calender class's instance and get current date , month and year from calender
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            datePickerDialog = new DatePickerDialog(Buy.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // set day of month , month and year value in the edit text
                        //   date.setText(dayOfMonth + "-"
                        //           + (monthOfYear+1) + "-" + year);
                        date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        //here create a variable
                        //      selectedDate.concat(String.valueOf(year)).concat("-").concat(String.valueOf(monthOfYear+1)).concat("-").concat(String.valueOf(dayOfMonth));  //yyyy/mm/dd

                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
            System.out.println(date.getText());
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
                rgMun.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group12, checkedId12) -> {
                    rgLake.setVisibility(View.GONE);
                    hiddenText_mun.setText("");

                    RadioButton selected0 = (RadioButton) findViewById(rgMun.getCheckedRadioButtonId());
                    hiddenText_mun.setText(selected0.getText());
                });
            } else {
                rgLake.setVisibility(VISIBLE);
                rgMun.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group1, checkedId1) -> {
                    hiddenText_mun.setText("");
                    RadioButton selected = (RadioButton) findViewById(rgMun.getCheckedRadioButtonId());
                    hiddenText_mun.setText(selected.getText());

                    String[] lakes = location.getLakesNamesByArea(String.valueOf(hiddenText_mun.getText()));
                    createRadioButton(lakes, rgLake);
                });
            }

        });


        btnProceed.setOnClickListener((View.OnClickListener) v -> {

            radioBtnArea = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            radioBtnMun = (RadioButton) findViewById(rgMun.getCheckedRadioButtonId());
            radioBtnLake = (RadioButton) findViewById(rgLake.getCheckedRadioButtonId());
            String[] choices = new String[3];  // 0- area, 1 - mun, 2 - lake

            if (duration == null) {
                Toast.makeText(Buy.this, R.string.warning_missing_duration, Toast.LENGTH_SHORT).show();
            } else if (String.valueOf(date.getText()).isEmpty()) {
                Toast.makeText(Buy.this, R.string.warning_missing_date, Toast.LENGTH_SHORT).show();
            } else if (radioBtnMun == null) {
                Toast.makeText(Buy.this, R.string.warning_missing_municipality, Toast.LENGTH_SHORT).show();
            } else if (radioBtnArea.getText().equals("Single Lake")) {
                if (radioBtnLake == null) {
                    Toast.makeText(Buy.this, R.string.warning_missing_lake, Toast.LENGTH_SHORT).show();
                } else {
                    choices[0] = String.valueOf(radioBtnArea.getText());
                    choices[1] = String.valueOf(radioBtnMun.getText());
                    choices[2] = String.valueOf(radioBtnLake.getText());
                    Log.e("array: ", Arrays.toString(choices));

                    createLicense(choices, duration, String.valueOf(date.getText()));

                }
            } else if (radioBtnArea.getText().equals("Municipality")) {
                choices[0] = String.valueOf(radioBtnArea.getText());
                choices[1] = String.valueOf(radioBtnMun.getText());
                choices[2] = " ";
                Log.e("array: ", Arrays.toString(choices));

                createLicense(choices, duration, String.valueOf(date.getText()));

            }

            cleanUpAndReturn();
        });


    }

    private void createLicense(String[] choices, Duration duration, String startDate) {
        final Calendar c = Calendar.getInstance();
        String endDate = "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(startDate, formatter);

        c.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());

        if (duration == Duration.ONE_DAY) {
            c.add(Calendar.DAY_OF_MONTH, duration.getValue());

        } else {
            c.add(Calendar.MONTH, duration.getValue());
        }
        Price p = new Price();
        endDate = "".concat(String.valueOf(c.get(Calendar.YEAR)).concat("-").concat(String.valueOf(c.get(Calendar.MONTH))).concat("-").concat(String.valueOf(c.get(Calendar.DAY_OF_MONTH))));
        LocalDatabaseAdapter localDatabaseAdapter = new LocalDatabaseAdapter(getApplicationContext());
        FishingCard fc;
        if (choices[0].equals("Municipality")) {
            System.out.println(startDate + " " + endDate + " " + LocationType.MUNICIPALITY + " " + new Municipality(choices[1]));
            fc = new FishingCard(startDate, endDate, LocationType.MUNICIPALITY, new Municipality(choices[1]));
            p.getPrice(duration, LocationType.MUNICIPALITY);
            localDatabaseAdapter.insertDataAsObject(fc);
            alert(fc, p);

        } else {
            fc = new FishingCard(startDate, endDate, LocationType.WATER, new Municipality(choices[1]), location.getLakeByMunAndName(choices[1], choices[2]));
            System.out.println(fc);
            p.getPrice(duration, LocationType.WATER);
            localDatabaseAdapter.insertDataAsObject(fc);
            alert(fc, p);
        }
    }

    private void alert(FishingCard fc, Price p) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Buy.this);
        dialog.setTitle("Confirmation");
        dialog.setNeutralButton("OK", null).create();
        dialog.setMessage("License Order is created for " + fc + ". Price " + p);
        dialog.create().show();

        dialog.setNegativeButton("OK", (dialog1, which) -> {
            Intent intent = new Intent(Buy.this, Fishing_card_activity.class);
            startActivity(intent);

        });


    }

    private void cleanUpAndReturn() {
        if (radioBtnMun != null) {
            radioBtnMun.setSelected(false);
        }
        if (radioBtnLake != null) {
            radioBtnLake.setSelected(false);
        }
        oneDayButton.setChecked(false);
        threeMonthButton.setChecked(false);
        sixMonthButton.setChecked(false);
        oneYearButton.setChecked(false);
        date.setText("");
    }


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
}