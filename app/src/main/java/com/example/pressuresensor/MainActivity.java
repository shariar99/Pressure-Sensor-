package com.example.pressuresensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private Boolean isAbailable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null){
            pressureSensor=sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            isAbailable = true;

        }
        else {
            textView.setText("This sensor is not  available");
            isAbailable = false;

        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText(event.values[0]+"hPa");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isAbailable){
            sensorManager.registerListener(this,pressureSensor,SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isAbailable){

            sensorManager.unregisterListener(this);
        }
    }
}