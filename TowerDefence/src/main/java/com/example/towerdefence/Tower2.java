package com.example.towerdefence;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower2 extends Tower{
    public Tower2(Game game,Cage cage,int id)
    {
        super(game,cage,id);
        towerBitmap = game.gameActivity.images.tower2;
        defHP = 850;
        defRadius =210-1;
        defAttack = 300;
        defSpeedAttack=(float)1;
        cost=310;
        amountOfAnemies=0;
        type=1;
        height=126;
        width=175;
        fit();
    }
    public Tower2()
    {
        super();

        defHP = 850;
        defRadius =210-1;
        defAttack = 300;
        defSpeedAttack=(float)1;
        cost=310;
        amountOfAnemies=1;
        type=1;
        height=126;
        width=175;

    }

}