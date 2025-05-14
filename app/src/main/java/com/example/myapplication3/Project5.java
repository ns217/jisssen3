package com.example.myapplication3;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Project5 extends Activity implements View.OnClickListener {

    final int NUMOF_MUSIC = 2;
    int sounds[];
    private SoundPool pool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) this.findViewById(R.id.button);
        b.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sounds = new int[NUMOF_MUSIC];

        pool = new SoundPool(NUMOF_MUSIC, AudioManager.STREAM_MUSIC,0);
        sounds[0]=pool.load(this,R.raw.sample,1);
    }

    @Override
    public void onClick(View v) {
        pool.play(sounds[0],1.0F,1.0F,0,0,1.0F);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pool.stop(sounds[0]);
        pool.release();
    }
}
