package com.example.labandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        Button signIn = (Button) findViewById(R.id.signIn);
        EditText email = (EditText) findViewById(R.id.textView_content);
        EditText password = (EditText) findViewById(R.id.password);
        signIn.setOnClickListener(view -> {
            mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(SignIn.this, homePage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}