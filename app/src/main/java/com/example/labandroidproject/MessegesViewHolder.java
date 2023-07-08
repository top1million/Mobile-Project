package com.example.labandroidproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MessegesViewHolder extends RecyclerView.ViewHolder {



    TextView titleTextView;
    TextView contentTextView;
    TextView senderTextView;


    public MessegesViewHolder(@NonNull View itemView) {
        super(itemView);

        senderTextView = itemView.findViewById((R.id.textView_sender));
        contentTextView= itemView.findViewById(R.id.textView_content);
        titleTextView = itemView.findViewById(R.id.textView_title);

    }
}