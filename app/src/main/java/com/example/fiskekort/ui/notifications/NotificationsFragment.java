package com.example.fiskekort.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.example.fiskekort.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements OnMapReadyCallback{
    MapView mapView;
    GoogleMap mMap;
    ArrayList<LatLng>lakeList = new ArrayList<>();
    String lakeName[];
    private NotificationsViewModel notificationsViewModel;

    LatLng Borringsjon= new LatLng(55.485911608606564, 13.315884787005805);
    LatLng Havgardssjon= new LatLng(55.484172960930216, 13.356868939878268);
    LatLng Fjallfotasjon = new LatLng(55.527719547851476, 13.301392783873526);
    LatLng Yddingesjön = new LatLng(55.54494055343786, 13.25420480600194);
    LatLng Krageholmssjon = new LatLng(55.501699999018236, 13.74415827458187);
    LatLng Ellestadsjön = new LatLng(55.531113651076694, 13.73342702616106);
    LatLng Snogholmssjon= new LatLng(55.561318731953286, 13.72372413304001);
    LatLng Sovdesjon = new LatLng(55.57578377109281, 13.662284067081533);
    LatLng Vombsjon = new LatLng(55.6850753685276, 13.589313707127603);
    LatLng Krankesjon = new LatLng(55.70007444895006, 13.479053600913856);
    LatLng OstraRingsjon = new LatLng(55.86019066425007, 13.558398750694453);
    LatLng VastraRingsjon= new LatLng(55.89254701796561, 13.462783369707653);
    LatLng Satoftasjon = new LatLng(55.89389461425486, 13.546725777791218);
    LatLng Finjasjon= new LatLng(56.1344074624344, 13.702793975891582);
    LatLng Kosen= new LatLng(56.78997919458905, 13.761014112231528);
    LatLng Hammarsjon =new LatLng (55.98000333995548, 14.221280450869578);
    LatLng Ivosjon= new LatLng (56.07518315049528, 14.411953319024981);
    LatLng Rabelovssjon = new LatLng (56.10041949978017, 14.232223867617401);
    LatLng Araslovsjon= new LatLng (56.06004298829865, 14.118069059078397);
    LatLng Immeln = new LatLng (56.280004474466324, 14.332962337525835);
    LatLng Vesljungasjon = new LatLng (56.42112909771548, 13.757748660467303);
    LatLng BronaSjo = new LatLng (56.42187103024562, 13.690213257532003);
    LatLng Skane = new LatLng(55,13);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        setMarker();



        lakeList.add(BronaSjo);
        lakeList.add(Vesljungasjon);
        lakeList.add(Immeln);
        lakeList.add(Araslovsjon);
        lakeList.add(Rabelovssjon);
        lakeList.add(Ivosjon);
        lakeList.add(Hammarsjon);
        lakeList.add(Kosen);
        lakeList.add(Finjasjon);
        lakeList.add(Satoftasjon);
        lakeList.add(VastraRingsjon);
        lakeList.add(OstraRingsjon);
        lakeList.add(Krankesjon);
        lakeList.add(Krageholmssjon);
        lakeList.add(Vombsjon);
        lakeList.add(Sovdesjon);
        lakeList.add(Snogholmssjon);
        lakeList.add(Ellestadsjön);
        lakeList.add(Yddingesjön);
        lakeList.add(Fjallfotasjon);
        lakeList.add(Havgardssjon);
        lakeList.add(Borringsjon);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {



            @Override
            public void onChanged(@Nullable String s) {

            }
        });
           return root;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
    mMap=googleMap;


    }


    public void setMarker (){

        for(int i=0; i<lakeList.size();i++){


            mMap.addMarker(new MarkerOptions().position(lakeList.get(i)));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(20.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Skane));
        }
    }


}