package com.example.fiskekort.license;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.satouf.fiskekort.R;




import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LicenceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LicenceFragment extends Fragment implements LicenseAdapter.OnRecyclerItemClick {

    private RecyclerView recyclerView;
    private LicenseAdapter licenseAdapter;
    private List<LicenseModel> licenseModelList;

    private String names[] = { "Skane License", "Vastland License", "Blekinge License", "Central region License", "Norge License" };
    private String desc[] = { "This is a license for Fishing in Skane Region", "This is a license for Fishing in Vastland Region", "This is a license for Fishing in Blekinge Region", "This is a license for Fishing in Central region", "This is a license for Fishing in  Norge region" };
    private String ids[] = { "License #1", "License #2", "License #3", "License #4", "License #5 " };

    public LicenceFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static LicenceFragment newInstance() {
        LicenceFragment fragment = new LicenceFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_licence, container, false);
        recyclerView = view.findViewById(R.id.licence_recycler);
        licenseModelList = new ArrayList<>(10);
        loadHardCodedValues();
        licenseAdapter = new LicenseAdapter(licenseModelList, getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(licenseAdapter);

        licenseAdapter.setOnRecyclerItemClick(this);

        return view;
    }

    private void loadHardCodedValues() {
        for(byte i = 0; i < names.length; i++){
            licenseModelList.add(new LicenseModel()
                    .setName(names[i])
                    .setDesc(desc[i])
                    .setId(ids[i])
            );
        }
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getContext(), "Position clicked is: " + position, Toast.LENGTH_SHORT).show();
    }
}