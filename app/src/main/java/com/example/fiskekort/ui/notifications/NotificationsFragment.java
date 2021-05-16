package com.example.fiskekort.ui.notifications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fiskekort.Lake;
import com.example.fiskekort.Location;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.example.fiskekort.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    MapView mapView;
    GoogleMap mMap;
    ArrayList<Lake>lakeList = new ArrayList<>();




    private NotificationsViewModel notificationsViewModel;
    private Location lake = new Location();




    LatLng Skane = new LatLng(55,13);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        mapView= root.findViewById(R.id.mapView);

        MapsInitializer.initialize(getContext());


        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        lakeList = lake.getAllLakes();








        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        Button closeButton;


            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {


        mMap=googleMap;
        mMap.setOnMarkerClickListener(this);
        for(int i=0; i<lakeList.size();i++){

            LatLng tempLatLng = new LatLng(lakeList.get(i).getLatitude(),lakeList.get(i).getLongitude());

            mMap.addMarker(new MarkerOptions().position(tempLatLng).title(lakeList.get(i).getName()));

        }

        CameraUpdate cameraUpdate=CameraUpdateFactory.newLatLngZoom(Skane,5.0f);
        mMap.animateCamera(cameraUpdate);



    }


    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {





        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());





        builder.setMessage("For more information on this lake click this link");
        builder.setTitle(marker.getTitle());
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setCancelable(true);

        builder.show();



        return false;
    }
}