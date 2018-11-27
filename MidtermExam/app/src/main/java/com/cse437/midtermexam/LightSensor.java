package com.cse437.midtermexam;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LightSensor extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor Light;
    private static String TAG = "MINA";
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        textView=findViewById(R.id.textView3);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Light=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(LightSensor.this,Light,SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(TAG,"Entered onCreate");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            Log.i(TAG,"Ligh value is "+event.values[0]);
            textView.setText(""+event.values[0]);
        } else {
           textView.setText("Light sensor is not available on your device");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
