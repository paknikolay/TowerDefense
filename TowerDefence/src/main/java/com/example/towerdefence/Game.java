package com.example.towerdefence;

import android.app.AlertDialog;
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


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    public float kHeight = 1, kWidth = 1;
    public int typeOfTower = 0;
    public Map map;
    public SurfaceHolder surfaceHolder;
    public GameThread gameThread;
    public float kIncreasingSpeedAttack = 1, kIncreasingDamageOfAttack = 1;
    public float kSlowingDown = 1;
    public LinkedList<Enemy> enemyQueue = new LinkedList<Enemy>();
    public LinkedList<Enemy> enemies = new LinkedList<Enemy>();
    public LinkedList<Tower> towers = new LinkedList<Tower>();
    public Bitmap menuMask;
    //   public LinkedList<Tower> towersToAdd = new LinkedList<Tower>();
    // public int towerCost;
    public Context context;
    public Time time;
    public LinkedList<Circle> circles = new LinkedList<Circle>();
    public LinkedList<TowerMenuButton> towerMenu = new LinkedList<TowerMenuButton>();
    public boolean isTouching = false;
    public int speedUp = 1;
    public boolean paused = false;
    public int launch = 0;
    public Player player;
    public int level;
    public Scanner in;
    public double currentWave = 0.5;
    public int amountOfWaves;
    public long timeBreak=5000;
    public  Time waveTime;
    private LoadingThread loadingThread;
    public boolean isLoaded = false;
    public GameActivity gameActivity;
    public Bitmap loadingBitmap1,loadingBitmap2,loadingBitmap3,loadingBitmap4,loadingBitmap5,loadingBitmap6,loadingBitmap7;
    public Tower1 tower1;
    public Tower2 tower2;
    public Tower3 tower3;
    public Tower4 tower4;
    public Tower5 tower5;
    public Tower6 tower6;
    public Tower7 tower7;
    public Tower8 tower8;
    public Tower9 tower9;
    public Tower10 tower10;
    public Enemy1 enemy1;
    public Enemy2 enemy2;
    public Enemy3 enemy3;
    public Enemy4 enemy4;
    public Enemy5 enemy5;
public int enemyID;
    public int towerID = 0;
    public boolean isLoading = true;
public FinalMenu finalMenu;
    public Base base;
    public Paint paint;
    public int isWon=0;
    public int amountOfEnemies=0,amountOfTime=0;
    int x,y;


    //TODO
    // создать несколько списков(дерево)для разных башен
    //считать (из файла ) списки врагов и занести в список(дерево)
