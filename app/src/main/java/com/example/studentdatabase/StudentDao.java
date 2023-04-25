package com.example.studentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentDao extends SQLiteOpenHelper {

    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_STUDENT_PROGRAM = "STUDENT_PROGRAM";
    public static final String COLUMN_STUDENT_START_YEAR = "STUDENT_START_YEAR";
    public static final String COLUMN_STUDENT_GRADUATION_YEAR = "STUDENT_GRADUATION_YEAR";
    public static final String COLUMN_ID = "ID";


    public StudentDao(@Nullable Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STUDENT_NAME + " TEXT, " + COLUMN_STUDENT_PROGRAM + " TEXT, " + COLUMN_STUDENT_START_YEAR +
                " INT, " + COLUMN_STUDENT_GRADUATION_YEAR + " INT)";

        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addStudent(Student student){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STUDENT_NAME, student.getName());
        cv.put(COLUMN_STUDENT_PROGRAM, student.getProgram());
        cv.put(COLUMN_STUDENT_START_YEAR, student.getStartYear());
        cv.put(COLUMN_STUDENT_GRADUATION_YEAR, student.getGraduationYear());

        long insert = db.insert(STUDENT_TABLE, null, cv);
        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + STUDENT_TABLE + " WHERE " + COLUMN_ID + " = " + student.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }

    }

    public List<Student> getAllStudents(){

        List<Student> studentList = new ArrayList<>();

        String queryString = "SELECT * FROM " + STUDENT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int gameID = cursor.getInt(0);
                String name = cursor.getString(1);
                String program = cursor.getString(2);
                int startYear = cursor.getInt(3);
                int graduationYear = cursor.getInt(4);

                Student student = new Student(gameID, name, program, startYear, graduationYear);

                studentList.add(student);
            } while(cursor.moveToNext());

        } else {

        }

        cursor.close();
        db.close();

        return studentList;

    }
    
}
