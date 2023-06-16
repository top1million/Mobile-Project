package com.example.labandroidproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDBHelper extends SQLiteOpenHelper {

    public CourseDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the "courses" table
        String createCoursesTableQuery = "CREATE TABLE courses (" +
                "courseNumber INTEGER PRIMARY KEY AUTOINCREMENT," +
                "courseTitle TEXT," +
                "courseMainTopics TEXT," +
                "photo TEXT," +
                "instructorName TEXT," +
                "registrationDeadline TEXT," +
                "courseStartDate TEXT," +
                "courseSchedule TEXT," +
                "venue TEXT" +
                ")";
        sqLiteDatabase.execSQL(createCoursesTableQuery);

        // Create the "prerequisites" table
        String createPrerequisitesTableQuery = "CREATE TABLE prerequisites (" +
                "courseNumber INTEGER," +
                "prerequisiteCourseNumber INTEGER," +
                "FOREIGN KEY(courseNumber) REFERENCES courses(courseNumber)," +
                "FOREIGN KEY(prerequisiteCourseNumber) REFERENCES courses(courseNumber)" +
                ")";


        sqLiteDatabase.execSQL(createPrerequisitesTableQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
