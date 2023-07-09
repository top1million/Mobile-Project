package com.example.labandroidproject.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.labandroidproject.Class.Course;
import com.example.labandroidproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class course_history extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    public course_history() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_history, container, false);
        TextView textView =  v.findViewById(R.id.textView);
        CollectionReference courses = FirebaseFirestore.getInstance().collection("courses");
        ArrayList<Course> coursesArrayList = new ArrayList<Course>();
        courses.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Course course = document.toObject(Course.class);
                        coursesArrayList.add(course);
                    }
                }
            }
        });

        Button showall = v.findViewById(R.id.showall);

        showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s ="";
                for(Course c : coursesArrayList){
                    s += c.getCourseTitle() + " "+c.getCourseMainTopics()+ " " + c.getPrerequisites() + "\n";
                    s += "--------------------------------------------------\n";
                }
                s+= "Total number of courses: " + coursesArrayList.size();
                textView.setText(s);
                textView.setVisibility(View.VISIBLE);
            }
        });


         return v;
    }
}