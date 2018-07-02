package com.example.towerdefence;


import android.graphics.BitmapFactory;

public class Enemy3 extends Enemy {
    Enemy3(Game game, int pathIterator, int id, double timeOFAppear,int wave,int pathNumber)
    {
        super( game,  pathIterator,  id,  timeOFAppear, wave,pathNumber);
        defHP = 800;
        defRadius =81;
        defAttack = 200;
        defSpeedAttack=(float)1;
        amountOfEnemies=1;
        type=1;
        defSpeedMovingY=(float)1/20;
        defSpeedMovingX=(float)1/20;
        defAmountOfPixelsMovingX=4;
        defAmountOfPixelsMovingY=3;
        award=45;
        type=1;
        enemyBitmap=game.gameActivity.images.enemy3;
       height= 167;
        width =157;
        fit();
    }
    Enemy3()
    {
        super( );
        defHP = 800;
        defRadius =81;
        defAttack = 200;
        defSpeedAttack=(float)1;
        amountOfEnemies=1;
        type=1;
        defSpeedMovingY=(float)1/10;
        defSpeedMovingX=(float)1/10;
        defAmountOfPixelsMovingX=8;
        defAmountOfPixelsMovingY=7;
        award=45;
        type=1;
    }
}
