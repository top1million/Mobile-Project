package com.example.labandroidproject.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.*;
import androidx.fragment.app.Fragment;

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
        button.setOnClickListener();
        return view;
    }
}