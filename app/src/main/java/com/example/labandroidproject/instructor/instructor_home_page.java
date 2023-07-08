package com.example.labandroidproject.instructor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.labandroidproject.R;

public class instructor_home_page extends Fragment {

    public instructor_home_page(){
        // require a empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_instructor_home_page, container, false);
        Button one = (Button) v.findViewById(R.id.one);
        Button two = (Button) v.findViewById(R.id.two);
        Button three = (Button) v.findViewById(R.id.three);
        Button four = (Button) v.findViewById(R.id.four);
        one.setOnClickListener(view -> {
            replaceFragment(new taughtcourses());
        });
        return v;
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getParentFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}