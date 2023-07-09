package com.example.labandroidproject.instructor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.labandroidproject.Class.Instructor;
import com.example.labandroidproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class taughtcourses extends Fragment {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DocumentReference documentReference = db.collection("instructors").document(mAuth.getCurrentUser().getEmail());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_taughtcourses, container, false);
        ArrayList<String> courses = new ArrayList<>();
        Instructor instructor ;
        db.collection("courses").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (int i = 0; i < task.getResult().size(); i++) {
                    if(task.getResult().getDocuments().get(i).get("instructor").equals(mAuth.getCurrentUser().getEmail())){
                        //get list of given courses

                    }

                }
            }
        });
        TextView textView = v.findViewById(R.id.textView2);
        for(String course:courses){
            textView.append(course+"\n");
        }
        return v;
    }
}