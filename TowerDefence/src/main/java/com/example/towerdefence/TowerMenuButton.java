package com.example.towerdefence;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class TowerMenuButton extends TowerButton {

    protected Cage cage;
    protected int leftX,leftY;
    protected int typeOfButton;//1-repair 2-upgrate 3-sell
    protected int height=140,width=160;
    protected Cage cage1;
    protected Bitmap buttonBitmap;

    Game game;
    public TowerMenuButton(Game game,Cage cage, int type)
    {
     super( game,  cage.leftX,  cage.leftY,  cage.rightX,  cage.rightY,type);

        this.typeOfButton=type;

        this.game=game;
        this.cage=cage;
        if(type==1){buttonBitmap=game.gameActivity.images.repairOff;}
        if(type==2){buttonBitmap=game.gameActivity.images.upgrateOff;}
        if(type==3){buttonBitmap=game.gameActivity.images.sellOff;}

        if(cage.leftY<835  )
        {
            if(cage.rightX<=1280) {if(type==1){cage1=game.map.getCage(cage.rightX+2,cage.rightY-1);} else if(type==2){cage1=game.map.getCage(cage.rightX+2,cage.rightY+2);}else {cage1=game.map.getCage(cage.rightX-1,cage.rightY+2);} }
            else if (cage.leftX>=1280) {if(type==1){cage1=game.map.getCage(cage.leftX-2,cage.leftY+1);} else if(type==2){cage1=game.map.getCage(cage.leftX-2,cage.rightY+2);}else {cage1=game.map.getCage(cage.rightX-2,cage.rightY+2);} }

        }
        else
        if(cage.rightY>=835)
        {
            if(cage.rightX<=1280)
            {if(type==1)
            {cage1=game.map.getCage(cage.rightX+2,cage.rightY-2);}  else if(type==2){cage1=game.map.getCage(cage.rightX+2,cage.leftY-2);}      else {cage1=game.map.getCage(cage.leftX+2,cage.leftY-2);} }
            else if (cage.leftX>=1280) {if(type==1){cage1=game.map.getCage(cage.leftX-2,cage.rightY-2);} else if(type==2){cage1=game.map.getCage(cage.leftX-2,cage.leftY-2);}else {cage1=game.map.getCage(cage.rightX-2,cage.leftY-2);} }

        }


    }
    public void changeImage(int i)
    {
        if(i==1)
        {
            if(typeOfButton==1){buttonBitmap=game.gameActivity.images.repairOn;}
            if(typeOfButton==2){buttonBitmap=game.gameActivity.images.upgrateOn;}
            if(typeOfButton==3){buttonBitmap=game.gameActivity.images.sellOn;}
        }
        else {
            if(typeOfButton==1){buttonBitmap=game.gameActivity.images.repairOff;}
            if(typeOfButton==2){buttonBitmap=game.gameActivity.images.upgrateOff;}
            if(typeOfButton==3){buttonBitmap=game.gameActivity.images.sellOff;}
        }
    }

    public void push() {
        for (int i = 0; i < game.towers.size(); i++)
            if(cage.equals(game.towers.get(i).cage)) {
changeImage(1);
if(typeOfButton==1)game.towers.get(i).repair();
else if(typeOfButton==2)game.towers.get(i).upgrate();
else game.towers.get(i).sell();
                                break;
            }

        }

@Override
    public void draw(Canvas canvas)
    {
if(!game.isTouching)changeImage(0);
        game.paint.setColor(Color.BLACK);

        try {
            int i;
for( i=0;i<game.towers.size();i++)
    if(cage.equals(game.towers.get(i).cage))break;
           game. paint.setTextSize(45 * game.kHeight);

            canvas.drawBitmap(buttonBitmap,cage1.leftX*game.kWidth,cage1.leftY*game.kHeight,game.paint);
            if (typeOfButton == 1)
                canvas.drawText(game.towers.get(i).repairCost + "", cage1.leftX * game.kWidth, (cage1.rightY) * game.kHeight, game.paint);
            if (typeOfButton == 2)
              if(game.towers.get(i).isMaxLVL()) canvas.drawText("Max", cage1.leftX * game.kWidth, (cage1.rightY) * game.kHeight,game.paint); else canvas.drawText(game.towers.get(i).upgrateCost + "", cage1.leftX * game.kWidth, (cage1.rightY) * game.kHeight,game.paint);
            if (typeOfButton == 3)
                canvas.drawText(game.towers.get(i).removingCost + "", cage1.leftX * game.kWidth, (cage1.rightY) * game.kHeight, game.paint);
        }
        catch (IndexOutOfBoundsException e){}
    }
}
