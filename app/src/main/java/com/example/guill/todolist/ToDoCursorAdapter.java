package com.example.guill.todolist;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CursorAdapter;

/***
 * ToDoCursorAdapter is a tool class, that is usefull to use cursor in lists
 */
public class ToDoCursorAdapter extends CursorAdapter {

    /***
     * ToDoCursorAdapter is called at the class creation
     * It called it super class creator
     * @param context Parent context
     * @param cursor DB cursor
     */
    public ToDoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    /***
     * newView
     * @param context
     * @param cursor
     * @param parent
     * @return
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
    }

    public String printable_date(String in) {
        String out = "";
        String[] tmp = in.split("/");
        tmp[0] = tmp[0].length() == 1 ? "0" + tmp[0] : tmp[0];
        tmp[1] = tmp[1].compareTo("0") == 0 ? "1" : tmp[1];
        tmp[1] = tmp[1].length() == 1 ? "0" + tmp[1] : tmp[1];
        out = tmp[0] + "/" + tmp[1] + "/" + tmp[2];
        return out;
    }

    /***
     * Setup the cursor to fill the list
     * @param view
     * @param context
     * @param cursor
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView item_id = (TextView) view.findViewById(R.id.item_id);
        TextView item_title = (TextView) view.findViewById(R.id.item_title);
        TextView item_state = (TextView) view.findViewById(R.id.item_state);
        TextView item_date = (TextView) view.findViewById(R.id.item_date);
        String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String date = printable_date(cursor.getString(cursor.getColumnIndexOrThrow("date")));
        String state = cursor.getInt(cursor.getColumnIndexOrThrow("state")) == 1 ? "Done" : "Todo";
        item_id.setText(id);
        item_title.setText(String.valueOf(title));
        item_state.setText(String.valueOf(state));
        item_date.setText(String.valueOf(date));
        if (Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("state"))) == 1)
            item_state.setTextColor(Color.parseColor("#FF43B05C"));
        else
            item_state.setTextColor(Color.parseColor("#FFED0400"));
    }
}
