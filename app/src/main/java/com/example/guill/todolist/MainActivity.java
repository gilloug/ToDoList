package com.example.guill.todolist;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDB my_db = new MyDB(this);
        setContentView(R.layout.activity_main);
        lvItems = findViewById(R.id.lvItems);
        Cursor cursor = my_db.get("tables");
        ListView lvItems = findViewById(R.id.lvItems);
        ToDoCursorAdapter todoAdapter = new ToDoCursorAdapter(this, cursor);
        lvItems.setAdapter(todoAdapter);
        setupListViewListener();
        my_db.close();
    }

    private void setupListViewListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                        Bundle b = new Bundle();
                        b.putString("_id", ((TextView) item.findViewById(R.id.item_id)).getText().toString());
                        intent.putExtras(b);
                        finish();
                        startActivity(intent);
                    }
                });
    }
}