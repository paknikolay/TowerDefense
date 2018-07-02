package com.example.towerdefence;


import android.graphics.BitmapFactory;

public class Enemy2 extends Enemy {
    Enemy2(Game game, int pathIterator, int id, double timeOFAppear,int wave,int pathNumber)
    {
        super( game,  pathIterator,  id,  timeOFAppear, wave,pathNumber);
        defHP = 200;
        defRadius =81;
        defAttack = 25;
        defSpeedAttack=(float)2;
        amountOfEnemies=1;
        type=1;
        defSpeedMovingY=(float)1/20;
        defSpeedMovingX=(float)1/20;
        defAmountOfPixelsMovingX=8;
        defAmountOfPixelsMovingY=6;
        award=20;
        type=1;
        enemyBitmap=game.gameActivity.images.enemy2;
        fit();
    }
    Enemy2()
    {
        super( );
        defHP = 200;
        defRadius =81;
        defAttack = 25;
        defSpeedAttack=(float)2;
        amountOfEnemies=1;
        type=1;
        defSpeedMovingY=(float)1/20;
        defSpeedMovingX=(float)1/35;
        defAmountOfPixelsMovingX=4;
        defAmountOfPixelsMovingY=2;
        award=20;
        type=1;
    }
    @Override
    public void die() {
        isDead = true;
        game.enemyID++;
        game.enemyQueue.add(new Enemy1(game,pathIterator,game.enemyID,0,wave,pathNumber));

        game.enemyID++;
        game.enemyQueue.add(new Enemy1(game,pathIterator,game.enemyID,0,wave,pathNumber));

        game.enemyID++;
        game.enemyQueue.add(new Enemy1(game,pathIterator,game.enemyID,0,wave,pathNumber));

        game.enemyID++;
        game.enemyQueue.add(new Enemy1(game,pathIterator,game.enemyID,0,wave,pathNumber));

        game.enemyID++;
        game.enemyQueue.add(new Enemy1(game,pathIterator,game.enemyID,0,wave,pathNumber));

        game.player.addGold(award);
    }
}
