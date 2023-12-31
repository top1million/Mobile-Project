package com.example.labandroidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labandroidproject.Class.ForgotPasswordActivity;
import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;


public class SignIn extends AppCompatActivity {
    private CheckBox CheckBoxRemember;
    private SharedPreferences mPrefs;
    private FirebaseAuth authProfile;
    private static final String PREFS_NAME = "PrefsFile";
    FirebaseAuth mAuth;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        Button signIn = (Button) findViewById(R.id.signIn);
        EditText email = (EditText) findViewById(R.id.textView_content);
        EditText password = (EditText) findViewById(R.id.password);
        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        CheckBoxRemember = (CheckBox) findViewById(R.id.remember_me_chkb);

        //Reset Password
        Button buttonForgotPassword=findViewById(R.id.button_forgot_password);
        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignIn.this,"You can reset your password now!",Toast.LENGTH_SHORT);
                startActivity(new Intent(SignIn.this, ForgotPasswordActivity.class));
            }
        });

        checkBox();

        Paper.init(this);

        signIn.setOnClickListener(view -> {
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (CheckBoxRemember.isChecked()) {
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("name", "true");
                        editor.apply();
                    }


                    Toast.makeText(SignIn.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this, homePage.class);
                    //To prevent user from returning back to to signIn activity on pressing back button after signed in
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("name", "");
        if (check.equals("true")) {
            Toast.makeText(SignIn.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignIn.this, homePage.class);
            startActivity(intent);
            finish();
        }
    }
}