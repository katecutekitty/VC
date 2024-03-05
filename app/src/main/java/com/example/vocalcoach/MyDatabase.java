package com.example.vocalcoach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    private static String DB_NAME = "tasksdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tasks";
    private static final String ID_COL = "_id";
    private static final String NAME_COL = "task_name";
    private static final String ICON_COL = "iconres";
    private static final String URL_COL = "url";
    private static final String TYPE_COL = "type";
    //private static final String ISFAV_COL = "isFav";


    public MyDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDB) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + ICON_COL + " TEXT,"
                + URL_COL + " TEXT,"
                + TYPE_COL + " INTEGER)";
                //+ ISFAV_COL + " INTEGER)";
        sqLiteDB.execSQL(query);
    }

    public ArrayList<Task> readTasks(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Task> taskArrayList = new ArrayList<>();
        if (cur.moveToFirst()){
            do {
                taskArrayList.add(new Task(cur.getString(1), cur.getInt(1), cur.getString(1), cur.getInt(1), cur.getInt(1)));
            } while (cur.moveToNext());

        }
        cur.close();
        return taskArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void putTask(Task task){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", task.getName());
        cv.put("type", task.getType());
        cv.put("url", task.getUrl());
        cv.put("iconres", task.getIconResource());
        //cv.put("isFav", task.isFav());
        db.insert("tasks", null, cv);
        db.close();
    }

    public void clear(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}
