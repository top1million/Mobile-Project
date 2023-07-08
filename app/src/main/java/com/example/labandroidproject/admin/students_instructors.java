package com.example.labandroidproject.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.labandroidproject.Class.Trainee;
import com.example.labandroidproject.R;
import com.example.labandroidproject.traineeAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Collection;

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
        String [] traineeFirstNames = new String[100];
        String [] traineeLastNames = new String[100];
        int [] traineeImages = new int[100];
        users.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                int i = 0;
                for(QueryDocumentSnapshot document : task.getResult()){
                    String role = document.getString("role");
                    if (role.equals("Trainee")){
                        String firstName = document.getString("firstName");
                        String lastName = document.getString("lastName");
                        String image = document.getString("image");
                        traineeFirstNames[i] = firstName;
                        traineeLastNames[i] = lastName;
                        traineeImages[i] = Integer.parseInt(image);
                        i++;
                    }
                }
            }
        });
        if (spinner.getSelectedItem().toString().equals("Students")){
            RecyclerView recyclerView = v.findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
            traineeAdapter adapter = new traineeAdapter(traineeFirstNames,traineeLastNames,traineeImages);
            recyclerView.setAdapter(adapter);
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