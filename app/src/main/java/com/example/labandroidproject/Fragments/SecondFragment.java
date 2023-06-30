package com.example.labandroidproject.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;
import androidx.fragment.app.Fragment;


import com.example.labandroidproject.Class.Message;
import com.example.labandroidproject.Class.User;
import com.example.labandroidproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SecondFragment extends Fragment {

    public SecondFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        Button send = (Button) v.findViewById(R.id.send);
        EditText content = (EditText) v.findViewById(R.id.content);
        EditText receiver = (EditText) v.findViewById(R.id.email1);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User reciver = db.collection("users").whereEqualTo("email", receiver.getText().toString()).get().getResult().getDocuments().get(0).toObject(User.class);
                Toast.makeText(getContext(), "view", Toast.LENGTH_SHORT).show();
                if (reciver == null){
                    Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                String contentText = content.getText().toString();
                Message message = new Message();
                message.setMessage(contentText);
                reciver.getMessages().add(message);
                db.collection("users").document(receiver.getText().toString()).set(reciver);
                Toast.makeText(getContext(), "Message sent", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}