package com.cse437.speechengine;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Events extends AppCompatActivity {

    ArrayList<String> Records;
    int recordsArrayIndex=0;
    ListView events_list;
    ListAdapter events_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Intent intent = getIntent();
        Records=new ArrayList<String>();

        events_list_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Records);
        events_list=findViewById(R.id.events_list);
        events_list.setAdapter(events_list_adapter);
        SetRecordsArray();
        //SetRecordsArray();



    }

    public void SetRecordsArray(){
        Cursor cursor = MainActivity.myDb.getAllRows();
        displayRecordSet(cursor);
        //records_view.setText(Records);
    }

    private void displayRecordSet(Cursor cursor) {

        // populate the message from the cursor

        // Reset cursor to start, checking to see if there's data:
        if (cursor.moveToFirst()) {
            do {
                // Process the data:

                int id = cursor.getInt(DBAdapter.COL_ROWID);
                String name = cursor.getString(DBAdapter.COL_EventName);
                String daynum = cursor.getString(DBAdapter.COL_DayNum);
                String day = cursor.getString(DBAdapter.COL_Day);
                String month = cursor.getString(DBAdapter.COL_Month);
                String time = cursor.getString(DBAdapter.COL_Time);
                String year = cursor.getString(DBAdapter.COL_Year);
                Log.i(MainActivity.TAG,"Passed");

                // Append data to the message:
                String r = "id=" + id
                        +", name=" + name
                        +", daynum" + daynum
                        +", day" + day
                        +", month" + month
                        +", time" + time
                        +", year" + year
                        +"\n";
                Log.i(MainActivity.TAG,"Passed2");
                Records.add(recordsArrayIndex,r);
                Log.i(MainActivity.TAG,"Passed3");
                // [TO_DO_B6]
                // Create arraylist(s)? and use it(them) in the list view
                recordsArrayIndex++;
            } while(cursor.moveToNext());
        }
        Log.i(MainActivity.TAG,"Passed4");
        // Close the cursor to avoid a resource leak.
        cursor.close();


        // [TO_DO_B7]
        // Update the list view

        // [TO_DO_B8]
        // Display a Toast message
    }
}
