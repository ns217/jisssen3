package com.example.myapplication3;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager manager;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    public void  onResume() {
        super.onResume();

        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_LIGHT);

        if (sensors.size() != 0){
            Sensor sensor = sensors.get(0);
            manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

    protected  void  onPause(){
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    @Override
    public void onSensorChanged(SensorEvent arg0) {
        if (arg0.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            float x = arg0.values[0];
            float y = arg0.values[1];
            float z = arg0.values[2];
            String str = Float.toString(x)+","+ Float.toString(y)+","+ Float.toString(z);
            TextView textView = (TextView) findViewById(R.id.status_text);
            textView.setText(str);
        }

    }

}