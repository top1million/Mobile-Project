package com.example.labandroidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.labandroidproject.Class.CustomBaseAdapter;
import com.example.labandroidproject.Class.Topic;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.example.labandroidproject.Class.Topic;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.C;


public class MarksActivity extends AppCompatActivity {
    List<Topic> topicList = new ArrayList<>();
    private static final String TAG = "MarksActivity";
    ListView listView;

    public interface TopicsFetchCallback {
        void onTopicsFetched(List<Topic> topics);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        listView = findViewById(R.id.marksListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), topicList);
        listView.setAdapter(customBaseAdapter);

        String username = "george";

        //////////////////////////////////////////////////////
        //addMockDataToFirestore(username);



        fetchTopics(username, new TopicsFetchCallback() {
            @Override
            public void onTopicsFetched(List<Topic> topics) {
                topicList = topics;
                customBaseAdapter.notifyDataSetChanged();
            }
        });

        //String username = "george";
        //addMockDataToFirestore(username);



        //listView = (ListView) findViewById(R.id.marksListView);
        //CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),topicList);
        //listView.setAdapter(customBaseAdapter);

        //fetchTopics(username);


    }



    private void fetchTopics(String username, TopicsFetchCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference marksRef = db.collection("marks");

        marksRef.document(username).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<HashMap<String, Object>> topicMapList = (List<HashMap<String, Object>>) document.get("topicList");
                            if (topicMapList != null) {
                                for (HashMap<String, Object> topicMap : topicMapList) {
                                    String name = (String) topicMap.get("name");
                                    int quiz = Math.toIntExact((Long) topicMap.get("quiz"));
                                    int midterm = Math.toIntExact((Long) topicMap.get("midterm"));
                                    int project = Math.toIntExact((Long) topicMap.get("project"));
                                    int finalGrade = Math.toIntExact((Long) topicMap.get("finalGrade"));

                                    Topic topic = new Topic(name, quiz, midterm, project, finalGrade);
                                    topicList.add(topic);
                                }

                                // Invoke the callback to notify that the data is fetched and provides the fetched topics for UI updates
                                callback.onTopicsFetched(topicList);
                            } else {
                                Log.d(TAG, "No topics found for username: " + username);
                                // Invoke the callback with an empty list
                                callback.onTopicsFetched(new ArrayList<>());
                            }
                        } else {
                            Log.d(TAG, "No document found for username: " + username);
                            // Invoke the callback with an empty list
                            callback.onTopicsFetched(new ArrayList<>());
                        }
                    } else {
                        Log.w(TAG, "Error getting document", task.getException());
                        // Invoke the callback with an empty list
                        callback.onTopicsFetched(new ArrayList<>());
                    }
                });
    }

    private void addMockDataToFirestore(String username) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference marksRef = db.collection("marks");

        // Create a list to hold the topic data
        List<HashMap<String, Object>> topicMapList = new ArrayList<>();

        // Create mock data for topics and their marks
        HashMap<String, Object> topic1 = new HashMap<>();
        topic1.put("name", "Object Oriented Programming");
        topic1.put("quiz", 90);
        topic1.put("midterm", 85);
        topic1.put("project", 95);
        topic1.put("finalGrade", 92);

        HashMap<String, Object> topic2 = new HashMap<>();
        topic2.put("name", "Mobile Devlopment");
        topic2.put("quiz", 80);
        topic2.put("midterm", 75);
        topic2.put("project", 88);
        topic2.put("finalGrade", 84);

        HashMap<String, Object> topic3 = new HashMap<>();
        topic3.put("name", "Arabic");
        topic3.put("quiz", 80);
        topic3.put("midterm", 75);
        topic3.put("project", 88);
        topic3.put("finalGrade", 84);


        HashMap<String, Object> topic4 = new HashMap<>();
        topic4.put("name", "English");
        topic4.put("quiz", 99);
        topic4.put("midterm", 96);
        topic4.put("project", 98);
        topic4.put("finalGrade", 98);


        // Add the topic data to the list
        topicMapList.add(topic1);
        topicMapList.add(topic2);
        topicMapList.add(topic4);
        topicMapList.add(topic3);

        // Create a document reference for the username
        DocumentReference userDocRef = marksRef.document(username);

        // Set the topicList field of the document with the mock data
        userDocRef.set(new HashMap<String, Object>() {{
                    put("topicList", topicMapList);
                }})
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Mock data added to Firestore for username: " + username))
                .addOnFailureListener(e -> Log.e(TAG, "Error adding mock data to Firestore: " + e.getMessage()));
    }













}
