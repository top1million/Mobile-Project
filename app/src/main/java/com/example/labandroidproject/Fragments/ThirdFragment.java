package com.example.labandroidproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.*;
import androidx.fragment.app.Fragment;

import com.example.labandroidproject.AccommodationActivity;
import com.example.labandroidproject.CalendarActivity;
import com.example.labandroidproject.R;

public class ThirdFragment extends Fragment {

    public ThirdFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third,container,false);
        Button button = view.findViewById(R.id.button2);
        Button button2 = view.findViewById(R.id.button3);
        ImageView imageView = view.findViewById(R.id.imageView3);
        imageView.setImageResource(R.drawable.chip);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when button11 is clicked
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when button11 is clicked
                Intent intent = new Intent(getActivity(), AccommodationActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}