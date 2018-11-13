package com.cse437.sensor_basedauthenticationapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

    private long mShakeTimestamp;
    private int mShakeCount;
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(TAG,"Entered onCreate");


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i(TAG,"Entered onSensorChanged");

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float gX = x / SensorManager.GRAVITY_EARTH;
        float gY = y / SensorManager.GRAVITY_EARTH;
        float gZ = z / SensorManager.GRAVITY_EARTH;

        Log.i(TAG,"X: "+x+" Y: "+y+" Z: "+z );
        Log.i(TAG,"gX: "+gX+" gY: "+gY+" gZ: "+gZ );

        double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);


        if (gForce > SHAKE_THRESHOLD_GRAVITY) {
            Log.i(TAG,"Entered if condition");
            final long now = System.currentTimeMillis();
            // ignore shake events too close to each other (500ms)
            if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                return;
            }

            // reset the shake count after 3 seconds of no shakes
            if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                mShakeCount = 0;
            }

            mShakeTimestamp = now;
            mShakeCount++;
            textView.setText(""+mShakeCount);

        }


    }
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//
//        Log.i(TAG,"Entered onSensorChanged");
//            long curTime = System.currentTimeMillis();
//            // only allow one update every 100ms.
//            long lastUpdate=0;
//            if ((curTime - lastUpdate) > 100) {
//                Log.i(TAG,"Entered if condition");
//                long diffTime = (curTime - lastUpdate);
//                lastUpdate = curTime;
//
//                float x = event.values[0];
//                float y = event.values[1];
//                float z = event.values[2];
//
//                Log.i(TAG,"X: "+x+" Y: "+y+" Z: "+z );
//
//
//                float last_x=0;
//                float last_y=0;
//                float last_z=0;
//                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;
//
//                if (speed > 50) {
//                    Log.i(TAG,"Speed is "+speed);
//
//                    Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
//                }
//                last_x = x;
//                last_y = y;
//                last_z = z;
//            }
//    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
