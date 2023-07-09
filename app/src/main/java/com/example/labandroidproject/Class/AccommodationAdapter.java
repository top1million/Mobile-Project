package com.example.labandroidproject.Class;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.labandroidproject.R;

import java.util.List;

public class AccommodationAdapter extends RecyclerView.Adapter<AccommodationViewHolder> {

    private List<Accommodation> accommodationList;

    // constructor that takes a list of accommodations as a parameter and assigns it to the accommodationList member variable.
    public AccommodationAdapter(List<Accommodation> accommodationList) {
        this.accommodationList = accommodationList;
    }
    //It inflates the layout for each item in the RecyclerView using the LayoutInflater from the parent's context.
    @NonNull
    @Override
    public AccommodationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accommodation, parent, false);
        return new AccommodationViewHolder(view);
    }
    //bind the data to the views in each AccommodationViewHolder. It gets the accommodation object at the given position from the accommodationList and sets the corresponding data to the views in the view holder.
    @Override
    public void onBindViewHolder(@NonNull AccommodationViewHolder holder, int position) {
        Accommodation accommodation = accommodationList.get(position);

        // Set the data to the views in the view holder
        holder.textViewPhoneNumber.setText(accommodation.getPhoneNumber());
        holder.textViewLocation.setText(accommodation.getLocation());
        holder.textViewArea.setText(accommodation.getArea());
        holder.textViewNumberOfRooms.setText(String.valueOf(accommodation.getNumberOfRooms()));

        // Load the image using an image loading library GLIDE
        Glide.with(holder.imageView.getContext())
                .load(accommodation.getImageUrl())
                .placeholder(R.drawable.programming)
                .into(holder.imageView);
    }

    //return the size of the accommodationList
    @Override
    public int getItemCount() {
        return accommodationList.size();
    }
}
