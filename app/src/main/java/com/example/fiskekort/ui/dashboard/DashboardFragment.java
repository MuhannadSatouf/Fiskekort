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

import com.example.fiskekort.FishingCard;
import com.example.fiskekort.LocalDB.LocalDatabaseAdapter;
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
    private ImageView qrView;
    private Spinner ticketSpinner;
    private LocalDatabaseAdapter localDatabaseAdapter;
    private ArrayList<FishingCard> cardList = new ArrayList();
    private DashboardViewModel dashboardViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        qrView = root.findViewById(R.id.qr_code_iv);
        ticketSpinner = root.findViewById(R.id.ticketSpinner);
        localDatabaseAdapter = new LocalDatabaseAdapter(getContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.period, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        ticketSpinner.setAdapter(adapter);
        ticketSpinner.setOnItemSelectedListener(this);


        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        return root;
    }

    //private void viewData() {

    //    Cursor cursor = db.viewData();
    //    if(cursor.getCount()==0){

    //        Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
    //    }else{

    //        while (cursor.moveToNext()){
    //            cardList.add(cursor.getString(1));
    //        }

    //    }
    //}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        cardList = localDatabaseAdapter.getListOfData();
        //ArrayList<Bitmap> bitmapList = new ArrayList<>();
        MultiFormatWriter writer = new MultiFormatWriter();
        StringBuilder textToQr = new StringBuilder();
        FishingCard fishingCard = cardList.get(position);


        String cNum = fishingCard.getCardNumber();
        String startDate = fishingCard.getStartDate();
        String finishDate = fishingCard.getFinishDate();
        textToQr.append("\n" + cNum + "\n" + startDate + "\n" + finishDate + "\n");
        String printQr = textToQr.toString();

        try {
            BitMatrix matrix = writer.encode(printQr, BarcodeFormat.QR_CODE, 350, 350);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(matrix);
            qrView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}






