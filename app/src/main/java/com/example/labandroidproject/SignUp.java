package com.example.labandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button signUp = (Button) findViewById(R.id.btnSignUp);
        Spinner role = (Spinner) findViewById(R.id.spinner);
        signUp.setOnClickListener(view -> {
            Intent SignUp2 = new Intent(SignUp.this, SignUp2.class);
            SignUp2.putExtra("role", role.getSelectedItem().toString());
            startActivity(SignUp2);
        });
    }
}