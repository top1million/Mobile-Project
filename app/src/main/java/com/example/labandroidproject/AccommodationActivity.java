package com.example.labandroidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.labandroidproject.Class.Accommodation;
import com.example.labandroidproject.Class.AccommodationAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AccommodationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AccommodationAdapter accommodationAdapter;
    private List<Accommodation> accommodationList;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation);


        recyclerView = findViewById(R.id.accommodationRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        accommodationList = new ArrayList<>();
        accommodationAdapter = new AccommodationAdapter(accommodationList);
        recyclerView.setAdapter(accommodationAdapter);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        ////////////////////////////////////////////
        //uploadMockAccommodations();




        // Fetch accommodations from Firestore
        fetchAccommodations();



    }
    private void fetchAccommodations() {
        // Query the accommodations collection
        db.collection("accommodations")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Parse the document data and create Accommodation objects
                        String imageUrl = document.getString("imageUrl");
                        String phoneNumber = document.getString("phoneNumber");
                        String location = document.getString("location");
                        String area = document.getString("area");
                        int numberOfRooms = document.getLong("numberOfRooms").intValue();

                        Accommodation accommodation = new Accommodation(imageUrl, phoneNumber, location, area, numberOfRooms);
                        accommodationList.add(accommodation);
                    }
                    // Notify the adapter of the data changes
                    accommodationAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Handle any errors
                    Toast.makeText(AccommodationActivity.this, "Failed to fetch accommodations: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }



    private void uploadMockAccommodations() {
        // Create mock data for accommodations
        List<Accommodation> mockAccommodations = new ArrayList<>();

        Accommodation accommodation1 = new Accommodation("https://cf.bstatic.com/xdata/images/hotel/max1280x900/453232541.jpg?k=9a2e83e03a4f318496af90e8cd3ad70fd3bb0aeaed8c64c069ba923387cb51af&o=&hp=1&fbclid=IwAR3kw0QNyZ8K_R790e0dbzBAV1VIE9PU8pol0NVXCh-3g8LDofrEpl1XsLk", "0587748459", "Birzeit", "100M2", 3);
        Accommodation accommodation2 = new Accommodation("https://cf.bstatic.com/xdata/images/hotel/max1024x768/453232635.jpg?k=cb6a624bb59c990552b2c026e540af44d1b2111ad349205f2861be31197908e9&o=&hp=1&fbclid=IwAR3hsdBgvahVzj8j8pmX95rihnFsi2bdX6eEsiz0-bKx98tvBGxMuwUwc64", "0598845124", "Birzeit", "220M2", 2);
        Accommodation accommodation3 = new Accommodation("https://cf.bstatic.com/xdata/images/hotel/max1280x900/453232648.jpg?k=b47ca4641cf20d808c5ce238a09689a0a9a6040625669fa4e715fa7463dc534a&o=&hp=1&fbclid=IwAR2qmFPdBDLSzYnaeq7DlNt7_8x4wj0G5pWZuG4RjmWX2YfzSzcxcsmPMXE", "0574412589", "Ein Musbah", "150M2", 4);
        Accommodation accommodation4 = new Accommodation("https://a0.muscache.com/im/pictures/94af75d2-1503-4c3e-a97e-619a4c657e30.jpg?im_w=1200&fbclid=IwAR37GR3ticrwPhQE6Vzye3NwA1VTiyqz7_sie7ODT2RQgpge1zdVO7AQ96Q", "456123789", "Surda", "120M2", 4);
        Accommodation accommodation5 = new Accommodation("https://a0.muscache.com/im/pictures/cb9a5fe4-d5dc-43d0-a190-e16fa2547255.jpg?im_w=1440&fbclid=IwAR3kw0QNyZ8K_R790e0dbzBAV1VIE9PU8pol0NVXCh-3g8LDofrEpl1XsLk", "456123789", "Em Al-Sharayet", "130M2", 4);

        // Add the mock accommodations to the list
        mockAccommodations.add(accommodation1);
        mockAccommodations.add(accommodation2);
        mockAccommodations.add(accommodation3);
        mockAccommodations.add(accommodation4);
        mockAccommodations.add(accommodation5);

        // Upload the mock accommodations to Firestore
        for (Accommodation accommodation : mockAccommodations) {
            db.collection("accommodations")
                    .add(accommodation)
                    .addOnSuccessListener(documentReference -> {
                        // Accommodation uploaded successfully
                        Toast.makeText(AccommodationActivity.this, "Accommodation uploaded successfully.", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Failed to upload accommodation
                        Toast.makeText(AccommodationActivity.this, "Failed to upload accommodation: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
