package com.example.labandroidproject.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.labandroidproject.MarksActivity;
import com.example.labandroidproject.R;
import com.example.labandroidproject.admin.Create_new_course;
import com.example.labandroidproject.admin.applications_control;
import com.example.labandroidproject.admin.course_history;
import com.example.labandroidproject.admin.edit_delete_course;
import com.example.labandroidproject.admin.make_available;
import com.example.labandroidproject.admin.students_instructors;

public class student_home_page extends Fragment {

    public student_home_page(){
        // require a empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_student_home_page, container, false);

        Button sign_for_course = v.findViewById(R.id.sign_for_course);
        Button view_schedule = v.findViewById(R.id.view_schedule);
        Button view_marks = v.findViewById(R.id.view_marks);


        sign_for_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new sign_for_course());
            }
        });

        view_marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MarksActivity.class);
                startActivity(intent);
            }
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