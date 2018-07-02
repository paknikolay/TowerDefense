package com.example.towerdefence;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class DeveloperButton extends Cage {
    public Cage cage;
    public Developer developer;
    private int type;//1-кнопка выход TODO
    private Bitmap buttonBitmap,mask;
    public DeveloperButton(Cage cage, Developer developer, int type) {
        super(cage.leftX, cage.leftY, cage.rightX, cage.rightY);
        this.cage = cage;
        this.developer = developer;
        this.type = type;
        if (type == 1) {
            buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(developer.getResources(), R.drawable.backmenubuttoff), Math.round(700 * developer.kWidth), Math.round(150 * developer.kHeight), true);

        }
    }

    public void changeImg(int i,int x,int y) {
        if (i == 0) {
            if (type == 1) {
                buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(developer.getResources(), R.drawable.backmenubuttoff), Math.round(700 * developer.kWidth), Math.round(150 * developer.kHeight), true);
            }

        }
        if (cage.belonging(x, y)) {
            if (i == 1) {
                if (type == 1) {
                    buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(developer.getResources(), R.drawable.backmenubuttonon), Math.round(700 * developer.kWidth), Math.round(150 * developer.kHeight), true);

                }

            }

        }
    }

    public void draw(Canvas canvas)
    {Paint paint=new Paint();
        canvas.drawBitmap(buttonBitmap,cage.leftX*developer.kWidth,cage.leftY*developer.kHeight,paint);

    }
    public void pushed() {
        changeImg(0,0,0);
        if (type == 1) {
            developer.developersActivity.exit();
        }

    }


}


