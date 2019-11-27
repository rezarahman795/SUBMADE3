package com.example.mainactivity.view;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mainactivity.R;
import com.example.mainactivity.adapter.SerialTVAdapter;
import com.example.mainactivity.detail.SerialTVDetailActivity;
import com.example.mainactivity.model.SerialTV;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SerialTVfragment extends Fragment {

    private RecyclerView rv_SerialTV;
    private ArrayList<SerialTV> listSerialTV = new ArrayList<>();


    public SerialTVfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serial_tvfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        rv_SerialTV = view.findViewById(R.id.rv_tv_serial);
        rv_SerialTV.setHasFixedSize(true);

        listSerialTV.addAll(getListSerialTV());

        showRecyclerSerialTV();
    }

    public ArrayList<SerialTV> getListSerialTV() {
        String[]dataNameSerial =getResources().getStringArray(R.array.data_name_serial);
        String[]dataTglSerial=getResources().getStringArray(R.array.data_tgl_serial);
        TypedArray imgSerial= getResources().obtainTypedArray(R.array.data_picture_serial);
        String[]dataCrewSerial = getResources().getStringArray(R.array.data_name_crew_serial);
        String[]dataCrewDetailSerial = getResources().getStringArray(R.array.detail_position_serial);
        String[]dataDescSerial= getResources().getStringArray(R.array.data_description_serial);

        ArrayList<SerialTV> listDataSerialTV = new ArrayList<>();

        for (int i = 0 ; i<imgSerial.length();i++){
            SerialTV dataSerialTVfg = new SerialTV();
            dataSerialTVfg.setNamaSerial(dataNameSerial[i]);
            dataSerialTVfg.setTglSerial(dataTglSerial[i]);
            dataSerialTVfg.setDescSerial(dataDescSerial[i]);
            dataSerialTVfg.setCrewSerial(dataCrewSerial[i]);
            dataSerialTVfg.setCrewDetailSerial(dataCrewDetailSerial[i]);
            dataSerialTVfg.setPicture(imgSerial.getResourceId(i,-1));

            listDataSerialTV.add(dataSerialTVfg);

        }
        return listDataSerialTV;

    }

    private void showRecyclerSerialTV() {
        rv_SerialTV.setLayoutManager(new LinearLayoutManager(getContext()));
        SerialTVAdapter serialTVAdapter= new SerialTVAdapter(listSerialTV);
        rv_SerialTV.setAdapter(serialTVAdapter);

        serialTVAdapter.setOnItemClickCallback(new SerialTVAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(SerialTV dataSerial) {
                showSelectedSerialTV(dataSerial);
            }
        });
    }

    private void showSelectedSerialTV(SerialTV detailDataSerialTV) {
        Intent serialTVintent = new Intent(getContext(), SerialTVDetailActivity.class);
        serialTVintent.putExtra("GET_DATA_SERIAL_TV", detailDataSerialTV);
        startActivity(serialTVintent);
        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }


}
