package com.example.fiskekort.ui.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.fiskekort.LocalDB.*;
import com.example.fiskekort.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class DashboardFragment extends Fragment {
    private EditText textInput;
    private Button qrButton;
    private ImageView qrView;

    private DashboardViewModel dashboardViewModel ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textInput=root.findViewById(R.id.inputText);
        qrButton=root.findViewById(R.id.qr_button);
        qrView=root.findViewById(R.id.qr_code_iv);




        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inText = textInput.getText().toString().trim();

                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix matrix = writer.encode(inText, BarcodeFormat.QR_CODE,350,350);

                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

                    Bitmap bitmap = barcodeEncoder.createBitmap(matrix);


                    qrView.setImageBitmap(bitmap);



                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}