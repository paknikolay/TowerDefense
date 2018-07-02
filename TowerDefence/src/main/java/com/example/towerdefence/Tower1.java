package com.example.towerdefence;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower1 extends Tower {
    public  Tower1()
    {
        super();
        defHP = 75;
        defRadius = 240;
        defAttack = 45;
        defSpeedAttack=2;
        amountOfAnemies=0;
height=113;
        width=199;
        type=3;
         cost=50;
    }
    public Tower1(Game game, Cage cage, int id) {
        super(game, cage,id);
        defHP = 75;
        defRadius = 240;
        defAttack = 45;
        defSpeedAttack=1;
        amountOfAnemies=1;
        towerBitmap=game.gameActivity.images.tower1;
        cost=50;
        type=3;
        height=113;
        width=199;
        fit();
    }


}
