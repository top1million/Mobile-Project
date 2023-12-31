package com.example.labandroidproject.Fragments;

import static android.content.Context.MODE_PRIVATE;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labandroidproject.Class.DeleteProfileActivity;
import com.example.labandroidproject.Class.UpdateEmailActivity;
import com.example.labandroidproject.Class.UploadProfilePicActivity;
import com.example.labandroidproject.Class.User;
import com.example.labandroidproject.MainActivity;
import com.example.labandroidproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

public class FourthFragment extends Fragment {
    private FirebaseAuth mAuth, authProfile;
    private SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "sharedPrefs";

    private ImageView mImageView;
    private TextView textViewWelcome, update_email,textDeleteUser;
    private Uri uri;
    String Uid;

    private EditText etToken;


    public FourthFragment() {
        // Required empty public constructor
    }

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
        mAuth = FirebaseAuth.getInstance();
        mImageView= view.findViewById(R.id.image_profile_picture);
        Button logout = view.findViewById(R.id.signOut);
        textViewWelcome = view.findViewById(R.id.text_username);
        textDeleteUser = view.findViewById(R.id.text_delete_user);
        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        //Set User DP (After user has uploaded)

        if (firebaseUser != null) {
            Uri uri = firebaseUser.getPhotoUrl();
            if (uri != null) {
                //ImageViewer setImageURI() should not be used with regular URI. So we are using Picasso
                Picasso.with(getActivity()).load(uri).into(mImageView);
            }
        } else {
            // Handle the case when the firebaseUser is null
            // For example, display a default image or show an error message
        }

        etToken = view.findViewById(R.id.etToken);


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        //System.out.println(token);
//                        Toast.makeText(getActivity(), "Your device registration token is" + token
//                                , Toast.LENGTH_SHORT).show();

                        etToken.setText(token);
                    }
                });


        textDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), DeleteProfileActivity.class);
                startActivity(intent);

            }
        });

        update_email = view.findViewById(R.id.text_update_email);

        update_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), UpdateEmailActivity.class);
                startActivity(intent);

            }
        });



        mImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), UploadProfilePicActivity.class);
                startActivity(intent);
            }
        });




        authProfile = FirebaseAuth.getInstance();

        if (firebaseUser == null) {
            Toast.makeText(getActivity(), "Something went wrong! User's details are not avaibale at the moment", Toast.LENGTH_SHORT).show();
        } else {

           textViewWelcome.setText("Welcome "+firebaseUser.getDisplayName().toUpperCase()+" !");
        }


        sharedPreferences = requireActivity().getPreferences(getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Retrieve the saved theme mode from SharedPreferences
        int savedThemeMode = sharedPreferences.getInt("themeMode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        // Set the saved theme mode
        AppCompatDelegate.setDefaultNightMode(savedThemeMode);

        RadioGroup themeRadioGroup = view.findViewById(R.id.radio_group_theme);
        RadioButton lightThemeRadioButton = view.findViewById(R.id.radio_light_theme);
        RadioButton darkThemeRadioButton = view.findViewById(R.id.radio_dark_theme);

        // Set the current theme mode radio button as checked
        if (savedThemeMode == AppCompatDelegate.MODE_NIGHT_NO) {
            lightThemeRadioButton.setChecked(true);
        } else if (savedThemeMode == AppCompatDelegate.MODE_NIGHT_YES) {
            darkThemeRadioButton.setChecked(true);
        }

        themeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int newThemeMode;
                if (checkedId == R.id.radio_light_theme) {

                    newThemeMode = AppCompatDelegate.MODE_NIGHT_NO;
                } else {
                    newThemeMode = AppCompatDelegate.MODE_NIGHT_YES;
                }


                editor.putInt("themeMode", newThemeMode);
                editor.apply();

                // Set the new theme mode
                AppCompatDelegate.setDefaultNightMode(newThemeMode);
                // Recreate the activity to apply the new theme immediately
                if (savedThemeMode != newThemeMode) {
                    replaceFragment(new FourthFragment());
                    getActivity().recreate();
                }
            }
        });

        Switch aSwitch = view.findViewById(R.id.switch_notifications);

        // Save switch state in shared preferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("save", MODE_PRIVATE);
        aSwitch.setChecked(sharedPreferences.getBoolean("value", true));

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch.isChecked()) {
                    //When switch checked
                    SharedPreferences.Editor editor = requireActivity().getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    aSwitch.setChecked(true);
                } else {
                    //When switch unchecked
                    SharedPreferences.Editor editor = requireActivity().getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    aSwitch.setChecked(false);
                }
            }
        });

        TextView changePasswordTextView = view.findViewById(R.id.text_change_password);
        changePasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangePasswordDialog();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("name", "false");
                editor.apply();


                mAuth.signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


    }

    //Creating ActionBar menu

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.common_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_refresh) {
            // Refresh Fragment
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.detach(this).attach(this).commit();
            Toast.makeText(requireContext(), "Fragment Refreshed", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showChangePasswordDialog() {
        //inflate layout for dialog
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_password, null);
        EditText edit_old_password = view.findViewById(R.id.edit_old_password);
        EditText edit_new_password = view.findViewById(R.id.edit_new_password);
        Button updatePasswordBtn = view.findViewById(R.id.updatePasswordBtn);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view); //set view to dialog

        final AlertDialog dialog = builder.create();
        builder.create().show();

        updatePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate data
                String oldPassword = edit_old_password.getText().toString().trim();
                String newPassword = edit_new_password.getText().toString().trim();
                if (TextUtils.isEmpty(oldPassword)) {
                    Toast.makeText(getActivity(), "Enter your current password...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPassword.length() < 6) {
                    Toast.makeText(getActivity(), "Password length must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.dismiss();
                updatePassword(oldPassword, newPassword);
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getParentFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
    private void updatePassword(String oldPassword, String newPassword) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog pd = builder.create();
        pd.show();

        // Get the current user
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        // Before changing the password, re-authenticate the user
        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword);

        user.reauthenticate(authCredential)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Successfully authenticated, begin the password update
                        user.updatePassword(newPassword)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        pd.dismiss();
                                        Toast.makeText(getActivity(), "Password Updated", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Failed updating the password, show the reason
                                        pd.dismiss();
                                        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Authentication failed, show the reason
                        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
