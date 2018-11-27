package com.cse437.midtermexam;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    RadioButton temp_radio;
    RadioButton proximity_radio;
    RadioButton light_radio;
    RadioButton pressure_radio;
    RadioGroup radioGroup;


    Sensor temperature;
    SensorManager sensorManager;
    private static String TAG="MINA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.MeasureBtn);
        radioGroup=findViewById(R.id.RGroup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
                String selectedtext = (String) radioButton.getText();
                if(selectedtext.equalsIgnoreCase("Pressure")){
                    Intent intent = new Intent(MainActivity.this,PressureSensor.class);
                    startActivity(intent);

                }else if(selectedtext.equalsIgnoreCase("Temperature")){
                    Intent intent = new Intent(MainActivity.this,TemperatureSensor.class);
                    startActivity(intent);

                }
                else if(selectedtext.equalsIgnoreCase("Proximity")){
                    Intent intent = new Intent(MainActivity.this,ProximitySensor.class);
                    startActivity(intent);

                }
                else if(selectedtext.equalsIgnoreCase("Light")){
                    Intent intent = new Intent(MainActivity.this,LightSensor.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this, "Please select a sensor", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
