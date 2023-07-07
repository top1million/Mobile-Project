package com.example.labandroidproject.admin;

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
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.labandroidproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class edit_delete_course extends Fragment {

    public edit_delete_course() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CollectionReference courses = FirebaseFirestore.getInstance().collection("courses");
        View v = inflater.inflate(R.layout.fragment_edit_delete_course, container, false);
        List<String> courseNames = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, courseNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        Button delete = (Button) v.findViewById(R.id.delete);
        Button edit = (Button) v.findViewById(R.id.edit);
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
        delete.setOnClickListener(view -> {
            String courseName = spinner.getSelectedItem().toString();
            courses.whereEqualTo("courseTitle", courseName).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        courses.document(document.getId()).delete();
                    }
                }
            });
            Toast.makeText(getActivity(), "Course deleted successfully", Toast.LENGTH_SHORT).show();
            replaceFragment(new admin_home_page());
        });
        edit.setOnClickListener(view->{
            String courseName = spinner.getSelectedItem().toString();
            courses.document(courseName).update("courseTitle", ((EditText) v.findViewById(R.id.courseTitle)).getText().toString());
            courses.document(courseName).update("courseMainTopics", ((EditText) v.findViewById(R.id.courseMainTopics)).getText().toString());
            courses.document(courseName).update("prerequisites", ((EditText) v.findViewById(R.id.prerequisites)).getText().toString());
            Toast.makeText(getContext(), "Course edited successfully", Toast.LENGTH_SHORT).show();
            replaceFragment(new admin_home_page());
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