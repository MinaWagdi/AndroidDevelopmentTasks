package com.cse437.speechengine;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView testText;
    ImageView mic_imgview;
    Button displayCalendarBtn;
    String EventString;
    final static String TAG = "SPLINTER";
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Mina","In the main activity");

        testText=findViewById(R.id.testText);
        mic_imgview =findViewById(R.id.micView);
        displayCalendarBtn=findViewById(R.id.DisplayCalendarBtn);
        displayCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalendar();
            }
        });

        mic_imgview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                getSpeech();
            }
        });
    }

    public void getSpeech(){
        testText.setText("Startinggg :)) ");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        try{
            startActivityForResult(intent,10);
        }catch (ActivityNotFoundException ex){
            Toast.makeText(this, "NOT SUPPORTED BY THIS DEVICE", Toast.LENGTH_SHORT).show();
        }


    }

    public void displayCalendar(){
        cursor=getContentResolver().query(CalendarContract.Events.CONTENT_URI,null,null,null);
        while(cursor.moveToNext()){
            if(cursor!=null){
                int id1 = cursor.getColumnIndex(CalendarContract.Events._ID);
                int id2 = cursor.getColumnIndex(CalendarContract.Events.TITLE);
                int id3 = cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION);
                int id4 = cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION);

                String idValue = cursor.getColumnName(id1);
                String titleValue = cursor.getColumnName(id2);
                String descriptionValue = cursor.getColumnName(id3);
                String locationValue = cursor.getColumnName(id4);

                Toast.makeText(this, idValue+" "+titleValue+" "+descriptionValue+" "+locationValue, Toast.LENGTH_LONG).show();
            }
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK && data!=null){
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    testText.setText(""+result.get(0));
                    EventString=""+result.get(0);
                    Intent intent = new Intent(this,AddEvent.class);
                    intent.putExtra("event_details",""+EventString);
                    startActivity(intent);

                }
                break;
        }
    }


}
