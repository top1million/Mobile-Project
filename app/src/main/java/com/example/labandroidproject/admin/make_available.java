package com.example.labandroidproject.admin;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labandroidproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class make_available extends Fragment {
    TextView courseStartDate;
    TextView registrationDeadline;
    public make_available() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_make_available, container, false);
        CollectionReference courses = FirebaseFirestore.getInstance().collection("courses");
        EditText instructorName = (EditText) v.findViewById(R.id.instructorName);
        // EditText registrationDeadline = (EditText) v.findViewById(R.id.registrationDeadline);
        Button insertStartDate = (Button) v.findViewById(R.id.courseStartDate);
        courseStartDate = (TextView) v.findViewById(R.id.showText);
        Button makeAvailable = (Button) v.findViewById(R.id.makeAvailable);
        registrationDeadline = (TextView) v.findViewById(R.id.showText_1);
        Button insertEndDate = (Button) v.findViewById(R.id.registrationDeadline);
        EditText courseVenue = (EditText) v.findViewById(R.id.courseVenue);
        List<String> courseNames = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, courseNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);


        insertEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker=new DatePickerDialog(make_available.this.getActivity(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        registrationDeadline.setText(String.valueOf(day) +"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
                    }
                }, 2023, 0, 15);

                datePicker.show();
            }
        });

        insertStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog();
            }
        });
        courses.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String courseName = document.getString("courseTitle");
                        courseNames.add(courseName);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
        makeAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = spinner.getSelectedItem().toString();
                String instructor = instructorName.getText().toString();
                String registration = registrationDeadline.getText().toString();
                String startDate = courseStartDate.getText().toString();
                String venue = courseVenue.getText().toString();
                courses.whereEqualTo("courseTitle", courseName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String id = document.getId();
                                    courses.document(id).update("instructor", instructor);
                                    courses.document(id).update("registrationDeadline", registration);
                                    courses.document(id).update("courseStartDate", startDate);
                                    courses.document(id).update("courseVenue", venue);
                                    courses.document(id).update("available", true);
                                }
                            }
                    }
                });
                Toast.makeText(getActivity(), "Course is now available", Toast.LENGTH_SHORT).show();
                replaceFragment(new admin_home_page());
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

    private  void OpenDialog(){
        DatePickerDialog datePicker=new DatePickerDialog(this.getActivity(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                courseStartDate.setText(String.valueOf(day) +"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
            }
        }, 2023, 0, 15);

        datePicker.show();
    }

}