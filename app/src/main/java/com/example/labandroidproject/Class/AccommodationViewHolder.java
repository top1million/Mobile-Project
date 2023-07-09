package com.example.labandroidproject.Class;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labandroidproject.R;
//It serves as a container for the views that represent an item in the RecyclerView.
public class AccommodationViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textViewPhoneNumber;
    TextView textViewLocation;
    TextView textViewArea;
    TextView textViewNumberOfRooms;
    //item view represents the layout of the item in the RecyclerView
    AccommodationViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
        textViewLocation = itemView.findViewById(R.id.textViewLocation);
        textViewArea = itemView.findViewById(R.id.textViewArea);
        textViewNumberOfRooms = itemView.findViewById(R.id.textViewNumberOfRooms);
    }
}