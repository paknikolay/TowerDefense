package com.example.towerdefence;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Cage {
public int rightX, rightY,leftX,leftY,centerX,centerY;
    protected Bitmap buttonBitmap;
    public  Cage(int leftX,int leftY,int rightX,int rightY)
    {   this.rightX=rightX;
        this.leftY=leftY;
        this.leftX=leftX;
        this.rightY=rightY;
        centerX=Math.round((leftX+rightX)/2);
        centerY=Math.round((leftY+rightY)/2);
    }

    public boolean equals(Cage cage)
    {
     if(cage.centerX==this.centerX && cage.centerY==this.centerY)return true;else return  false;
    }

    public boolean belonging (int x,int y)
    {
        if (this.rightY>=y && this.rightX>=x && this.leftX<=x && this.leftY<=y)return  true;else return false;


    }

}
