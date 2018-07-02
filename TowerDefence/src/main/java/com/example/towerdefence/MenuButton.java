package com.example.towerdefence;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;

public class MenuButton extends Cage {
    protected Game game;
    public int pause;

    public MenuButton(Game game, int leftX, int leftY, int rightX, int rightY) {
        super(leftX, leftY, rightX, rightY);
        this.game = game;
        buttonBitmap =game.gameActivity.images.menuOff;

        pause = 0;
    }
public void changeImage(int i)
{
    if(i==1)
    {
        buttonBitmap =game.gameActivity.images.menuOn;
    }
    else  buttonBitmap =game.gameActivity.images.menuOff;
}
    public void pauseGame() {
     if(!game.paused) game.map.pauseButton.pauseGame();


       game.gameActivity.pauseMenu.show();

}}
