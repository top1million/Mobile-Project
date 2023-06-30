package com.example.labandroidproject;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.labandroidproject.Class.Message;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessegesAdapter extends RecyclerView.Adapter<MessegesViewHolder> {


    Context context;
    List<Message> items;


    public MessegesAdapter(Context context, List<Message> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MessegesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessegesViewHolder(LayoutInflater.from(context).inflate(R.layout.messege_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessegesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.titleTextView.setText(items.get(position).getTitle());
        holder.contentTextView.setText(items.get(position).getMessage());
        holder.senderTextView.setText(items.get(position).getSender());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}