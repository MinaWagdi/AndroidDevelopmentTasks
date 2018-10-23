package com.cse437.quiz1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView t;
    static DBAdapter myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDb = new DBAdapter(this);
        myDb.open();
    }

    @Override
    protected void onStart() {
        super.onStart();
        InsertCoursesData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        t = findViewById(R.id.textView);

        try{
        Cursor res = getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "No data found");
            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            buffer.append("ID : " + res.getString(DBAdapter.COL_ROWID) + "\n");
            buffer.append("Course Number : " + res.getString(DBAdapter.COL_Number) + "\n");
            buffer.append("Course Name : " + res.getString(DBAdapter.COL_Name) + "\n");
            while (res.moveToNext()) {
                buffer.append("ID : " + res.getString(DBAdapter.COL_ROWID) + "\n");
                buffer.append("Course Number : " + res.getString(DBAdapter.COL_Number) + "\n");
                buffer.append("Course Name : " + res.getString(DBAdapter.COL_Name) + "\n");
            }
            showMessage("Data", buffer.toString());
        }
        }catch(Exception ex){
            Log.i("EXCEPTION","message : "+ex.getMessage()+"class :"+ex.getClass());
        }
    }

    public void showMessage(String T, String message){
        t.setText(message);
    }

    public void InsertCoursesData(){
        myDb.insertRow("CSE115","Digital Design");
        myDb.insertRow("CSE125","Computer Programming(1)");
        myDb.insertRow("CSE127","Data Structures and Algorithms");
        myDb.insertRow("CSE221","Object Oriented Analysis and Design");
    }


    Cursor getAllData(){
        return myDb.getAllRows();
    }

    static public void closeDB() {
        myDb.close();
    }

    static public long insertData(String Code_Number, String Code_Name) {

        long newId = myDb.insertRow(Code_Number,Code_Name);
        if(newId==-1){
            return  0;
        }
        else{
            return 1;
        }
    }
}
