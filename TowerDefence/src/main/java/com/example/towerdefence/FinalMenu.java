package com.example.towerdefence;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class FinalMenu {
    Game game;
    protected Bitmap menuBitmap, nextLvlBitmap, backgroundBitmap, retryBitmap;
protected  Cage menuCage,nextLvlCage,retryCage;
protected     long  totalScore,enemyScore,timeScore,baseScore;
    public FinalMenu(Game game) {
        this.game = game;
        backgroundBitmap = game.gameActivity.images.menuOfTheEndLose;
        menuBitmap = game.gameActivity.images.menuEndOff;
        nextLvlBitmap = game.gameActivity.images.nextLvlOff;
        retryBitmap = game.gameActivity.images.retryOff;
        menuCage=new Cage(243,1182,925,1357);
        retryCage=new Cage(940,1182,1622,1357);
        nextLvlCage=new Cage(1637,1182,2319,1357);

    }
public void touch(int x,int y,int i)
{
    if(i==1) {
        if (retryCage.belonging(x, y)) {
            retryBitmap = game.gameActivity.images.retryOn;
        }


        if (menuCage.belonging(x, y)) {
            menuBitmap = game.gameActivity.images.menuEndOn;
        }

        if (nextLvlCage.belonging(x, y)) {
            nextLvlBitmap = game.gameActivity.images.nextLvlOn;
        }
    }
    else
    {
        if (retryCage.belonging(x, y)) {      pushRetry();      }


        if (menuCage.belonging(x, y)) {       pushMenu(); }

        if (nextLvlCage.belonging(x, y)) {       pushNextLVL();}
        retryBitmap = game.gameActivity.images.retryOff;
        menuBitmap = game.gameActivity.images.menuEndOff;
        nextLvlBitmap = game.gameActivity.images.nextLvlOff;
    }

}

    public void pushRetry()
    {
        retryBitmap = game.gameActivity.images.retryOff;
        game.gameActivity.isOn=false;
        {
            try {
                synchronized (this){   wait(60);}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        game.gameActivity.recreate();
    }
    public void pushMenu()
    {  menuBitmap = game.gameActivity.images.menuEndOff;
        game.gameActivity.isOn=false;
        {
            try {
                synchronized (this){   wait(60);}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        game.gameActivity.exit();}

    public void pushNextLVL()
    {if(game.level<=2) {
        nextLvlBitmap = game.gameActivity.images.enemy1;
        game.gameActivity.isOn = false;
        Intent intent = new Intent(game.gameActivity.getApplicationContext(), GameActivity.class);
        intent.putExtra("level", game.level + 1);
        game.gameActivity.isOn=false;
        nextLvlBitmap=game.gameActivity.images.nextLvlOff;

       {
           try {
               synchronized (this){   wait(60);}
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
        game.gameActivity.startActivity(intent);

        game.gameActivity.finish();
        ;
    }
    }

    public void setBackground() {
        if (game.isWon == -1) backgroundBitmap = game.gameActivity.images.menuOfTheEndLose;
        if (game.isWon == 1) backgroundBitmap = game.gameActivity.images.menuOfTheEndWin;
        enemyScore=game.player.score;
        timeScore=Math.round(game.time.getAbsolutePassedTimePaused()/(game.time.getAbsolutePassedTimePaused()- game.amountOfTime)*10000);
if(timeScore<=0)timeScore=0;
        baseScore=Math.round((game.base.curHP)/game.base.defHP*10000);
        totalScore=enemyScore+timeScore+baseScore;
    }


    public void draw(Canvas canvas) {
        game.paint.setColor(Color.BLACK);
game.paint.setTextSize(80*game.kHeight);
        canvas.drawBitmap(backgroundBitmap, 205 * game.kWidth, 100 * game.kHeight, game.paint);
        canvas.drawBitmap(menuBitmap, (38+205)  * game.kWidth, 1182 * game.kHeight, game.paint);
        canvas.drawBitmap(retryBitmap, (735+205)  * game.kWidth, 1182 * game.kHeight, game.paint);
        if (game.isWon == 1) {
            canvas.drawBitmap(nextLvlBitmap, (1432 + 205) * game.kWidth, 1182 * game.kHeight, game.paint);

        //    canvas.drawBitmap(Bitmap.createScaledBitmap(game.gameActivity.images.mask,(int)(682*game.kWidth-40),(int)(175*game.kHeight),true), (1432 + 205)  * game.kWidth, 1182 * game.kHeight, game.paint);

        }

        canvas.drawText(timeScore+"",(1090+205) *game.kWidth,993*game.kHeight,game.paint);
        canvas.drawText(baseScore+"",(1090+205) *game.kWidth,766*game.kHeight,game.paint);
        canvas.drawText(enemyScore+"",(1090+205)*game.kWidth,877*game.kHeight,game.paint);

        canvas.drawText(totalScore+"",(1090+205) *game.kWidth,1138*game.kHeight,game.paint);

    }
}
