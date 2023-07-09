package com.example.labandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity {
    private TextView calendarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);


        //uploadData();

        fetchDataAndFillActivity();
        // Find the TextView in the layout
        calendarTextView = findViewById(R.id.calendarTextView);

        // Set the academic calendar text
        String academicCalendarText = getAcademicCalendarText();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(academicCalendarText);

        // Apply color to the semester titles
        int fallSemesterStart = academicCalendarText.indexOf("Fall Semester:");
        int fallSemesterEnd = fallSemesterStart + "Fall Semester:".length();
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),
                fallSemesterStart, fallSemesterEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int winterSemesterStart = academicCalendarText.indexOf("Winter Semester:");
        int winterSemesterEnd = winterSemesterStart + "Winter Semester:".length();
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),
                winterSemesterStart, winterSemesterEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the formatted text to the TextView
        calendarTextView.setText(spannableStringBuilder);
    }

    private String getAcademicCalendarText() {
        StringBuilder calendarText = new StringBuilder();

        // Fall Semester
        calendarText.append("Fall Semester:\n")
                .append("- September 1: Semester begins\n")
                .append("- September 5: Labor Day (No classes)\n")
                .append("- September 10: Last day to add/drop courses\n")
                .append("- October 10-14: Midterm exams\n")
                .append("- November 24-26: Thanksgiving break (No classes)\n")
                .append("- December 6-17: Final exams\n")
                .append("- December 17: Semester ends\n\n");

        // Winter Semester
        calendarText.append("Winter Semester:\n")
                .append("- January 3: Semester begins\n")
                .append("- January 16: Martin Luther King Jr. Day (No classes)\n")
                .append("- January 20: Last day to add/drop courses\n")
                .append("- February 20-24: Midterm exams\n")
                .append("- March 10-17: Spring break\n")
                .append("- April 25-May 5: Final exams\n")
                .append("- May 5: Semester ends");

        return calendarText.toString();
    }
    private void fetchDataAndFillActivity() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Assuming you have a "calendar" collection in Firestore with a document called "academicCalendar"
        db.collection("calendar").document("academicCalendar")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String academicCalendarText = documentSnapshot.getString("calendarText");
                        if (academicCalendarText != null) {
                            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(academicCalendarText);
                            // Apply color to the semester titles and set the formatted text to the TextView
                            int fallSemesterStart = academicCalendarText.indexOf("Fall Semester:");
                            int fallSemesterEnd = fallSemesterStart + "Fall Semester:".length();
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),
                                    fallSemesterStart, fallSemesterEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                            int winterSemesterStart = academicCalendarText.indexOf("Winter Semester:");
                            int winterSemesterEnd = winterSemesterStart + "Winter Semester:".length();
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),
                                    winterSemesterStart, winterSemesterEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                            calendarTextView.setText(spannableStringBuilder);
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle any errors that occur during fetching data
                });
    }
    private void uploadData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Assuming you have a "calendar" collection in Firestore with a document called "mockData"
        String academicCalendarText = getAcademicCalendarText();
        Map<String, Object> data = new HashMap<>();
        data.put("calendarText", academicCalendarText);
        db.collection("calendar").document("mockData")
                .set(data)
                .addOnSuccessListener(aVoid -> {
                    // Data uploaded successfully
                })
                .addOnFailureListener(e -> {
                    // Handle any errors that occur during data upload
                });
    }

}