//for ()
    //TODO созать bhelper и считать player,tower или в другом месте


    protected void noTouch() {
        isTouching = false;
    }

    public boolean isNotTowerMenu(Cage cage) {
        for (int i = 0; i < towerMenu.size(); i++) {
            if (towerMenu.get(i).cage1.equals(cage)) {
                towerMenu.get(i).push();
                return false;
            }
        }
        return true;

    }

    private boolean isFree(Cage cage) {

if(base.cage.belonging(cage.centerX,cage.centerY))return  false;
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).cage.equals(cage)) {
                try {
                    circles.clear();
                    circles.add(new Circle(this, towers.get(i).cage.centerX, towers.get(i).cage.centerY, towers.get(i).curRadius, towers.get(i).cage));
                    towerMenu.add(new TowerMenuButton(this, towers.get(i).cage, 1));
                    towerMenu.add(new TowerMenuButton(this, towers.get(i).cage, 2));
                    towerMenu.add(new TowerMenuButton(this, towers.get(i).cage, 3));

                } catch (IndexOutOfBoundsException e) {
                }
                return false;
            }
        }
        return true;

    }

    public void makeBase() {
        base = new Base(this, new Cage(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
    }

    public void makeExampleOfTowers() {
        tower1 = new Tower1();
        tower2 = new Tower2();
        tower3 = new Tower3();
        tower4 = new Tower4();
        tower5 = new Tower5();
        tower6 = new Tower6();
        tower7 = new Tower7();
        tower8 = new Tower8();
        tower9 = new Tower9();
        tower10 = new Tower10();
    }

    public void makeExampleOfEnemies() {
        enemy1 = new Enemy1();
        enemy2 = new Enemy2();
enemy3=new Enemy3();
        enemy4=new Enemy4();
        enemy5=new Enemy5();
    }

    public Game(Context context, float kWidth, float kHeight, int level, GameActivity gameActivity) {
        super(context);
        this.level = level;

        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        this.kHeight = kHeight;
        this.kWidth = kWidth;
        this.kHeight = this.kHeight / 1600;
        this.kWidth = this.kWidth / 2560;
        this.context = context;
        this.gameActivity = gameActivity;

        loadingThread = new LoadingThread(this);


    }

    public Game(Context context) {
        super(context);
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isLoading) {
            isTouching = true;
            if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                noTouch();
                if(isWon==1 || isWon==-1)finalMenu.touch((int)Math.round((event.getX()) / this.kWidth), (int) Math.round((event.getY() / this.kHeight)),0);
                if (isWon == 0) {
                    map.notTouched();
                    for (int i = 0; i < towerMenu.size(); i++) {
                        towerMenu.get(i).changeImage(0);
                    }
                }

            }
            if(isWon==1 || isWon==-1)finalMenu.touch((int)Math.round((event.getX()) / this.kWidth), (int) Math.round((event.getY() / this.kHeight)),1);
            if (isWon == 0) {
                Cage cage = map.getCage((int) Math.round((event.getX()) / this.kWidth), (int) Math.round((event.getY() / this.kHeight)));
                x=(int) Math.round((event.getX()) / this.kWidth);
                y= (int) Math.round((event.getY() / this.kHeight));
                if (isTouching) {
                    if (cage != null) {
                        map.touch(cage);
                        for (int i = 0; i < towerMenu.size(); i++) {
                            if (towerMenu.get(i).cage1.equals(cage))
                                towerMenu.get(i).changeImage(1);
                        }
                    }
                } else {
                    if(isWon==1 || isWon==-1)finalMenu.touch(x, y,0);
                    if (cage == null) ;
                    else if (isNotTowerMenu(cage)) {

                        circles.clear();
                        towerMenu.clear();
                        if (map.isMenu(cage)) ;
                        else if (map.isFree(cage)) if (isFree(cage)) {
                            if (this.typeOfTower == 0) ;
                            else {
                                //map.pauseButton.pauseGame();
                                // gameActivity.setTowerMenu.show();
                                towerID++;
                                if (this.typeOfTower == 1) {
                                    towers.add(new Tower1(this, cage, towerID));
                                    player.buy(tower1.cost);
                                } else if (this.typeOfTower == 2) {
                                    towers.add(new Tower2(this, cage, towerID));
                                    player.buy(tower2.cost);
                                } else if (this.typeOfTower == 3) {
                                    towers.add(new Tower3(this, cage, towerID));
                                    player.buy(tower3.cost);
                                } else if (this.typeOfTower == 4) {
                                    towers.add(new Tower4(this, cage, towerID));
                                    player.buy(tower4.cost);
                                }
                                /*else if (this.typeOfTower == 5) {
                                    towers.add(new Tower5(this, cage, towerID));
                                    player.buy(tower5.cost);
                                } else if (this.typeOfTower == 6) {
                                    towers.add(new Tower6(this, cage, towerID));
                                    player.buy(tower6.cost);
                                } else if (this.typeOfTower == 7) {
                                    towers.add(new Tower7(this, cage, towerID));
                                    player.buy(tower7.cost);
                                } else if (this.typeOfTower == 8) {
                                    towers.add(new Tower8(this, cage, towerID));
                                    player.buy(tower8.cost);
                                } else if (this.typeOfTower == 9) {
                                    towers.add(new Tower9(this, cage, towerID));
                                    player.buy(tower9.cost);
                                } else if (this.typeOfTower == 10) {
                                    towers.add(new Tower10(this, cage, towerID));
                                    player.buy(tower10.cost);
                                }*/
                                typeOfTower = 0;
                                sortTowers();
                            }
                        }


                    }

                }
            }
        }
        return true;
    }

    protected void getEnemies() {
        enemyID = 0;
        int type;
        amountOfWaves = in.nextInt();

        for (int p = 1; p <= amountOfWaves; ++p) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                type = in.nextInt();
                enemyID++;
                //       if (type == 0)

                if (type == 1)
                    enemyQueue.add(new Enemy1(this, 0, enemyID, in.nextDouble(), p, in.nextInt()));//TODO сделать проверку на тип врага
                else if (type == 2)
                    enemyQueue.add(new Enemy2(this, 0, enemyID, in.nextDouble(), p, in.nextInt()));
                else if (type == 3)
                    enemyQueue.add(new Enemy3(this, 0, enemyID, in.nextDouble(), p, in.nextInt()));
                else if (type == 4)
                    enemyQueue.add(new Enemy4(this, 0, enemyID, in.nextDouble(), p, in.nextInt()));
                else if(type==5)
                    enemyQueue.add(new Enemy5(this, 0, enemyID, in.nextDouble(), p, in.nextInt()));
            }
        }


        for(int i=0;i<enemyQueue.size()-1;i++)
        {amountOfEnemies++;
            if(enemyQueue.get(i).wave!=enemyQueue.get(i+1).wave){amountOfTime+=enemyQueue.get(i).timeOFAppear+timeBreak;}
        }
        amountOfTime+=enemyQueue.getLast().timeOFAppear+timeBreak;

    }
    public void updateAllTime() {//TODO

        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).updateTime();
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).attackTime.updateTimeForGame();
            enemies.get(i).movingTime.updateTimeForGame();
        }
        for (int i = 0; i < map.menu.size(); i++) {
            if (map.menu.get(i).getClass().equals(AbilityButton.class))
                ((AbilityButton) map.menu.get(i)).updateTime();

        }
        time.updateTimeForGame();
        waveTime.updateTimeForGame();
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        launch++;
        if (launch == 1) {
            paint = new Paint();
            Typeface typeface = Typeface.createFromAsset(this.getContext().getAssets(), "Sochi2014 Bold.ttf");
            paint.setTypeface(typeface);
            loadingBitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background1), Math.round(2560 * kWidth), Math.round(1600 * kHeight), true);
            loadingBitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background2), Math.round(2560 * kWidth), Math.round(1600 * kHeight), true);
            loadingBitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background3), Math.round(2560 * kWidth), Math.round(1600 * kHeight), true);
            loadingBitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background4), Math.round(2560 * kWidth), Math.round(1600 * kHeight), true);
            loadingBitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background5), Math.round(2560 * kWidth), Math.round(1600 * kHeight), true);
            loadingBitmap6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background6), Math.round(2560 * kWidth), Math.round(1600 * kHeight), true);
            loadingBitmap7 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background7), Math.round(2560 * kWidth), Math.round(1600 * kHeight), true);

            loadingThread.start();
        }


        if (launch == 1) {
            gameThread = new GameThread(this);
            gameThread.start();
        }
        if (launch >= 2 && isLoaded) {
            updateAllTime();
            gameThread.pause = 0;
        }
        if (isLoaded) isLoading = false;

    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {


    }

    public void addEnemies() {
        for (int i = 0; i < enemyQueue.size(); i++) {
            if (currentWave == enemyQueue.get(i).wave && enemyQueue.get(i).timeOFAppear <= waveTime.getAbsolutePassedTime()) {
                enemies.add(enemyQueue.get(i));
                enemyQueue.remove(i);
                enemies.get(enemies.size() - 1).updatePosition();
            }
           // else {break;}
        }
    }


    public void calculate() {
        if (isWon == 0) {
            if (!paused) {
                for (int i = 0; i < towers.size(); i++) {
                    towers.get(i).calculate();
                }
                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).calculate();
                }

                addEnemies();
                sortEnemies();
                base.calculate();

                if (enemyQueue.size() != 0) {
                    if (enemyQueue.getFirst().wave != currentWave)

                        if (enemies.size() == 0) {
                            if (waveTime.getAbsolutePassedTime() >= timeBreak) {
                                waveTime.updateTime();
                                currentWave += 0.5;
                            }

                        }
                }
                if (enemies.size() == 0 && enemyQueue.size() == 0) {
                      {isWon = 1;map.pauseButton.pauseGame();}
                }

            }
        }
    }


    public void sortEnemies() {
        Collections.sort(enemies);
    }

    public void sortTowers() {
        Collections.sort(towers);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
    if (gameActivity.paused == 1) {
        map.pauseButton.pauseGame();
        gameActivity.paused = 0;
    }


    map.drawBackground(canvas);
    int j = 0, i = 0;
    while (i < towers.size() || j < enemies.size()) {
        if (i < towers.size()) {
            if (j < enemies.size()) {
                if (enemies.get(j).curPositionY >= towers.get(i).cage.centerY) {
                    drawTowers(canvas, i);
                    i++;
                } else {
                    drawEnemies(canvas, j);
                    j++;
                }
            } else {
                drawTowers(canvas, i);
                i++;
            }
        } else if (j < enemies.size()) {
            drawEnemies(canvas, j);
            j++;
        }
    }
    for (i = 0; i < circles.size(); i++) {
        circles.get(i).draw(canvas);

    }
    for (i = 0; i < towerMenu.size(); i++)
        towerMenu.get(i).draw(canvas);
    drawBase(canvas);

    map.drawMenu(canvas);
    player.draw(canvas);

        if(isWon==1 || isWon==-1)
        {
            paint.setAlpha(110);
            canvas.drawBitmap(menuMask,0,0,paint);
            paint.setAlpha(255);
            finalMenu.setBackground();
            finalMenu.draw(canvas);


        }

    }
