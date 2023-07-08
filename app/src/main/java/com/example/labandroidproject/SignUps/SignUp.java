package com.example.labandroidproject.SignUps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.labandroidproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button signUp = (Button) findViewById(R.id.btnSignUp);
        Spinner role = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);
        signUp.setOnClickListener(view -> {
            String role1 = role.getSelectedItem().toString();
            if (role1.equals("Student")) {
                Intent intent = new Intent(SignUp.this, Student_SignUP.class);
                intent.putExtra("role", role1);
                startActivity(intent);
            } else if (role1.equals("Instructor")) {
                Intent intent = new Intent(SignUp.this, Instructor_SignUp.class);
                intent.putExtra("role", role1);
                startActivity(intent);
            } else if (role1.equals("Admin")) {
                Intent intent = new Intent(SignUp.this, Admin_SignUp.class);
                intent.putExtra("role", role1);
                startActivity(intent);
            }
        });

    }
}