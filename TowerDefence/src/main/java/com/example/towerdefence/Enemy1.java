package com.example.towerdefence;


import android.graphics.BitmapFactory;

public class Enemy1 extends Enemy {
    Enemy1(Game game, int pathIterator, int id, double timeOFAppear, int wave,int pathNumber) {
        super(game, pathIterator, id, timeOFAppear, wave,pathNumber);
        defHP = 55;
        defRadius = 81;
        defAttack = 10;
        defSpeedAttack = (float) 1;
        amountOfEnemies = 1;
        type = 1;
        defSpeedMovingY = (float) 1 / 30;
        defSpeedMovingX = (float) 1 / 35;
        defAmountOfPixelsMovingX = 16;
        defAmountOfPixelsMovingY = 12;
        award = 5;
        type=1;
        height=68;
        width=61;
          enemyBitmap=game.gameActivity.images.enemy1;
        fit();
    }
    Enemy1() {
        super();
        defHP = 55;
        defRadius = 81;
        defAttack = 10;
        defSpeedAttack = (float) 1;
        amountOfEnemies = 1;
        type = 1;
        defSpeedMovingY = (float) 1 / 30;
        defSpeedMovingX = (float) 1 / 35;
        defAmountOfPixelsMovingX = 16;
        defAmountOfPixelsMovingY = 12;
        award = 5;type=1;
        height=68;
        width=61;
    }

}
