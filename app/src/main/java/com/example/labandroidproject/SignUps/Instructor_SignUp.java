package com.example.labandroidproject.SignUps;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Instructor_SignUp extends AppCompatActivity {
    ImageView imageView;
    Uri imageUri ;
    int imageId;
    EditText password, confirmPassword, firstName, lastName, email;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sign_up);
        // when click on the button load the instructor's profile picture
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
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
            // verification part for the instructor's data
            if (!validateFirstName() | !validateLastName() | !validateEmail() | !validatePassword()) {
                return;
            }
            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                return;
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
            db.collection("Instructor").document(newUser.getEmail()).set(newUser);
            Toast.makeText(this, "Instructor added successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null){
            imageUri = data.getData();
            uploadImage(imageUri);
        }
    }

    private void uploadImage(Uri imageUri) {
        EditText email = findViewById(R.id.email);
        UploadTask uploadTask = storageRef.child("lol/"+email.getText().toString()).putFile(imageUri);

        // Register observers to listen for the upload progress or errors
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                // Get the progress of the upload

                // Update UI or show progress dialog
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Handle successful uploads
                imageId = taskSnapshot.hashCode();

            }
        });
    }

    private  boolean validateEmail(){
        String email1 = this.email.getText().toString();
        if(email1.isEmpty()){
            email.setError("Email is required");
            return false;

        }
        if(!email1.contains("@")){
            email.setError("Email must contain @");
            return false;
        }
        if(!email1.contains(".")){
            email.setError("Email must contain .");
            return false;
        }
        else {
            email.setError(null);

            return true;
        }

    }

    private boolean validatePassword(){
        String password1 = this.password.getText().toString();

        if(password1.isEmpty()){
            password.setError("Password is required");
            return false;
        }
        if(password1.length() < 8){
            password.setError("Password must be at least 8 characters");
            return false;
        }
        if(password1.length() > 15){
            password.setError("Password must be less than 15 characters");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }

    private boolean validateFirstName(){
        String firstName1 = this.firstName.getText().toString();
        if(firstName1.isEmpty()){
            firstName.setError("First name is required");
            return false;
        }
        if(firstName1.length() < 3){
            firstName.setError("First name must be at least 3 characters");
            return false;
        }
        if (firstName1.length() > 20){
            firstName.setError("First name must be less than 20 characters");
            return false;
        }
        else{
            firstName.setError(null);
            return true;
        }

    }
    private boolean validateLastName(){
        String lastName1 = this.lastName.getText().toString();
        if(lastName1.isEmpty()){
            lastName.setError("Last name is required");
            return false;

        }
        if(lastName1.length() < 3){
            lastName.setError("Last name must be at least 3 characters");
            return false;
        }
        if (lastName1.length() > 20){
            lastName.setError("Last name must be less than 20 characters");
            return false;
        }
        else{
            lastName.setError(null);
            return true;
        }
    }
}