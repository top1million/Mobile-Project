package com.example.labandroidproject.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.labandroidproject.Class.Instructor;
import com.example.labandroidproject.Class.Trainee;
import com.example.labandroidproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
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
        ArrayList<Trainee> trainees = new ArrayList<>();
        ArrayList<Instructor> instructors = new ArrayList<>();
        db.collection("Student").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    trainees.add(document.toObject(Trainee.class));
                }
            }
        });
        db.collection("Instructor").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    instructors.add(document.toObject(Instructor.class));
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                if (pos == 0) {

                    String s = "";
                    for (int i = 0; i < trainees.size(); i++) {
                        s+=trainees.get(i).getEmail().toString()+" "+trainees.get(i).getFirstName().toString()+" "+trainees.get(i).getLastName().toString()+"\n";
                        s+="\n";
                    }
                    textView.setText(s);
                    textView.setVisibility(View.VISIBLE);
                } else if (pos == 1) {
                    String s = "";
                    for (int i = 0; i < instructors.size(); i++) {
                        s+=instructors.get(i).getEmail().toString()+" "+instructors.get(i).getFirstName().toString()+" "+instructors.get(i).getLastName().toString()+"\n";
                        s+="\n";
                    }
                    textView.setText(s);
                    textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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