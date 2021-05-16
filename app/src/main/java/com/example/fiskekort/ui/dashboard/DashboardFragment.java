package com.example.fiskekort.ui.dashboard;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fiskekort.FishingCard;
import com.example.fiskekort.LocalDB.LocalDatabaseAdapter;
import com.example.fiskekort.QRView.QRAdapter;
import com.example.fiskekort.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

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
    private ArrayList<Bitmap> images = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        localDatabaseAdapter = new LocalDatabaseAdapter(getContext());
        qe_ImageView = root.findViewById(R.id.qe_ImageView);

        fillArray();
        generateImages();
        recyclerView = root.findViewById(R.id.qrRecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        programAdapter = new QRAdapter(getContext(), kommuns, descriptions, images);
        recyclerView.setAdapter(programAdapter);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), s -> {

        });

        return root;
    }

    public void fillArray() {
        LocalDatabaseAdapter localDatabaseAdapter = new LocalDatabaseAdapter(getContext());
        kommuns = localDatabaseAdapter.getKommun();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        cardList = localDatabaseAdapter.getAllCards();
        MultiFormatWriter writer = new MultiFormatWriter();
        StringBuilder textToQr = new StringBuilder();

        for (int i = 0; i < cardList.size(); i++) {
            textToQr.append(cardList.get(i));
            String printQr = textToQr.toString();
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


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void generateImages() {

        cardList = localDatabaseAdapter.getAllCards();
        MultiFormatWriter writer = new MultiFormatWriter();
        for (int i = 0; i < cardList.size(); i++) {
            String printQr = cardList.get(i).toString();
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
}






