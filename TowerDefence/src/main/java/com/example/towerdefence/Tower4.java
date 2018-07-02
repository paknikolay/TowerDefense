package com.example.towerdefence;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower4 extends Tower {
    public Tower4(Game game, Cage cage,int id) {
        super(game, cage,id);
        defHP = 300;
        defRadius = 320;
        defAttack = 50;
        defSpeedAttack=1;
        amountOfAnemies=1;
        cost=210;
        type=2;
        towerBitmap=game.gameActivity.images.tower4;
        height=220;
                width=115;
        fit();


    }

    public Tower4() {
        super();
        defHP = 300;
        defRadius = 400;
        defAttack = 50;
        defSpeedAttack=1;
        amountOfAnemies=1;
        cost=210;
        height=220;
        width=115;
        type=2;
    }
}