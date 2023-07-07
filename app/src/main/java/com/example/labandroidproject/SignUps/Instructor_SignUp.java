package com.example.labandroidproject.SignUps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;

import com.example.labandroidproject.R;

public class Instructor_SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sign_up);
        // when click on the button load the instructor's profile picture
        Button upload_photo = (Button) findViewById(R.id.upload);
        upload_photo.setOnClickListener(view -> {
            Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, 1);
        });

    }
}