package com.example.towerdefence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;


import java.util.LinkedList;

public class Developer extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder surfaceHolder;
    public float kHeight, kWidth;
    public DevelopersActivity developersActivity;
public DeveloperButton exit;
    public Bitmap background;

    public Developer(Context context, float kWidth, float kHeight,DevelopersActivity developersActivity) {
        super(context);

        this.developersActivity=developersActivity;
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        this.kHeight = kHeight;
        this.kWidth = kWidth;
        this.kHeight = this.kHeight / 1600;
        this.kWidth = this.kWidth / 2560;


    }

    public Developer(Context context) {
        super(context);
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);




    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x,y;
        x= (int) (event.getX()/kWidth);
        y= (int) (event.getY()/kHeight);
        if(exit.cage.belonging(x,y))exit.changeImg(1,x,y);

        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            if(exit.cage.belonging(x,y))exit.pushed();
exit.changeImg(0,0,0);
        }
        try {
            Canvas canvas = this.surfaceHolder.lockCanvas();
            this.draw(canvas);
            draw(canvas);
            this.surfaceHolder.unlockCanvasAndPost(canvas);

        }catch(NullPointerException n)
        {n.printStackTrace();}


        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        background=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.developersbackground),Math.round(2560*kWidth),Math.round(1600*kHeight),true);
        exit=new DeveloperButton(new Cage(100,1350,800,1500),this,1);
        try {
            Canvas canvas = this.surfaceHolder.lockCanvas();
            this.draw(canvas);
            draw(canvas);
            this.surfaceHolder.unlockCanvasAndPost(canvas);
       /* synchronized (this) {  try {

            wait(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}*/
        }catch(NullPointerException n)
        {n.printStackTrace();}

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }


    public void calculate() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint=new Paint();
        canvas.drawBitmap(background,0,0,paint);
            exit.draw(canvas);



    }

}
