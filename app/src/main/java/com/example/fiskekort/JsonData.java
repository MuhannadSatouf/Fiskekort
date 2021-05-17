package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonData extends AppCompatActivity {
    ArrayList<String> jsonData = new ArrayList<>();
    ListView listViewFish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_data);

        listViewFish = findViewById(R.id.listviewFish);
            getTjörnarpsjön();
    }public void getTjörnarpsjön(){
        String json;


        try {
            InputStream is = getAssets().open("Tjörnarpasjön.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);


            for (int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString("Län").equals("Skåne Län")){
                    jsonData.add("Datum " + jsonObject.getString("Datum"));
                    jsonData.add("Art: " + jsonObject.getString("Art"));
                    jsonData.add("Antal: " + jsonObject.getString("Antal"));
                    jsonData.add("Vikt: " + jsonObject.getString("Vikt"));

                    System.out.println(jsonData);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,jsonData);
                if (listViewFish != null){
                    listViewFish.setAdapter(adapter);
                }
            }



        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


        //Toast.makeText(getApplicationContext(),jsonData.toString(),Toast.LENGTH_LONG).show();

    }
}