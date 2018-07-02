package com.example.towerdefence;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class LevelButton extends Cage {
    public Cage cage;
    public Levels levels;
    private int type;//1-кнопка запуска 2-улучшения 3-...TODO
    private Bitmap buttonBitmap;
    public LevelButton(Cage cage, Levels levels, int type) {
        super(cage.leftX, cage.leftY, cage.rightX, cage.rightY);
        this.cage = cage;
        this.levels = levels;
        this.type = type;
        if(type==0){buttonBitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(levels.getResources(),R.drawable.level0),Math.round(700*levels.kWidth),Math.round(130 * levels.kHeight),true); }
        if (type == 1) {
            buttonBitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(levels.getResources(),R.drawable.level1),Math.round(700*levels.kWidth),Math.round(130 * levels.kHeight),true);


        }
    }
    public void draw(Canvas canvas)
    {Paint paint=new Paint();
        canvas.drawBitmap(buttonBitmap,cage.leftX*levels.kWidth,cage.leftY*levels.kHeight,paint);
    }
    public void pushed() {
        if(type==0){ levels.levelActivity.exit();
        //TODO становить поток
        }
        if (type == 1) {
            levels.startGame();

        }
    }


}


