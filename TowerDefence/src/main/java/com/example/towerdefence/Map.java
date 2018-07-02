package com.example.towerdefence;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Map {
    private int cageHeight = 140, cageWidth = 160;
    public float width, height;
    public LinkedList<LinkedList<Cage>> path = new LinkedList<LinkedList<Cage>>();
    public LinkedList<Cage> noWay = new LinkedList<>();
    public LinkedList<Cage> web = new LinkedList<>();
    public LinkedList<Cage> menu = new LinkedList<>();



    public PauseButton pauseButton;
    public MenuButton menuButton;
    public SpeedUpButton speedUpButton;
    private Game game;
    protected Bitmap fieldBitmap;


    public Map(Game game)  {
        this.game = game;
        fieldBitmap = game.gameActivity.images.gameField;

        cageHeight = 140;
        cageWidth = 160;
//TODO считать путь врага


//a=getFilePath(a);
  //      System.out.println(a);







        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 18; j++) {
                web.add(new Cage((j-1) * cageWidth, 275 + i * cageHeight, 0 + cageWidth * j, 275 + cageHeight * (i + 1)));
                //web.add(new Cage(Math.round(j*cageWidth/game.kWidth),Math.round((275+i*cageHeight)/game.kHeight),Math.round(cageWidth*(j+1)/game.kWidth),Math.round((275+cageHeight*(i+1))/game.kWidth)));
            }
        }


            for (int i = 0; i < 10; i++) {
            menu.add(new TowerButton(game, 80 + i * 150 + i * 10, 1450, 70 + 150 + i * 10 + i * 150, 1600, i + 1));
        }

        for (int i = 0; i < 4; i++) {
            menu.add(new AbilityButton(game, 1848 + i * 150 + i * 8, 1450, 1848 + 150 + i * 150 + i * 8, 1600, i + 1));
        }
        pauseButton = new PauseButton(game, 10, 10, 10 + 150, 10 + 150);
        menuButton = new MenuButton(game, 2400, 10, 2400 + 150, 10 + 150);
        speedUpButton = new SpeedUpButton(game, 170, 10, 170 + 150, 10 + 150);

        int amountOfPath=game.in.nextInt();
        for(int j=0;j<amountOfPath;j++) {
         path.add(new LinkedList<Cage>());
            int n = game.in.nextInt();
            int x, y;
            for (int i = 0; i < n; i++) {
                x = game.in.nextInt();
                y = game.in.nextInt();
                System.out.println(x + " " + y);
                path.get(j).add(this.getCage(x, y));
            }
        }
    }


    public Cage getCage(int x, int y) {

        for (int i = 0; i < web.size(); i++) {
            if (web.get(i).belonging(x, y)) return web.get(i);
        }
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).belonging(x, y)) return menu.get(i);
        }
        if (pauseButton.belonging(x, y)) return pauseButton;
        if (menuButton.belonging(x, y)) return menuButton;
        if (speedUpButton.belonging(x, y)) return speedUpButton;
