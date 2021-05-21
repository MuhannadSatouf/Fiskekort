package com.example.fiskekort.ui.dashboard;

import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fiskekort.LocalDB.LocalDatabaseAdapter;
import com.example.fiskekort.QRView.QRAdapter;
import com.example.fiskekort.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DashboardFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private EditText textInput;
    private Button qrButton;
    private ImageView qe_ImageView;
    private Spinner ticketSpinner;
    private LocalDatabaseAdapter localDatabaseAdapter;
    private ArrayList<String> cardList = new ArrayList();
    private DashboardViewModel dashboardViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter programAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<String> kommuns;
    private ArrayList<String> descriptions;
    private ArrayList<String> expiredDate;
    private ArrayList<Bitmap> images = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        localDatabaseAdapter = new LocalDatabaseAdapter(getContext());
        qe_ImageView = root.findViewById(R.id.qe_ImageView);

        fillArray();
        try {
            //convertStringToDate(expiredDate);
            generateImages();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        recyclerView = root.findViewById(R.id.qrRecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        if (images.size() > 0) {
            programAdapter = new QRAdapter(getContext(), kommuns, descriptions, images);
            recyclerView.setAdapter(programAdapter);
        }

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), s -> {

        });

        return root;
    }

    public void fillArray() {
        LocalDatabaseAdapter localDatabaseAdapter = new LocalDatabaseAdapter(getContext());
        kommuns = localDatabaseAdapter.getKommun();
        descriptions = localDatabaseAdapter.getDescription();
        expiredDate = localDatabaseAdapter.getExpiredDate();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void generateImages() throws ParseException {
        for (int j = 0; j < cardList.size(); j++) {
            compareDate(convertStringToDate(expiredDate));
        }
        cardList = localDatabaseAdapter.getAllCards();
        MultiFormatWriter writer = new MultiFormatWriter();
        for (int i = 0; i < cardList.size(); i++) {
            String printQr = cardList.get(i);
            try {


                BitMatrix matrix = writer.encode(printQr, BarcodeFormat.QR_CODE, 350, 350);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(matrix);
                images.add(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<LocalDate> convertStringToDate(ArrayList<String> expiredDate) {
        ArrayList<LocalDate> convertedDate = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");


        for (int i = 0; i < expiredDate.size(); i++) {

            dateTimeFormatter = dateTimeFormatter.ofPattern("yyyy-M-d");
            String s = expiredDate.get(i);

            LocalDate localDate = LocalDate.parse(s, dateTimeFormatter);
            System.out.println(localDate);

            System.out.println(expiredDate.get(i) + " " + dateTimeFormatter);
            convertedDate.add(localDate);
        }

        return convertedDate;
    }

    public void compareDate(ArrayList<LocalDate> convertedDate) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getCurrentDateTime = sdf.format(c.getTime());
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        //String getCurrentDateTime = dateTimeFormatter.format((TemporalAccessor) c.getTime());


        for (int i = 0; i < convertedDate.size(); i++) {
            System.out.println(getCurrentDateTime + " " + String.valueOf(convertedDate));
            LocalDate date = convertedDate.get(i);
            if (getCurrentDateTime.compareTo(String.valueOf(date)) < 0) {
                String s = String.valueOf(convertedDate.get(i));


                Log.d("Return", "getMyTime older than getCurrentDateTime ");


            } else if (getCurrentDateTime.compareTo(String.valueOf(date)) > 0) {
                localDatabaseAdapter.delete(String.valueOf(convertedDate.get(i)));
                Log.d("Return", "getMyTime newer than getCurrentDateTime ");
            }
            Log.d("Return", "getMyTime Same ");
        }
    }
}






