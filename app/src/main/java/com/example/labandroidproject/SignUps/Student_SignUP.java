package com.example.labandroidproject.SignUps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.labandroidproject.Class.Trainee;
import com.example.labandroidproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Student_SignUP extends AppCompatActivity {
    ImageView imageView;
    Uri imageUri ;
    int imageId;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
        // when click on the button load the student's profile picture
        EditText password = findViewById(R.id.password);
        EditText confirmPassword = findViewById(R.id.confirmPassword);
        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);
        EditText mobileNumber = findViewById(R.id.mobileNumber);
        EditText address = findViewById(R.id.Address);
        Button upload_photo = (Button) findViewById(R.id.upload);
        upload_photo.setOnClickListener(view -> {
            Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, 1);
        });
        Button signUp = (Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(view -> {
            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Toast.makeText(this, "Please make sure pasword = confirmpassword", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth = FirebaseAuth.getInstance();
            Trainee newUser = new Trainee();
            newUser.setFirstName(firstName.getText().toString());
            newUser.setLastName(lastName.getText().toString());
            newUser.setEmail(email.getText().toString());
            newUser.setPassword(password.getText().toString());
            newUser.setMobileNumber(mobileNumber.getText().toString());
            newUser.setAddress(address.getText().toString());
            if(imageUri == null){
                imageId = getResources().getIdentifier("blank_profile_picture", "drawable", getPackageName());
                imageUri = Uri.parse("android.resource://com.example.labandroidproject/drawable/blank_profile_picture");
            }
            imageId = getResources().getIdentifier(imageUri.toString(), null, getPackageName());
            newUser.setImageId(imageId);
            String imageUri = this.imageUri.toString();
            newUser.setPersonalPhoto(imageUri);
            newUser.setRole("Student");
            db.collection("Student").document(newUser.getEmail()).set(newUser);
            Intent intent = new Intent(Student_SignUP.this, SignIn.class);
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