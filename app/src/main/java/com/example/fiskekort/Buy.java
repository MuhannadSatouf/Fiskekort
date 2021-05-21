package com.example.fiskekort;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.fiskekort.LocalDB.LocalDatabaseAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Buy extends AppCompatActivity {

    private CheckBox oneDayButton;
    private CheckBox threeMonthButton;
    private CheckBox sixMonthButton;
    private CheckBox oneYearButton;
    public static Duration duration;
    private Location location;
    private EditText date;
    private DatePickerDialog datePickerDialog;
    private RadioButton radioBtnArea;
    private RadioButton radioBtnMun;
    public static FragmentTransaction ft;
    public static BuyFragment fragment;
    public static String lake_;


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

        Button btnProceed = (Button) findViewById(R.id.b_Proceed);
        date = (EditText) findViewById(R.id.date);
        date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(Buy.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
            System.out.println(date.getText());
        });

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        final RadioGroup rgMun = (RadioGroup) findViewById(R.id.rg_municipality);

        String[] municipalities = location.getAllMunicipalityNames();
        createRadioButton(municipalities, rgMun);

        radioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group, checkedId) -> {
            RadioButton radioBtn = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

            if (radioBtn.getText().equals("Municipality")) {
                if (ft != null){
                    ft.remove(fragment);
                }
                lake_ = "";
            } else {
                rgMun.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group1, checkedId1) -> {
                    lake_ = "";
                    RadioButton selected = (RadioButton) findViewById(rgMun.getCheckedRadioButtonId());
                    ft = getSupportFragmentManager().beginTransaction();
                    fragment = BuyFragment.newInstance(String.valueOf(selected.getText()));
                    ft.replace(R.id.option_buttons_lake, fragment);
                    ft.show(fragment);
                    ft.commit();
                });
            }
        });


        btnProceed.setOnClickListener((View.OnClickListener) v -> {
            radioBtnArea = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            radioBtnMun = (RadioButton) findViewById(rgMun.getCheckedRadioButtonId());
            String[] choices = new String[3];  // 0- area, 1 - mun, 2 - lake

            if (duration == null) {
                Toast.makeText(Buy.this, R.string.warning_missing_duration, Toast.LENGTH_SHORT).show();
            } else if (String.valueOf(date.getText()).isEmpty()) {
                Toast.makeText(Buy.this, R.string.warning_missing_date, Toast.LENGTH_SHORT).show();
            } else if (radioBtnMun == null) {
                Toast.makeText(Buy.this, R.string.warning_missing_municipality, Toast.LENGTH_SHORT).show();
            } else if (radioBtnArea.getText().equals("Single Lake")) {
                if ((lake_ == null) || (lake_.isEmpty())) {
                    Toast.makeText(Buy.this, R.string.warning_missing_lake, Toast.LENGTH_SHORT).show();
                } else {
                    choices[0] = String.valueOf(radioBtnArea.getText());
                    choices[1] = String.valueOf(radioBtnMun.getText());
                    choices[2] = lake_;

                    createLicense(choices, duration, String.valueOf(date.getText()));
                }
            } else if (radioBtnArea.getText().equals("Municipality")) {
                choices[0] = String.valueOf(radioBtnArea.getText());
                choices[1] = String.valueOf(radioBtnMun.getText());
                choices[2] = " ";

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
            endDate = date.plusDays(duration.getValue()).toString();

        } else {
            endDate = date.plusMonths(duration.getValue()).toString();
        }

        Price p = new Price();
        LocalDatabaseAdapter localDatabaseAdapter = new LocalDatabaseAdapter(getApplicationContext());

        FishingCard fc;
        if (choices[0].equals("Municipality")) {
            fc = new FishingCard(startDate, endDate, LocationType.MUNICIPALITY, new Municipality(choices[1]));
            p.getPrice(duration, LocationType.MUNICIPALITY);

        } else {
            fc = new FishingCard(startDate, endDate, LocationType.WATER, new Municipality(choices[1]), location.getLakeByMunAndName(choices[1], choices[2]));
            System.out.println(fc);
            p.getPrice(duration, LocationType.WATER);
        }
        localDatabaseAdapter.insertDataAsObject(fc);
        alert(fc, p);
    }

    private void alert(FishingCard fc, Price p) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Buy.this);
        dialog.setTitle("Confirmation");
        dialog.setMessage("License Order is created for " + fc + ". Price " + p);
        dialog.setNeutralButton("OK", (dialog1, which) -> {
            Intent intent = new Intent(Buy.this, Fishing_card_activity.class);
            startActivity(intent);
        });
        dialog.create().show();
    }

    private void cleanUpAndReturn() {
        if (radioBtnMun != null) {
            radioBtnMun.setSelected(false);
        }
        if (ft != null) {
            ft.remove(fragment);
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



    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(this, Fishing_card_activity.class));
        finish();

    }
}