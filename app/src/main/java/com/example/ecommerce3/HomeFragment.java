package com.example.ecommerce3;

import android.hardware.lights.LightState;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerce3.adapters.PopularAdapters;
import com.example.ecommerce3.models.PopularModel;

import java.util.List;


public class HomeFragment extends Fragment {

    //popular items
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}