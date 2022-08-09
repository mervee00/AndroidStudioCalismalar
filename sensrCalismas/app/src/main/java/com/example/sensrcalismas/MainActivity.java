package com.example.sensrcalismas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Sensor accelerometor;
    SensorManager sm;
    TextView accelaration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,accelerometor,SensorManager.SENSOR_DELAY_NORMAL);
        accelaration=(TextView) findViewById(R.id.accelarate1);

    }

    @Override
    protected void onPause(){
        super.onPause();
        sm.unregisterListener(this);

    }

    @Override
    protected void onSensorChanged(SensorEvent args0){
        if(args0.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float[] values=args0.values;
            float x=values[0];
            float y=values[1];
            float z=values[2];
            accelaration.setText("x: "+x+"\ny:"+y+"\nz:"+z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
    public void gec(View view) {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }
}