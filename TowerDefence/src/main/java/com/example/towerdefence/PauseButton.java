package com.example.towerdefence;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PauseButton extends Cage {
    protected Game game;
    public int pause;

    public PauseButton(Game game, int leftX, int leftY, int rightX, int rightY) {
        super(leftX, leftY, rightX, rightY);
        this.game = game;
        buttonBitmap = game.gameActivity.images.pauseOff;
        buttonBitmap = Bitmap.createScaledBitmap(buttonBitmap, Math.round(150 * game.kWidth), Math.round(150 * game.kHeight), true);
        pause = 0;
    }
    public void changeImage(int i)
    {
        if(i==1)
        {if(game.paused)buttonBitmap =game.gameActivity.images.resumeOn;
            else
            buttonBitmap =game.gameActivity.images.pauseOn;
        }
        else { if(game.paused)buttonBitmap =game.gameActivity.images.resumeOff;else buttonBitmap =game.gameActivity.images.pauseOff;}
    }
    public void pauseGame() {
      if(game.paused){game.paused=false; game.updateAllTime(); }else {game.paused=true;}
        if(game.paused)buttonBitmap =game.gameActivity.images.resumeOff;else buttonBitmap =game.gameActivity.images.pauseOff;
if (!game.paused)    {game.updateAllTime();}

    }

}
