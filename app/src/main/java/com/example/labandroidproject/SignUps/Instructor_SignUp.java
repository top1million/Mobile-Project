package com.example.labandroidproject.SignUps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.labandroidproject.Class.Instructor;
import com.example.labandroidproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

public class Instructor_SignUp extends AppCompatActivity {
    ImageView imageView;
    Uri imageUri ;
    int imageId;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sign_up);
        // when click on the button load the instructor's profile picture
        EditText password = findViewById(R.id.password);
        EditText confirmPassword = findViewById(R.id.confirmPassword);
        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phoneNumber);
        EditText address = findViewById(R.id.Address);
        Spinner specialization = findViewById(R.id.degree);
        Button upload_photo = (Button) findViewById(R.id.upload);
        upload_photo.setOnClickListener(view -> {
            Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, 1);
        });
        Button signUp = (Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(view -> {
            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Toast.makeText(this, "password is not the same", Toast.LENGTH_SHORT).show();
            }
            mAuth = FirebaseAuth.getInstance();
            Instructor newUser = new Instructor();
            newUser.setFirstName(firstName.getText().toString());
            newUser.setLastName(lastName.getText().toString());
            newUser.setEmail(email.getText().toString());
            newUser.setPassword(password.getText().toString());
            newUser.setMobileNumber(phone.getText().toString());
            newUser.setAddress(address.getText().toString());
            newUser.setSpecialization(specialization.getSelectedItem().toString());

            newUser.setRole("Instructor");
            db.collection("users_test").document(newUser.getEmail()).set(newUser);
            Toast.makeText(this, "Instructor added successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Instructor_SignUp.class);
            startActivity(intent);
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null){
            imageUri = data.getData();
            imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageURI(imageUri);
        }
    }
}