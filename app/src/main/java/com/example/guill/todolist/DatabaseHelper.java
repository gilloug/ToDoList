package com.example.guill.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shopping";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "create table tables(id integer primary key, name text not null, description text not null, count integer);" +
                    "create table other(id integer primary key, name text not null, description text not null, count integer);" +
                    "create table food(id integer primary key, name text not null, description text not null, count integer);" +
                    "INSERT INTO tables (id, name, description, count) VALUES (0, \"other\", \"Other types of items\", 0" +
                    "INSERT INTO tables (id, name, description, count) VALUES (1, \"food\", \"Edibles items\", 0";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS ToDoLists");
        onCreate(database);
    }
}