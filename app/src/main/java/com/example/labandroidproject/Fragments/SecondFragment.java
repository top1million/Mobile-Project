package com.example.labandroidproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.labandroidproject.Class.Message;
import com.example.labandroidproject.Class.User;
import com.example.labandroidproject.MessegesAdapter;
import com.example.labandroidproject.R;
import com.example.labandroidproject.SendMessageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

public class SecondFragment extends Fragment {
    FirebaseAuth mAuth;



    public SecondFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        View v = inflater.inflate(R.layout.fragment_second, container, false);

        RecyclerView recyclerView =  v.findViewById(R.id.recyclerview);
        Button sendNewButton = (Button) v.findViewById(R.id.button_sendNew);

        sendNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SendMessageActivity.class);
                startActivity(intent);
            }
        });
        String loggedUserEmail = mAuth.getCurrentUser().getEmail();
        DocumentReference docRef = db.collection("users").document(loggedUserEmail);

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                User loggedUser = task.getResult().toObject(User.class);
                if (loggedUser== null) {
                    Toast.makeText(v.getContext(), "Error fetch/user not found", Toast.LENGTH_SHORT).show();
                    return;
                }



                  Message [] messegeList =  loggedUser.getMessages().toArray(new Message[0]);



                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                recyclerView.setAdapter(new MessegesAdapter(v.getContext(), Arrays.asList(messegeList) ));






            }else
            {
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                recyclerView.setAdapter(new MessegesAdapter(v.getContext(), Arrays.asList(new Message[]{new Message()}) ));

            }

        });





        return v;
    }

}