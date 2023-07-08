package com.example.labandroidproject;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labandroidproject.Class.Trainee;

public class traineeAdapter extends RecyclerView.Adapter<traineeAdapter.ViewHolder>{
    String [] traineeFirstNames = new String[100];
    String [] traineeLastNames = new String[100];
    int [] traineeImages = new int[100];
    public traineeAdapter(String[] traineeFirstNames, String[] traineeLastNames, int[] traineeImages){
        this.traineeFirstNames = traineeFirstNames;
        this.traineeLastNames = traineeLastNames;
        this.traineeImages = traineeImages;
    }

    @NonNull
    @Override
    public traineeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull traineeAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.image);
        TextView firstName = cardView.findViewById(R.id.firstName);
        TextView lastName = cardView.findViewById(R.id.lastName);
        Drawable dr = ContextCompat.getDrawable(cardView.getContext(), traineeImages[position]);
        imageView.setImageDrawable(dr);
        firstName.setText(traineeFirstNames[position]);
        lastName.setText(traineeLastNames[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}