//TODO добавить все менюшки
        return null;
    }

    public boolean isFree(Cage cage) {
        for(int j=0;j<path.size();j++)
        for (int i=0;i<path.get(j).size();i++)
        {
            if (path.get(j).get(i).equals(cage))return  false;
        }

        for (int i=0;i<noWay.size();i++)
        {
            if (noWay.get(i).equals(cage))return  false;
        }

        //TODO посмотреть чтобы было не path || moWay
        return true;
    }

    public boolean isPaused() {
     return  game.paused;
    }
    public void touch(Cage cage) {
        if (cage.equals(menuButton)) {
            menuButton.changeImage(1);

        }  else if (cage.equals(speedUpButton)) {
            speedUpButton.changeImage(1);

        } else
        if (cage.equals(pauseButton)) {
            pauseButton.changeImage(1);

        }   /*else {
            for (int i = 0; i < menu.size(); i++)
                if (menu.get(i).equals(cage)) {
                    if (menu.get(i).getClass().equals(TowerButton.class)) {
                        if (((TowerButton) menu.get(i)).typeOfTower == 1) {
                            if(((TowerButton) menu.get(i)).buy())game.typeOfTower = 1;
                            return true;
                        } else if (((TowerButton) menu.get(i)).typeOfTower == 2) {
                            if(((TowerButton) menu.get(i)).buy()) game.typeOfTower = 2;
                            return true;
                        } else if (((TowerButton) menu.get(i)).typeOfTower == 3) {
                            if(((TowerButton) menu.get(i)).buy())   game.typeOfTower = 3;
                            return true;
                        } else if (((TowerButton) menu.get(i)).typeOfTower == 4) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 4;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 5) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 5;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 6) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 6;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 7) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 7;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 8) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 8;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 9) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 9;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 10) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 10;
                            return true;
                        }
                    } else if (menu.get(i).getClass().equals(AbilityButton.class)) {
                        if(((AbilityButton) menu.get(i)).canUseAbility())((AbilityButton) menu.get(i)).useAbility();//TODO проверить наличие кд

                        return true;
                    }

                }


            //TODO обработать меню(вид+функции)

        }*/

    }
    public boolean isMenu(Cage cage) {
         if (cage.equals(speedUpButton)) {
            speedUpButton.speedUP();
            return true;
        } else
        if (cage.equals(pauseButton)) {
            pauseButton.pauseGame();
            return true;
        } else if (cage.equals(menuButton)) {

            menuButton.pauseGame();
            return true;
        } else /*if (!isPaused()) */{
            for (int i = 0; i < menu.size(); i++)
                if (menu.get(i).equals(cage)) {
                    if (menu.get(i).getClass().equals(TowerButton.class)) {
                        if (((TowerButton) menu.get(i)).typeOfTower == 1) {
                            if(((TowerButton) menu.get(i)).buy())game.typeOfTower = 1;
                            return true;
                        } else if (((TowerButton) menu.get(i)).typeOfTower == 2) {
                            if(((TowerButton) menu.get(i)).buy()) game.typeOfTower = 2;
                            return true;
                        } else if (((TowerButton) menu.get(i)).typeOfTower == 3) {
                            if(((TowerButton) menu.get(i)).buy())   game.typeOfTower = 3;
                            return true;
                        } else if (((TowerButton) menu.get(i)).typeOfTower == 4) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 4;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 5) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 5;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 6) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 6;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 7) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 7;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 8) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 8;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 9) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 9;
                            return true;
                        }
                        else if (((TowerButton) menu.get(i)).typeOfTower == 10) {
                            if(((TowerButton) menu.get(i)).buy())  game.typeOfTower = 10;
                            return true;
                        }
                    } else if (menu.get(i).getClass().equals(AbilityButton.class)) {
                        if(((AbilityButton) menu.get(i)).canUseAbility())((AbilityButton) menu.get(i)).useAbility();//TODO проверить наличие кд

                        return true;
                    }

                }


            //TODO обработать меню(вид+функции)
            return false;
        }
     //   return true;
    }
public  void drawTime(Canvas canvas)
    {


        game.paint.setColor(Color.BLACK);
        game.paint.setTextSize(70*game.kHeight);
        long s=0,m=0;

       if(!game.paused){
           s=Math.round(Math.floor(game.time.getAbsolutePassedTime()/1000));
           m=Math.round(Math.floor(s/60));
           s=s%60;
           String h="";
           if(m<=9)h+="0"+m;else h+=m;
           h+=":";
           if(s<=9)h+="0"+s;else h+=s;

          canvas.drawText(h, 600*game.kWidth, 110*game.kHeight,game. paint);



       }
        else {
           s=Math.round(Math.floor(game.time.getAbsolutePassedTimePaused()/1000));
           m=Math.round(Math.floor(s/60));
           s=s%60;
           String h="";
           if(m<=9)h+="0"+m;else h+=m;
           h+=":";
           if(s<=9)h+="0"+s;else h+=s;
     canvas.drawText(h, 600*game.kWidth, 110*game.kHeight, game.paint);



       }
    }
    public void notTouched()
    {
menuButton.changeImage(0);
        speedUpButton.changeImage(0);
        pauseButton.changeImage(0);
    }
    public void drawBackground(Canvas canvas) {


        canvas.drawBitmap(fieldBitmap, 0, 0, game.paint);

    }

    public void drawMenu(Canvas canvas)
    { game.paint.setColor(Color.BLACK);
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getClass().equals(TowerButton.class))
                ((TowerButton) menu.get(i)).draw(canvas);
            else
                ((AbilityButton) menu.get(i)).draw(canvas);
        }

        canvas.drawBitmap(pauseButton.buttonBitmap, pauseButton.leftX * game.kWidth, pauseButton.leftY * game.kHeight, game.paint);
        canvas.drawBitmap(menuButton.buttonBitmap, menuButton.leftX * game.kWidth, menuButton.leftY * game.kHeight, game.paint);
        canvas.drawBitmap(speedUpButton.buttonBitmap, speedUpButton.leftX * game.kWidth, speedUpButton.leftY * game.kHeight, game.paint);
       game. paint.setTextSize(70*game.kHeight);
         canvas.drawText((int)Math.floor(game.currentWave)+"/"+game.amountOfWaves,340*game.kWidth,110*game.kHeight,game.paint);
       drawTime(canvas);
        canvas.drawText(game.player.score+" ",860*game.kWidth,110*game.kHeight,game.paint);
        game.player.draw(canvas);



       drawTime(canvas);

    }

}

