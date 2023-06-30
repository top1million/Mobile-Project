package com.example.labandroidproject.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.labandroidproject.Class.Message;
import com.example.labandroidproject.Class.User;
import com.example.labandroidproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SecondFragment extends Fragment {
    FirebaseAuth mAuth;

    public SecondFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        Button send = (Button) v.findViewById(R.id.send);
        EditText content = (EditText) v.findViewById(R.id.content);
        EditText receiver = (EditText) v.findViewById(R.id.email1);
        EditText title = (EditText) v.findViewById(R.id.title);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String loggedUser = mAuth.getCurrentUser().getEmail();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("users").document(receiver.getText().toString());
                docRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        User reciver = task.getResult().toObject(User.class);
                        if (reciver == null) {
                            Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String contentText = content.getText().toString();
                        Message message = new Message();
                        message.setTitle(title.getText().toString());
                        message.setMessage(contentText);
                        message.setSender(loggedUser);
                        message.setSeen(false);
                        reciver.getMessages().add(message);
                        docRef.set(reciver);
                        Toast.makeText(getContext(), "Message sent", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });

        return v;
    }

}