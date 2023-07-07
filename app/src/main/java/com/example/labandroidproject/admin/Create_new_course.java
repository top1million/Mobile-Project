package com.example.labandroidproject.admin;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labandroidproject.Class.Course;
import com.example.labandroidproject.R;
import com.google.firebase.firestore.FirebaseFirestore;


public class Create_new_course extends Fragment {
    Uri imageUri ;
    public Create_new_course() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_create_new_course, container, false);
        EditText courseTitle = (EditText) v.findViewById(R.id.courseTitle);
        EditText courseMainTopics = (EditText) v.findViewById(R.id.courseMainTopics);
        EditText prerequisites = (EditText) v.findViewById(R.id.prerequisites);
        Button uploadPhoto = (Button) v.findViewById(R.id.upload);
        Button createCourse = (Button) v.findViewById(R.id.create);
        uploadPhoto.setOnClickListener(view -> {
            Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, 1);
        });


        createCourse.setOnClickListener(view -> {
            if(courseTitle.getText().toString().isEmpty() || courseMainTopics.getText().toString().isEmpty() || prerequisites.getText().toString().isEmpty()|| imageUri==null){
                Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String imageUri = this.imageUri.toString();
            db.collection("courses").add(
                    new Course(courseTitle.getText().toString(), courseMainTopics.getText().toString(), prerequisites.getText().toString(),imageUri)
            );
            Toast.makeText(getActivity(), "Course created successfully", Toast.LENGTH_SHORT).show();
            replaceFragment(new admin_home_page());
        });
        return v;
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getParentFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null){
            imageUri = data.getData();
        }
    }
}