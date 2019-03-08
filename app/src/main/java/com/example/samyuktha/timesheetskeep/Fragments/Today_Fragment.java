package com.example.samyuktha.timesheetskeep.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samyuktha.timesheetskeep.Controller;
import com.example.samyuktha.timesheetskeep.R;

public class Today_Fragment extends Fragment {

    TextView mTodaysDate;
    Controller controller = new Controller();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_today, container, false);
        mTodaysDate = (TextView) view.findViewById(R.id.dateField);
        mTodaysDate.setText(controller.getTodaysDate());
        return view;
    }

}
