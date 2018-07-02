package com.example.towerdefence;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Comparator;
public class EnemyMovingPicture implements Comparable<EnemyMovingPicture> {
    protected float defSpeedMovingX = (float) 0.05, defSpeedMovingY = (float) 0.05;
    protected float curSpeedMovingX = (float) 0.5, curSpeedMovingY = (float) 0.5;/*секунд на пиксель*/
    protected int curPositionX, curPositionY;
    public Cage cage;
    protected int direction=1;//0-на меня, 1-вправо, 2- от меня, 3-влево
    public int pathIterator;
    protected Bitmap enemyBitmap;
    protected int width = 160, height = 140;
    public boolean isDead = false;
    public double timeOFAppear = 1;
    public int enemuID;//номер по появлению для сортировки
    protected MainMenu mainMenu;
    protected int pathNumber;
    protected int defAmountOfPixelsMovingX,defAmountOfPixelsMovingY;
    protected int curAmountOfPixelsMovingX,curAmountOfPixelsMovingY;
Time movingTime;

    public EnemyMovingPicture(MainMenu mainMenu, int pathIterator,  double timeOFAppear,int pathNumber) {

        this.pathNumber=pathNumber;
        this.mainMenu = mainMenu;
        this.pathIterator = pathIterator;
       // this.enemuID = id;
        this.timeOFAppear = timeOFAppear;



        defSpeedMovingY=(float)1/20;
        defSpeedMovingX=(float)1/20;
        defAmountOfPixelsMovingX=4;
        defAmountOfPixelsMovingY=3;

        enemyBitmap=mainMenu.enemyBitmap;
        fit();

        curPositionX = mainMenu.path.get(pathNumber).get(pathIterator).centerX;
        curPositionY = mainMenu.path.get(pathNumber).get(pathIterator).centerY;
        updateDirection(pathIterator);

        movingTime = new Time(mainMenu);


    }

    public void fit() {

        defSpeedMovingX *= 1000*mainMenu.kWidth ;
        defSpeedMovingY *= 1000*mainMenu.kHeight ;
        timeOFAppear *= 1000;
        curSpeedMovingY = defSpeedMovingY;

        curSpeedMovingX = defSpeedMovingX;


    }

    @Override
    public int compareTo(EnemyMovingPicture enemyMovingPicture) {

        if (this.curPositionY < enemyMovingPicture.curPositionY) return -1;
        else if (this.curPositionY > enemyMovingPicture.curPositionY) return 1;
        else if (this.curPositionX > enemyMovingPicture.curPositionX) return 1;
        else if (this.curPositionX < enemyMovingPicture.curPositionX) return -1;
        else return this.enemuID - enemyMovingPicture.enemuID;
    }

    public void calculate() {
        this.curAmountOfPixelsMovingX=Math.round(defAmountOfPixelsMovingX);
        this.curAmountOfPixelsMovingY=Math.round(defAmountOfPixelsMovingY);
            updatePosition();

    }

    public void setPosition(int x, int y) {
        this.curPositionX = x;
        this.curPositionY = y;
    }

    public void updatePosition() {
        if (mainMenu.path.get(pathNumber).size()== pathIterator + 1) {  curPositionX = mainMenu.path.get(pathNumber).get(0).centerX;    curPositionY = mainMenu.path.get(pathNumber).get(0).centerY; updateDirection(0);}
        else {
            if (this.direction == 0) {
                if (movingTime.passedTimeMainMenu() >= curSpeedMovingY) {
                    movingTime.updateTime();
                    this.curPositionY += curAmountOfPixelsMovingY;
                    if (this.curPositionY >= mainMenu.path.get(pathNumber).get(pathIterator + 1).centerY)
                        curPositionY = mainMenu.path.get(pathNumber).get(pathIterator + 1).centerY;
                }
            } else if (this.direction == 1) {
                if (movingTime.passedTimeMainMenu() >= curSpeedMovingX) {
                    movingTime.updateTime();
                    this.curPositionX += curAmountOfPixelsMovingX;
                    if (this.curPositionX >= mainMenu.path.get(pathNumber).get(pathIterator + 1).centerX)
                        curPositionX = mainMenu.path.get(pathNumber).get(pathIterator + 1).centerX;
                }
            } else if (this.direction == 2) {
                if (movingTime.passedTimeMainMenu() >= curSpeedMovingY) {
                    movingTime.updateTime();
                    this.curPositionY -= curAmountOfPixelsMovingY;
                    if (this.curPositionY <= mainMenu.path.get(pathNumber).get(pathIterator + 1).centerY)
                        curPositionY = mainMenu.path.get(pathNumber).get(pathIterator + 1).centerY;
                }
            } else if (this.direction == 3) {
                if (movingTime.passedTimeMainMenu() >= curSpeedMovingX) {
                    movingTime.updateTime();
                    this.curPositionX -= curAmountOfPixelsMovingX;
                    if (this.curPositionX <= mainMenu.path.get(pathNumber).get(pathIterator + 1).centerX)
                        curPositionX = mainMenu.path.get(pathNumber).get(pathIterator + 1).centerX;
                }
            }

            if (mainMenu.path.get(pathNumber).get(pathIterator + 1).centerY == curPositionY && mainMenu.path.get(pathNumber).get(pathIterator + 1).centerX == curPositionX)
                updateDirection(pathIterator + 1);
            //TODO просмотреть выходы за границы
        }
    }












    public void updateDirection(int pathIterator) {
        this.pathIterator = pathIterator;
        if (mainMenu.path.get(pathNumber).size() <= pathIterator + 2) ;
        else {
            Cage cageFirst = mainMenu.path.get(pathNumber).get(pathIterator);
            Cage cageLast = mainMenu.path.get(pathNumber).get(pathIterator + 1);
            if (cageFirst.centerX == cageLast.centerX) {
                if (cageFirst.centerY < cageLast.centerY) this.direction = 0;
                else this.direction = 2;
            } else {
                if (cageFirst.centerX < cageLast.centerX) this.direction = 1;
                else this.direction = 3;
            }
        }
    }

    public void draw(Canvas canvas) {
        Rect src;

        canvas.drawBitmap(enemyBitmap, (curPositionX - width / 2) * mainMenu.kWidth, (curPositionY - height / 2-20) * mainMenu.kHeight,mainMenu.paint);//TODO Проверить смещения

        // canvas.drawText(attackTime.passedTime()+"",500,500,paint);

    }
public void isBelonging(int x,int y)
{
   if(curPositionX-width/2<=x && curPositionY-height/2<=y && curPositionX+width/2>=x && curPositionY+height/2>=y){{  curPositionX = mainMenu.path.get(pathNumber).get(0).centerX;    curPositionY = mainMenu.path.get(pathNumber).get(0).centerY; updateDirection(0);}}
}
}
