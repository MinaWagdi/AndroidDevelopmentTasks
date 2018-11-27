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

public class PressureSensor extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor Pressure;
    private static String TAG = "MINA";

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_sensor);


        textView=findViewById(R.id.textView3);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Pressure =sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensorManager.registerListener(PressureSensor.this, Pressure,SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(TAG,"Entered onCreate");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null){
            Log.i(TAG,"Pressure value is "+event.values[0]);
            textView.setText(""+event.values[0]);
        }
        else {
            textView.setText("Pressure sensor is not available on your device");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
