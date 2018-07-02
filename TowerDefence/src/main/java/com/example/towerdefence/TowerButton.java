package com.example.towerdefence;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class TowerButton extends Cage {
    public int typeOfTower = 1;
    public int cost;
    protected Bitmap maskBitmap;
    protected Game game;

    public TowerButton(Game game, int leftX, int leftY, int rightX, int rightY, int type) {
        super(leftX, leftY, rightX, rightY);
        this.game = game;

        typeOfTower = type;
        //TODO дополнить

        maskBitmap = game.gameActivity.images.mask;
        if (type == 1) {
            buttonBitmap = game.gameActivity.images.tower1img;
            cost = game.tower1.cost;
        } else if (type == 2) {
            buttonBitmap = game.gameActivity.images.tower2img;
            cost = game.tower2.cost;
        } else if (type == 3) {
            buttonBitmap = game.gameActivity.images.tower3img;
            cost = game.tower3.cost;
        } else if (type == 4) {
            buttonBitmap = game.gameActivity.images.tower4img;
            cost = game.tower4.cost;
        } else if (type == 5) {
            buttonBitmap = game.gameActivity.images.tower5img;
            cost = game.tower5.cost;
        } else if (type == 6) {
            buttonBitmap = game.gameActivity.images.tower6img;
            cost = game.tower6.cost;
        } else if (type == 7) {
            buttonBitmap = game.gameActivity.images.tower7img;
            cost = game.tower7.cost;
        } else if (type == 8) {
            buttonBitmap = game.gameActivity.images.tower8img;
            cost = game.tower8.cost;
        } else if (type == 9) {
            buttonBitmap = game.gameActivity.images.tower9img;
            cost = game.tower9.cost;
        } else if (type == 10) {
            buttonBitmap = game.gameActivity.images.tower10img;
            cost = game.tower10.cost;
        }
buttonBitmap=Bitmap.createScaledBitmap(buttonBitmap,Math.round(150*game.kWidth),Math.round(150*game.kHeight),true);


    }

    public boolean buy() {
        if (game.player.canBuyTower(cost)) {
            return true;
        } else return false;

    }

    public void draw(Canvas canvas) {
        Rect src;

        canvas.drawBitmap(buttonBitmap, leftX * game.kWidth, leftY * game.kHeight,game. paint);
        if (!game.player.canBuyTower(cost)) {
           game. paint.setAlpha(150);
            canvas.drawBitmap(maskBitmap, leftX * game.kWidth, leftY * game.kHeight, game.paint);


        }
       game. paint.setTextSize(70*game.kHeight);
        canvas.drawText(cost+"",leftX*game.kWidth,rightY*game.kHeight,game.paint);
        ;

    }
}
