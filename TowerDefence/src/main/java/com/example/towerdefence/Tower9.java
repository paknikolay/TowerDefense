package com.example.towerdefence;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower9 extends Tower {
    public Tower9(Game game, Cage cage, int id) {
        super(game, cage, id);
        defHP = 200 - 1;
        defRadius = 240;
        defAttack = 75;
        defSpeedAttack = 2;
        amountOfAnemies = 1;
        towerBitmap = game.gameActivity.images.tower9;
        cost = 50;
        fit();
    }

    public Tower9() {
        super();
        defHP = 200 - 1;
        defRadius = 240;
        defAttack = 75;
        defSpeedAttack = 2;
        amountOfAnemies = 1;

        cost = 50;

    }


}