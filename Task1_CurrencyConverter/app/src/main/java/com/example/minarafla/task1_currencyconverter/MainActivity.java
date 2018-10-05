package com.example.minarafla.task1_currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Convert();
            }
        });
    }

    public void Convert(){
        double result=0;
        EditText valueText = (EditText)findViewById(R.id.editText2);
        Double value = Double.parseDouble(valueText.getText().toString());

        Spinner fromSpinner = (Spinner)findViewById(R.id.spinner);
        String fromCurrency = fromSpinner.getSelectedItem().toString();

        Spinner toSpinner = (Spinner)findViewById(R.id.spinner3);
        String toCurrency = toSpinner.getSelectedItem().toString();

        TextView resultView = (TextView)findViewById(R.id.textView6);
        resultView.setText(""+result);

        if(fromCurrency.equalsIgnoreCase("EGP")){
            if(toCurrency.equalsIgnoreCase("Dollars")){
                result=value*18;
            }
            else{
                result=value;
            }
        }
        else{
            if(toCurrency.equalsIgnoreCase("EGP")){
                result=value*10;
            }
            else{
                result=value;
            }

        }

        resultView.setText(""+result);





    }
}
