package com.example.towerdefence;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle {
    private float centerX,centerY,radius;
    private Game game;
    private Cage cage;

    public Circle(Game game,float centerX,float centerY,float radius,Cage cage)
    {
        this.cage=cage;
        this.centerX=centerX;
        this.centerY=centerY;
        this.game=game;
        this.radius=radius;


    }
public void changeRadius(float radius)
{
    this.radius=radius;

}
    public void draw(Canvas canvas)
    {Paint paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setAlpha(100);
        canvas.drawCircle(centerX*game.kWidth,centerY*game.kHeight,radius*Math.max(game.kHeight,game.kWidth),paint);
    }

}
