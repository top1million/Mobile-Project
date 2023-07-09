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

    public AccommodationAdapter(List<Accommodation> accommodationList) {
        this.accommodationList = accommodationList;
    }

    @NonNull
    @Override
    public AccommodationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accommodation, parent, false);
        return new AccommodationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccommodationViewHolder holder, int position) {
        Accommodation accommodation = accommodationList.get(position);

        // Set the data to the views in the view holder
        holder.textViewPhoneNumber.setText(accommodation.getPhoneNumber());
        holder.textViewLocation.setText(accommodation.getLocation());
        holder.textViewArea.setText(accommodation.getArea());
        holder.textViewNumberOfRooms.setText(String.valueOf(accommodation.getNumberOfRooms()));

        // Load the image using an image loading library (e.g., Glide or Picasso)
        Glide.with(holder.imageView.getContext())
                .load(accommodation.getImageUrl())
                .placeholder(R.drawable.programming)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return accommodationList.size();
    }
}
