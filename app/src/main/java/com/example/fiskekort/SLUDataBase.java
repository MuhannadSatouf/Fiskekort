package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SLUDataBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_l_u_data_base);

        Button ShowDatabaseButton = (Button) findViewById(R.id.ShowDataSlubt);

        ShowDatabaseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.slu.se/institutioner/akvatiska-resurser/databaser/databas-for-sjoprovfiske-nors/"));
                startActivity(intent);
            }
        });



        Button ShowTheMapButton = (Button) findViewById(R.id.ShowSLUmap);

        ShowTheMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://dvfisk.slu.se"));
                startActivity(intent);
            }
        });


        Button ShowTheTableButton = (Button) findViewById(R.id.showTableBt);

        ShowTheTableButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://norssers-api.slu.se"));
                startActivity(intent);
            }
        });


    }
    }