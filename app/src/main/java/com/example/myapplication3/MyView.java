package com.example.myapplication3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
    }


    @Override
    protected void onDraw(@NonNull Canvas canvas){
        super.onDraw(canvas);

        @SuppressLint("DrawAllocation") Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(10);

        canvas.drawLine(10,20,30,40,p);
    }

}
