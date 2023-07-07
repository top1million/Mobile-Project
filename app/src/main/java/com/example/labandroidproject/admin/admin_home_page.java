package com.example.labandroidproject.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.labandroidproject.R;
import com.example.labandroidproject.admin.Create_new_course;
import com.example.labandroidproject.admin.applications_control;
import com.example.labandroidproject.admin.course_history;
import com.example.labandroidproject.admin.edit_delete_course;
import com.example.labandroidproject.admin.make_available;
import com.example.labandroidproject.admin.students_instructors;

public class admin_home_page extends Fragment {

    public admin_home_page(){
        // require a empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_home_page, container, false);

        Button create_course = (Button) v.findViewById(R.id.create_course);
        Button edit_delete_course = (Button) v.findViewById(R.id.edit_delete_course);
        Button make_available = (Button) v.findViewById(R.id.make_available);
        Button applications_control = (Button) v.findViewById(R.id.applications_control);
        Button students_instructors = (Button) v.findViewById(R.id.students_instructors);
        Button course_history = (Button) v.findViewById(R.id.course_history);

        create_course.setOnClickListener(view -> {
            replaceFragment(new Create_new_course());
        });
        edit_delete_course.setOnClickListener(view -> {
            replaceFragment(new edit_delete_course());
        });
        make_available.setOnClickListener(view -> {
            replaceFragment(new make_available());
        });
        applications_control.setOnClickListener(view -> {
            replaceFragment(new applications_control());
        });
        students_instructors.setOnClickListener(view -> {
            replaceFragment(new students_instructors());
        });
        course_history.setOnClickListener(view -> {
            replaceFragment(new course_history());
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