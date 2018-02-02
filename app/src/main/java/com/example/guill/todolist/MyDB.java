package com.example.guill.todolist;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyDB {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public MyDB(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public long add(String table, Map<String, String> items) {
        ContentValues values = new ContentValues();
        for (String key : items.keySet()) {
            values.put(key, items.get(key));
        }
        database.insert(table, null, values);
        return 0;
    }

    public Cursor get(String table) {
        Cursor mCursor = database.rawQuery("select * from " + table + ";", null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void delete(String id, String table) {
        database.execSQL("DELETE FROM " + table + " WHERE id=" + id + ";");
    }

    public void update(String table, Map<String, String> items) {
        String tmp = "";
        String id = "";
        boolean first = true;
        for (String key : items.keySet()) {
            if (key.compareTo("id") != 0)
                id = items.get(key);
            else {
                tmp += (first ? key + "=\"" + items.get(key) + "\"" : ", " + key + "=\"" + items.get(key) + "\"");
                first = false;
            }
        }
        database.execSQL("UPDATE " + table + " SET " + tmp + " WHERE id=\"" + id + "\";");
    }
}