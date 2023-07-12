package com.example.labandroidproject.Class;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.atomic.AtomicReference;

public class Database {
    FirebaseFirestore db;

    public Database() {
        db = FirebaseFirestore.getInstance();
    }
    public String getUserRoleByEmail(String email) {
        AtomicReference<String> role = new AtomicReference<>("");
        db.collection("users").document(email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                role.set(task.getResult().getString("role"));
            }
        });
        return String.valueOf(role);
    }
}
