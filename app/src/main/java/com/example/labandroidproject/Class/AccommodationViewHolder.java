package com.example.labandroidproject.Class;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labandroidproject.R;

public class AccommodationViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textViewPhoneNumber;
    TextView textViewLocation;
    TextView textViewArea;
    TextView textViewNumberOfRooms;

    AccommodationViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
        textViewLocation = itemView.findViewById(R.id.textViewLocation);
        textViewArea = itemView.findViewById(R.id.textViewArea);
        textViewNumberOfRooms = itemView.findViewById(R.id.textViewNumberOfRooms);
    }
}