package com.example.towerdefence;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower5 extends Tower {
    public Tower5(Game game, Cage cage,int id) {
        super(game, cage,id);
        defHP = 200-1;
        defRadius = 240;
        defAttack = 75;
        defSpeedAttack=2;
        amountOfAnemies=1;
        towerBitmap = game.gameActivity.images.tower5;
        cost=50;
        fit();
    }
    public Tower5() {
        super();
        defHP = 200-1;
        defRadius = 240;
        defAttack = 75;
        defSpeedAttack=2;
        amountOfAnemies=1;

        cost=50;

    }

}
