package com.example.labandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labandroidproject.Class.Message;
import com.example.labandroidproject.Class.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SendMessageActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        mAuth = FirebaseAuth.getInstance();
        Button send = (Button) findViewById(R.id.send);
        EditText content = (EditText) findViewById(R.id.content);
        EditText receiver = (EditText) findViewById(R.id.email1);
        EditText title = (EditText) findViewById(R.id.title);
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
                            Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });
    }
}