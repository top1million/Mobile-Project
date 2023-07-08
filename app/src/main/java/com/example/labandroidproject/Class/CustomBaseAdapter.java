package com.example.labandroidproject.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.labandroidproject.R;

import java.util.ArrayList;
import java.util.List;
public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    List<Topic> topicList;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, List<Topic> topicList) {
        this.context = ctx;
        this.topicList = topicList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return topicList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_marks_list_view, null);
        }

        TextView txtViewTopic = convertView.findViewById(R.id.topicText);
        TextView txtViewQuiz = convertView.findViewById(R.id.quizText);
        TextView txtViewMidterm = convertView.findViewById(R.id.midtermText);
        TextView txtViewProject = convertView.findViewById(R.id.projectText);
        TextView txtViewFinal = convertView.findViewById(R.id.finalText);

        txtViewTopic.setText(topicList.get(position).getName());
        txtViewFinal.setText(String.valueOf(topicList.get(position).getFinalGrade()));
        txtViewMidterm.setText(String.valueOf(topicList.get(position).getMidterm()));
        txtViewQuiz.setText(String.valueOf(topicList.get(position).getQuiz()));
        txtViewProject.setText(String.valueOf(topicList.get(position).getProject()));

        return convertView;
    }

}