public void drawBase(Canvas canvas)
{
    base.draw(canvas);
}
    public void drawTowers(Canvas canvas, int i) {
        if (towers.get(i).isDead) {
            if (towerMenu.size() > 0) if (towerMenu.get(0).cage.equals(towers.get(i).cage)) {
                towerMenu.clear();
                circles.remove(0);
            }
            towers.remove(i);
        } else {
            towers.get(i).draw(canvas);

        }
    }

    public int load = -1,loadim=-1;

    public void drawLoadingImg(Canvas canvas) {
 {if(!isLoaded){
    String s = "Loading";
    load = (load + 1) % 30;
     loadim=(loadim+1)%12;
    if (load >= 0) s = s + ".";
    if (load >= 10) s = s + ".";
    if (load >= 22) s = s + ".";
    paint.setColor(Color.RED);
    paint.setTextSize(100 * kHeight);
   if(loadim==0) canvas.drawBitmap(loadingBitmap1, 0, 0, paint);
     if(loadim==1) canvas.drawBitmap(loadingBitmap2, 0, 0, paint);
     if(loadim==2) canvas.drawBitmap(loadingBitmap3, 0, 0, paint);
     if(loadim==3) canvas.drawBitmap(loadingBitmap4, 0, 0, paint);
     if(loadim==4) canvas.drawBitmap(loadingBitmap3, 0, 0, paint);
     if(loadim==5) canvas.drawBitmap(loadingBitmap2, 0, 0, paint);
     if(loadim==6) canvas.drawBitmap(loadingBitmap1,0, 0, paint);
     if(loadim==7) canvas.drawBitmap(loadingBitmap5,0, 0, paint);
     if(loadim==8) canvas.drawBitmap(loadingBitmap6,0, 0, paint);
     if(loadim==9) canvas.drawBitmap(loadingBitmap7,0, 0, paint);
     if(loadim==10) canvas.drawBitmap(loadingBitmap6,0, 0, paint);
     if(loadim==11) canvas.drawBitmap(loadingBitmap5,0, 0, paint);






    canvas.drawText(s, 2100 * kWidth, 1540 * kHeight, paint);
}}
    }

    public void drawEnemies(Canvas canvas, int j) {

        if (enemies.get(j).isDead) {
            for (int i = 0; i < towers.size(); i++)
                if (towers.get(i).attackingEnemiesID.contains(enemies.get(j).enemuID)) {
                    towers.get(i).attackingEnemiesID.remove(enemies.get(j).enemuID);
                    break;
                }
            enemies.remove(j);
        } else {
            enemies.get(j).draw(canvas);
        }

    }




}
