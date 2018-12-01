package com.cse437.sensor_basedauthenticationapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private static String TAG="MINA";
    private SensorManager sensorManager;
    Sensor accelerometer;
    TextView textView;
    long curTime=0;
    long lastUpdate=0;
    float last_x=0;
    float last_y=0;
    float last_z=0;
    int shake_count=0;
    boolean PinDetected=false;
    boolean shakeOccured=false;

    private static final float SHAKE_THRESHOLD = 1300;
    private static final float Shake_time_diff1 = 200;
    private static final float Shake_time_diff2 = 950;


    TextView progress_bar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        progress_bar=findViewById(R.id.progress_bar_text);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(TAG,"Entered onCreate");


    }
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float speed = Math.abs(x + y + z - last_x - last_y - last_z) / 200*10000;
        if(speed>SHAKE_THRESHOLD){
            Log.i(TAG,"Shake detected");

            curTime = System.currentTimeMillis();
            long diff = curTime-lastUpdate;

            Log.i(TAG,"diff between curTime and lastUpdate is "+diff);

            if(diff<Shake_time_diff2 && diff>Shake_time_diff1){
                if(shake_count<3){
                    shake_count++;
                    //Log.i(TAG,"shake counts is "+shake_count);
                    //progress_bar.setText(shake_count+"    ");
                }

            }
            //First shake
            else{
                shakeOccured=true;
                shake_count= 1;
                //Log.i(TAG,"shake_count"+shake_count);

            }
            lastUpdate=curTime;

        }
        else if(System.currentTimeMillis()-lastUpdate>Shake_time_diff2){
            //pin detected
            if(shakeOccured){
                progress_bar.append("  "+shake_count);
                Log.i(TAG,"shake_count"+shake_count);
                shakeOccured=false;
            }


        }


        last_x=x;
        last_y=y;
        last_z=z;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
