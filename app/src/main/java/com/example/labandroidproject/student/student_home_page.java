package com.example.labandroidproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.labandroidproject.AccommodationActivity;
import com.example.labandroidproject.CalendarActivity;
import com.example.labandroidproject.MarksActivity;
import com.example.labandroidproject.R;

public class FirstFragment extends Fragment {


    public FirstFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Find the buttons by their IDs
        Button button11 = view.findViewById(R.id.button11);
        Button button12 = view.findViewById(R.id.button12);
        Button button13 = view.findViewById(R.id.button13);
        Button button14 = view.findViewById(R.id.button14);
        Button button15 = view.findViewById(R.id.button15);


        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when button11 is clicked
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when button11 is clicked
                Intent intent = new Intent(getActivity(), MarksActivity.class);
                startActivity(intent);
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccommodationActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}