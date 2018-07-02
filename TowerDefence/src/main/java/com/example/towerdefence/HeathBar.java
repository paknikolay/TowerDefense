package com.example.towerdefence;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class HeathBar {
    protected float rightX,rightY,width,height;
    float hp;
    private  Game game;
    private int i;
    public HeathBar(Game game,float rightX,float rightY,float height,float width,float hp)
    {
        this.game=game;
        this.rightX=rightX;
        this.rightY=rightY;
        this.height=height;
        this.width=width;
        this.hp=hp;
        this.i=i;
    }
    public void draw(Canvas canvas)
    {
        game.paint.setColor(Color.BLACK);
        game.paint.setTextSize(50*game.kHeight);

        canvas.drawText(Math.round(Math.ceil(hp))+"%",(rightX-width/4-10)*game.kWidth ,(rightY-height/2-30)*game.kHeight ,game.paint);


    }


}
