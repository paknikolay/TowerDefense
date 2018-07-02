package com.example.towerdefence;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SpeedUpButton extends Cage {
    protected Game game;
    public int speed=1;

    public SpeedUpButton(Game game, int leftX, int leftY, int rightX, int rightY) {
        super(leftX, leftY, rightX, rightY);
        this.game = game;
        buttonBitmap = game.gameActivity.images.speed1Off;
        buttonBitmap = Bitmap.createScaledBitmap(buttonBitmap, Math.round(150 * game.kWidth), Math.round(150 * game.kHeight), true);

    }

    public void changeImage(int i)
    {
        if(i==1)
        {
            if(speed==1) buttonBitmap = game.gameActivity.images.speed1On;
            else if(speed==2)buttonBitmap=game.gameActivity.images.speed2On;
            else if(speed ==4)buttonBitmap=game.gameActivity.images.speed4On;
            else if(speed ==4)buttonBitmap=game.gameActivity.images.speed4On;
        }
        else {if(speed==1) buttonBitmap = game.gameActivity.images.speed1Off;
        else if(speed==2)buttonBitmap=game.gameActivity.images.speed2Off;
        else if(speed ==4)buttonBitmap=game.gameActivity.images.speed4Off;}
    }
    public void speedUP() {
        speed=(speed*2)%7;
        if(speed==1) buttonBitmap = game.gameActivity.images.speed1Off;
        else if(speed==2)buttonBitmap=game.gameActivity.images.speed2Off;
        else if(speed ==4)buttonBitmap=game.gameActivity.images.speed4Off;



        game.speedUp=speed;


    }

}