package com.example.labandroidproject.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.labandroidproject.Class.Instructor;
import com.example.labandroidproject.Class.Trainee;
import com.example.labandroidproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class students_instructors extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_students_instructors, container, false);
        Spinner spinner = v.findViewById(R.id.spinner);
        CollectionReference users = db.collection("users_test");
        TextView textView =  v.findViewById(R.id.textView2);
        if(spinner.getSelectedItem().toString().equals("Students")){
            ArrayList<Trainee> trainees = new ArrayList<>();
            users.whereEqualTo("role","Trainee").get().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Trainee trainee = document.toObject(Trainee.class);
                        trainees.add(trainee);
                        textView.setText(trainee.getFirstName()+" "+trainee.getLastName()+"\n");

                    }
                }
            });
        }
        else{
            ArrayList<Instructor> instructors = new ArrayList<>();
            users.whereEqualTo("role","Instructor").get().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Instructor instructor = document.toObject(Instructor.class);
                        instructors.add(instructor);
                        textView.setText(instructor.getFirstName()+" "+instructor.getLastName()+"\n");
                    }

                }
            });
        }




        return v;
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getParentFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}