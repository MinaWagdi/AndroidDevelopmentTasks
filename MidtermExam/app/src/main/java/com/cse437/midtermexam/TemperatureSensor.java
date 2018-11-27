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

public class TemperatureSensor extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor Temperature;
    private static String TAG = "MINA";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_sensor);

        textView=findViewById(R.id.textView3);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Temperature =sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorManager.registerListener(TemperatureSensor.this, Temperature,SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(TAG,"Entered onCreate");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            Log.i(TAG,"Temp value is "+event.values[0]);
            textView.setText(""+event.values[0]);
        } else {
            textView.setText("Temperature sensor is not available on your device");
        }
    }
}
