package com.example.labandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.labandroidproject.Class.Message;
import com.example.labandroidproject.Class.UploadProfilePicActivity;
import com.example.labandroidproject.Class.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
public class SignUp2 extends AppCompatActivity {
    ImageView imageView;
    Uri imageUri;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        EditText password = findViewById(R.id.password);
        EditText confirmPassword = findViewById(R.id.confirmPassword);
        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);
        Button upload_photo = findViewById(R.id.upload);
        Button signUp = findViewById(R.id.signUp);
        String role = getIntent().getStringExtra("role");

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();


        signUp.setOnClickListener(view -> {
            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Toast.makeText(this, "Please make sure password = confirm password", Toast.LENGTH_SHORT).show();
                return;
            }
            User newUser = new User();
            newUser.setFirstName(firstName.getText().toString());
            newUser.setLastName(lastName.getText().toString());
            newUser.setEmail(email.getText().toString());
            newUser.setPassword(password.getText().toString());
            String imageUriString = imageUri.toString();

            newUser.setPersonalPhoto(imageUriString);
            newUser.setRole(role);
            ArrayList<Message> messages = new ArrayList<>();
            newUser.setMessages(messages);

            db.collection("users").document(newUser.getEmail()).set(newUser);
            mAuth.createUserWithEmailAndPassword(newUser.getEmail(), newUser.getPassword()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(newUser.getFirstName() + " " + newUser.getLastName())
                                .build();

                        user.updateProfile(profileUpdates).addOnCompleteListener(updateProfileTask -> {
                            if (updateProfileTask.isSuccessful()) {
                                Intent intent = new Intent(SignUp2.this, SignIn.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this, "Failed to update user profile", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(this, "Failed to create user", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
        }
    }
}
    /*
    private void UploadPic(){
        if(imageUri!=null)
        {
            //save the image with uid of the currently logged user
            StorageReference fileReference = storageReference.child(mAuth.getCurrentUser().getUid() + "."+getFileExtention(imageUri));

            //Upload image to Storage
            fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUri = uri;
                            firebaseUser = mAuth.getCurrentUser();
                            //Finally set the display image of the user after upload
                            UserProfileChangeRequest profileUpadtes = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(downloadUri).build();
                            firebaseUser.updateProfile(profileUpadtes);
                        }
                    });
                }
            });
        }
    }
    //obtain file extension of the image
    private String getFileExtention(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}**/
