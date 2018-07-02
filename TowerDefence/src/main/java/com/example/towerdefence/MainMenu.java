package com.example.towerdefence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class MainMenu extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder surfaceHolder;
    public float kHeight, kWidth;
    public MainMenuActivity mainMenuActivity;
    public Bitmap backgroundBitmap;
    public MainMenuThread mainMenuThread;
    public Tower1 tower1=new Tower1();
    public Tower2 tower2=new Tower2();
    public Tower3 tower3= new Tower3();
    public Tower4 tower4 =new Tower4();
    public Tower5 tower5= new Tower5();
    public Tower6 tower6 =new Tower6();
    public Tower7 tower7= new Tower7();
    public Tower8 tower8= new Tower8();
    public Tower9 tower9 =new Tower9();
    public Tower10 tower10= new Tower10();
    public Enemy1 enemy1=new Enemy1();
    public Enemy2 enemy2= new Enemy2();
    public  LinkedList<LinkedList<Cage>> path=new LinkedList<LinkedList<Cage>>();
    public LinkedList<Cage> web= new LinkedList<Cage>();

    public int launch=0;
    public Bitmap loadingBitmap;
    public Paint paint;
    private LoadingThreadImages loadingThreadImages=new LoadingThreadImages(this);
    public LinkedList<MainMenuButton> mainMenuButtons= new LinkedList<MainMenuButton>();
