package com.example.myapplication3;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class MyActivity extends Activity implements LocationListener {

    private LocationManager manager;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (LocationManager)getSystemService(LOCATION_SERVICE);
    }

    public void  onResume() {
        super.onResume();
        final int FREQUENCY = 8000;

        int bufsize = AudioRecord.getMinBufferSize(FREQUENCY, AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT);
        short[] buf = new short[bufsize];
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        AudioRecord rec = new AudioRecord(MediaRecorder.AudioSource.MIC,FREQUENCY,AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT,bufsize);

        rec.startRecording();

        rec.stop();
        rec.release();

        rec.read(buf,0,bufsize);

        int datasize = rec.read(buf, 0, bufsize),max=0;
        for (int i=0; i<datasize; i++){
            if ((buf[i]>0) && (buf[i]>max)){ max=buf[i];}
            if ((buf[i]<0) && (buf[i]<-max)){ max=-buf[i];}}
        String str = Integer.toString(max);
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }



    protected void onPause(){
        super.onPause();
        manager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        Toast.makeText(this,String.format("%.3f %.3f", lat,lng),Toast.LENGTH_SHORT).show();
    }
}