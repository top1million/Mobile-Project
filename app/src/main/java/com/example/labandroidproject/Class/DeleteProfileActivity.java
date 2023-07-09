package com.example.labandroidproject.Class;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labandroidproject.MainActivity;
import com.example.labandroidproject.R;
import com.example.labandroidproject.homePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class DeleteProfileActivity extends AppCompatActivity {
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private EditText editTextUserPwd;
    private TextView textViewAuthenticated;
    private ProgressBar progressBar;
    private String userPwd;
    private static final String TAG = "DeleteProfileActivity";
    private Button buttonReAuthenticate, buttonDeleteUser;

    public static final String SHARED_PREFS = "sharedPrefs";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile);

        getSupportActionBar().setTitle("Delete Your Profile");

        progressBar = findViewById(R.id.progressBar);
        editTextUserPwd = findViewById(R.id.editText_delete_user_pwd);
        textViewAuthenticated = findViewById(R.id.textView_delete_user_authenticated);
        buttonReAuthenticate = findViewById(R.id.button_delete_user_authenticate);
        buttonDeleteUser = findViewById(R.id.button_delete_user);

        //Disable Delete User Button until user is authenticated
        buttonDeleteUser.setEnabled(false);
        authProfile=FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        if(firebaseUser.equals("")){
            Toast.makeText(DeleteProfileActivity.this,"Something went wrong!"+"User Detils are not available at the moment.",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DeleteProfileActivity.this, homePage.class);
            startActivity(intent);
            finish();
        }else {
            reAuthenticateUser(firebaseUser);
        }

    }
    private void reAuthenticateUser(FirebaseUser firebaseuser){
        buttonReAuthenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPwd = editTextUserPwd.getText().toString();
                {
                    if(TextUtils.isEmpty(userPwd)){
                        Toast.makeText(DeleteProfileActivity.this,"Password is needed",
                                Toast.LENGTH_SHORT).show();
                        editTextUserPwd.setError("Please enter your current password to authenticate");
                        editTextUserPwd.requestFocus();
                    }else{
                        progressBar.setVisibility(View.VISIBLE);

                        //ReAuthenticate User now
                        AuthCredential credential = EmailAuthProvider.getCredential(firebaseuser.getEmail(),userPwd);
                        firebaseuser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){

                                    //Disable editText for Password.
                                    editTextUserPwd.setEnabled(false);

                                    //Enable Delete User Nutton. Disable Authenticate Button
                                    buttonReAuthenticate.setEnabled(false);
                                    buttonDeleteUser.setEnabled(true);

                                    //Set TextView to show user is authenticated/Verified
                                    textViewAuthenticated.setText("You are authenticated/verified."+"You can delete your profile now");

                                            Toast.makeText(DeleteProfileActivity.this,"Password has been verified."+".Be careful, this action is irreversible",Toast.LENGTH_SHORT).show();
                                    //Update color of Change Password Button

                                    buttonDeleteUser.setBackgroundTintList(ContextCompat.getColorStateList(DeleteProfileActivity.this,R.color.dark_green));

                                    buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            showAlertDialog();
                                        }
                                    });


                                }else {
                                    try{
                                        throw task.getException();
                                    }catch(Exception e){
                                        Toast.makeText(DeleteProfileActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }

                                }
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                }
            }
        });
    }
    private void showAlertDialog(){
        //Setupt the Alert Builder

        AlertDialog.Builder builder = new AlertDialog.Builder(DeleteProfileActivity.this);
        builder.setTitle("Delete User and Related Data?");
        builder.setMessage("Do you really want to delete your profile and related data? This action is irreversible!");

        //Open Email App if user clicks.taps continue button
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteUser(firebaseUser);
            }
        });

        //Return to home activity if user presses cancel button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent = new Intent(DeleteProfileActivity.this, homePage.class);
                startActivity(intent);
                finish();
            }
        });
        //Create the alertDialog
        AlertDialog alertDialog = builder.create();

        //Change the button color of continue

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));
            }
        });
        //show the alertDialog
        alertDialog.show();

    }

    private void deleteUser(FirebaseUser firebaseUser) {
        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){


                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("name", "false");
                    editor.apply();
                    authProfile.signOut();
                    deleteUserDatA();
                    Toast.makeText(DeleteProfileActivity.this,"User has been deleted",Toast.LENGTH_SHORT);

                    Intent intent = new Intent(DeleteProfileActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    try{
                        throw task.getException();
                    }catch (Exception e){
                        Toast.makeText(DeleteProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    //Delete all the date of user
    private void deleteUserDatA() {
      if(firebaseUser.getPhotoUrl() != null)
      {
          //Delete Display Pic
          FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
          StorageReference storageReference = firebaseStorage.getReferenceFromUrl(firebaseUser.getPhotoUrl().toString());
          storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
              @Override
              public void onSuccess(Void unused) {
                  Log.d(TAG,"On Success: photo deleted");
              }
          }).addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                  Log.d(TAG,e.getMessage());
                  Toast.makeText(DeleteProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
              }
          });
      }

        //Delete Data from Realtime Database

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        databaseReference.child(firebaseUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG,"On Success: User Data deleted");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,e.getMessage());
                Toast.makeText(DeleteProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
