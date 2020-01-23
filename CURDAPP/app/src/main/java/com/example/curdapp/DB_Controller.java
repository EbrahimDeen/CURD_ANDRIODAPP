package com.example.curdapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DB_Controller extends SQLiteOpenHelper {


    public DB_Controller(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STUDENT( ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE, LASTNAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STUDENT;");
        onCreate(db);
    }
    public void insert_student(String fristname,String lastname){
        ContentValues contentValues =new ContentValues();
        contentValues.put("FIRSTNAME",fristname);
        contentValues.put("LASTNAME",lastname);
        this.getWritableDatabase().insertOrThrow("STUDENT","",contentValues);
    }
    public void delete_student(String fristname){
        this.getWritableDatabase().delete("STUDENT","FIRSTNAME='"+fristname+"'",null);
    }
    public void update_Student(String OLD_Firstname,String NEW_Firstname){
        this.getWritableDatabase().execSQL("UPDATE STUDENT SET FIRSTNAME='"+NEW_Firstname+"'WHERE FIRSTNAME'"+OLD_Firstname+"'");
    }
    public void list_All_students(TextView textView){
        Cursor cursor =this.getReadableDatabase().rawQuery("SELECT * FROM STUDENT",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n'");
        }
    }
}
