package com.example.ecommerce3;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        SensorManager sensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager !=null) {
            Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

            if (lightSensor !=null) {
                sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }

        } else {
            Toast.makeText(this, "Sensor not detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_LIGHT){
            ((TextView)findViewById(R.id.txtvalues)).setText("values: "+event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}