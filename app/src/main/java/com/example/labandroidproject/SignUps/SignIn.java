package com.example.labandroidproject.SignUps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labandroidproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignIn extends AppCompatActivity {
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText email_address;
    EditText password_field;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Button signIn = (Button) findViewById(R.id.signIn);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        CheckBox rememberMe = (CheckBox) findViewById(R.id.checkBox);
        checkperfs();
        signIn.setOnClickListener(view -> {
            mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (rememberMe.isChecked()) {
                        editor.putString("email", email.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.commit();
                    }
                    roleIntent(email.getText().toString());
                }
                else {
                    Toast.makeText(this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }



    private void roleIntent(String email) {
        String role;
        if(email.equals("test@gmail.com"))
            role = "admin";
        else if(email.equals("teststudent@gmail.com"))
            role = "student";
        else{
            role = "instructor";
        }
        Intent homeIntent = new Intent(this, com.example.labandroidproject.HomePages.homePage.class);
        homeIntent.putExtra("role",role);
        startActivity(homeIntent);
    }

    private void checkperfs() {
        String email = sharedPreferences.getString("email","");
        String password = sharedPreferences.getString("password","");
        if (!email.equals("") && !password.equals("")) {
            email_address = (EditText) findViewById(R.id.email);
            password_field = (EditText) findViewById(R.id.password);
            email_address.setText(email);
            password_field.setText(password);
        }
    }

}