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

public class Levels extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder surfaceHolder;
    public float kHeight, kWidth;
public LevelActivity levelActivity;
    public LinkedList<LevelButton>levelButtons=new LinkedList<LevelButton>();
    public Images images;
    public Levels(Context context, float kWidth, float kHeight,LevelActivity levelActivity) {
        super(context);
        this.images=images;
this.levelActivity=levelActivity;
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        this.kHeight = kHeight;
        this.kWidth = kWidth;
        this.kHeight = this.kHeight / 1600;
        this.kWidth = this.kWidth / 2560;

    }

    public Levels(Context context) {
        super(context);
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);




    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

int x,y;
        x= (int) (event.getX()/kWidth);
        y= (int) (event.getY()/kHeight);
        for(int i=0;i<levelButtons.size();i++)
        {
        if(levelButtons.get(i).cage.belonging(x,y))levelButtons.get(i).pushed();
        }

        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        levelButtons.add(new LevelButton(new Cage(100,100,800,230),this,0));
        levelButtons.add(new LevelButton(new Cage(100,300,800,430),this,1));
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
for(int i=0; i<levelButtons.size();i++)
{
    levelButtons.get(i).draw(canvas);
}


    }
    public void startGame()
    {

        Intent intent=   new Intent(levelActivity.getApplication(),GameActivity.class);
     //   LinkedList<Images> imageses=new LinkedList<Images>();
     ////   imageses.add(images);
     //   intent.putExtra("images",imageses);
        levelActivity.startActivity(intent);


    }

}
