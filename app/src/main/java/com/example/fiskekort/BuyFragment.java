package com.example.fiskekort;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

public class BuyFragment extends Fragment {
    private String mun;
    public static String[] lakes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mun = getArguments().getString("mun", "");
        Location location = new Location();
        lakes = location.getLakesNamesByArea(mun);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buy, container, false);
        RadioGroup rgLake = (RadioGroup) rootView.findViewById(R.id.rg_lake);
        Location location = new Location();
        lakes = location.getLakesNamesByArea(mun);

        for (int i = 0; i < lakes.length; i++) {
            RadioButton radioButton = new RadioButton(getActivity());
            if (i == 0) {
                radioButton.setSelected(true);
            }
            radioButton.setText(lakes[i]);
            radioButton.setId(i);
            rgLake.addView(radioButton);
        }

        rgLake.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group12, checkedId12) -> {
            RadioButton radioBtn0 = (RadioButton) rootView.findViewById(rgLake.getCheckedRadioButtonId());
            Buy.lake_ = (String) radioBtn0.getText();
        });

        return rootView;
    }

    public static BuyFragment newInstance(String lakeName) {
        BuyFragment fragment = new BuyFragment();
        Bundle args = new Bundle();
        args.putString("mun", lakeName);
        fragment.setArguments(args);
        return fragment;
    }
}