public boolean isLoaded=false;
    Bitmap enemyBitmap;
    public boolean isTouching=false;
    Time time;
    public LinkedList<EnemyMovingPicture> enemyQueue = new LinkedList<EnemyMovingPicture>();
    public LinkedList<EnemyMovingPicture> enemies = new LinkedList<EnemyMovingPicture>();

    public MainMenu(Context context, float kWidth, float kHeight,MainMenuActivity mainMenuActivity) {
        super(context);
        this.mainMenuActivity=mainMenuActivity;
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        this.kHeight = kHeight;
        this.kWidth = kWidth;
        this.kHeight = this.kHeight / 1600;
        this.kWidth = this.kWidth / 2560;
        enemyBitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.enemy3), Math.round(160* this.kWidth), Math.round(140 * this.kHeight), true);
        backgroundBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.mainmenubackground), Math.round(2560* this.kWidth), Math.round(1600 * this.kHeight), true);
    }

    public MainMenu(Context context) {
        super(context);
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);




    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isLoaded) {
            int x, y;
            x = Math.round(event.getX() / kWidth);
            y = Math.round(event.getY() / kHeight);
            isTouching = true;
            for (int i = 0; i < mainMenuButtons.size(); i++) {
                mainMenuButtons.get(i).changeImg(1, x, y);
            }
            if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                isTouching = false;
                for (int i = 0; i < mainMenuButtons.size(); i++) {
                    mainMenuButtons.get(i).changeImg(0, x, y);
                }
            }
            for(int i=0;i<enemies.size();i++)
            {
                enemies.get(i).isBelonging(x,y);
            }
            if (!isTouching) {
                for (int i = 0; i < mainMenuButtons.size(); i++) {
                    if (mainMenuButtons.get(i).cage.belonging(x, y))
                        mainMenuButtons.get(i).pushed();
                    {

                    }

                }

            }
        }

        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        if (launch == 0) {
            paint = new Paint();
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "Sochi2014 Regular.ttf");
            paint.setTypeface(typeface);
            loadingBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.loading), Math.round(2560 * kWidth), Math.round(1600 * kHeight), true);
            loadingThreadImages.start();
            time = new Time(this);

        }


        if (launch == 0) {
            mainMenuActivity.isOn = true;
            mainMenuThread = new MainMenuThread(this);
            mainMenuThread.start();
            mainMenuButtons.add(new MainMenuButton(new Cage(1810, 250, 2510, 400), this, 1));
            mainMenuButtons.add(new MainMenuButton(new Cage(1810, 450, 2510, 600), this, 2));
            mainMenuButtons.add(new MainMenuButton(new Cage(1810, 650, 2510, 800), this, 3));
            mainMenuButtons.add(new MainMenuButton(new Cage(1810, 850, 2510, 1000), this, 4));
            mainMenuButtons.add(new MainMenuButton(new Cage(1810, 1050, 2510, 1200), this, 5));
            mainMenuButtons.add(new MainMenuButton(new Cage(1810, 1250, 2510, 1500), this, 6));
            for (int i = -1; i <= 12; i++) {
                for (int j = -1; j <= 7; j++) {
                    web.add(new Cage(i * 160, j * 140+620, (i + 1) * 160, (j + 1) * 140+620));
                }
            }
            try {
                Scanner in= new Scanner(this.getContext().getAssets().open("main_menu.gs"));
                int n,m,x,y;
                n=in.nextInt();
                for(int j=1;j<=n;j++)
                {path.add(new LinkedList<Cage>());
                    m=in.nextInt();
                    for(int i=1;i<=m;i++)
                    {
                        x=in.nextInt();
                        y=in.nextInt();
                        for(int h=0;h<web.size();h++)
                        {
                            if(web.get(h).belonging(x,y)){path.get(j-1).add(web.get(h));break;}

                        }

                    }

                }
                enemyQueue.add(new EnemyMovingPicture(this,0,0,0));
                enemyQueue.add(new EnemyMovingPicture(this,0,2,1));
                enemyQueue.add(new EnemyMovingPicture(this,0,4,0));
                enemyQueue.add(new EnemyMovingPicture(this,0,6,1));
                enemyQueue.add(new EnemyMovingPicture(this,0,8,0));
                enemyQueue.add(new EnemyMovingPicture(this,0,10,1));
                enemyQueue.add(new EnemyMovingPicture(this,0,12,0));
                enemyQueue.add(new EnemyMovingPicture(this,0,14,1));
                enemyQueue.add(new EnemyMovingPicture(this,0,16,0));
                enemyQueue.add(new EnemyMovingPicture(this,0,18,1));

            } catch (IOException e) {
                e.printStackTrace();
            }




        }

        launch++;
    }
    public void addEnemies() {
        for (int i = 0; i < enemyQueue.size(); i++) {
            if (enemyQueue.get(i).timeOFAppear <= time.getAbsolutePassedTimeMainMenu()) {
                enemies.add(enemyQueue.get(i));
                enemyQueue.remove(i);
                enemies.get(enemies.size() - 1).updatePosition();
            }

        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }


    public void calculate() {
addEnemies();
        Collections.sort(enemies);

        for(int i=0;i<enemies.size();i++)
            enemies.get(i).calculate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setTextSize(50*kHeight);

canvas.drawBitmap(backgroundBitmap,0,0,paint);
        for(int i=0;i<mainMenuButtons.size();i++)
        {
            mainMenuButtons.get(i).draw(canvas);
        }
        for(int i=0;i<enemies.size();i++) {
            enemies.get(i).draw(canvas);

        }
    }
    public int load=0;
    public void drawLoadingImg(Canvas canvas) {

        String s = "Loading";
        load = (load + 1) % 3;
        if (load >= 0) s = s + ".";
        if (load >= 1) s = s + ".";
        if (load >= 2) s = s + ".";
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        canvas.drawBitmap(loadingBitmap, 0, 0, paint);
        canvas.drawText(s, 2100 * kWidth, 1540 * kHeight, paint);


    }
    public void startGame()
    {
        mainMenuActivity.isOn=false;
        Intent intent=new Intent(mainMenuActivity.getApplicationContext(), GameActivity.class);
intent.putExtra("level",1);
        mainMenuActivity.isOn=false;
        {
            try {
                synchronized (this){   wait(60);}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mainMenuActivity.startActivity(intent);
        mainMenuActivity.finish();;

    }
    public void openDevelopers()
    {
        mainMenuActivity.isOn=false;
        Intent intent=new Intent(mainMenuActivity.getApplicationContext(), DevelopersActivity.class);
        mainMenuActivity.isOn=false;
        {
            try {
                synchronized (this){   wait(60);}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mainMenuActivity.startActivity(intent);
        mainMenuActivity.finish();;

    }

}
