package com.example.towerdefence;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Comparator;
public class Enemy implements Comparable<Enemy> {
    protected float award,defHP = 300, defRadius = 170, defAttack = 10, defResistance, defSpeedAttack = (float) 1, defSpeedMovingX = (float) 0.05, defSpeedMovingY = (float) 0.05;
    protected float curHP = 2000, curRadius = 200, curAttack = 10, curResistance, curSpeedAttack = (float) 1/*секунд на удар */, curSpeedMovingX = (float) 0.5, curSpeedMovingY = (float) 0.5;/*секунд на пиксель*/
    protected int curPositionX, curPositionY;
    protected float type, amountOfEnemies;//1-наземный 2-воздушный 3-подземный(невидимый)///0- 1цель 1- много целей
    public Cage cage;
    protected int direction=1;//0-на меня, 1-вправо, 2- от меня, 3-влево
    public int pathIterator;
    protected Bitmap enemyBitmap;
    protected int width = 160, height = 140;
    public boolean isDead = false;
    public double timeOFAppear = 1;
    Time attackTime, movingTime;
    public int enemuID;//номер по появлению для сортировки
    public int towerID;
public int wave;
    protected Game game;
    protected int pathNumber;
protected int defAmountOfPixelsMovingX,defAmountOfPixelsMovingY;
    protected int curAmountOfPixelsMovingX,curAmountOfPixelsMovingY;
    public Enemy() {
    }

    public Enemy(Game game, int pathIterator, int id, double timeOFAppear,int wave,int pathNumber) {
        //TODO НЕ домножить радиус, скорость на
        this.pathNumber=pathNumber;
        this.game = game;
        this.pathIterator = pathIterator;
        this.enemuID = id;
     this.timeOFAppear = timeOFAppear;

        curPositionX = game.map.path.get(pathNumber).get(pathIterator).centerX;
        curPositionY = game.map.path.get(pathNumber).get(pathIterator).centerY;
updateDirection(pathIterator);
        attackTime = new Time(game);
        movingTime = new Time(game);
        this.wave=wave;
        //TODO fit в конце
    }

    public void fit() {

        defSpeedMovingX *=1000* game.kWidth ;
        defSpeedMovingY *=1000* game.kHeight ;
        defSpeedAttack *= 1000;
        timeOFAppear *= 1000;


        curHP = defHP;
        curRadius = defRadius;
        curSpeedMovingY = defSpeedMovingY;
        curSpeedAttack = defSpeedAttack;
        curAttack = defAttack;
        curResistance = defResistance;
        curSpeedMovingX = defSpeedMovingX;


    }

    @Override
    public int compareTo(Enemy enemy) {

        if (this.curPositionY < enemy.curPositionY) return -1;
        else if (this.curPositionY > enemy.curPositionY) return 1;
        else if (this.curPositionX > enemy.curPositionX) return 1;
        else if (this.curPositionX < enemy.curPositionX) return -1;
        else return this.enemuID - enemy.enemuID;
    }

    public void calculate() {
    this.curAmountOfPixelsMovingX=Math.round(defAmountOfPixelsMovingX*(game.kSlowingDown));
    this.curAmountOfPixelsMovingY=Math.round(defAmountOfPixelsMovingY*(game.kSlowingDown));

if(!(curPositionX>=0 && curPositionY>=0 && curPositionX<=2560 && curSpeedMovingY<=1600) ){updatePosition();}
        if (!attack()) {
            updatePosition();
        }

    }


    public void setPosition(int x, int y) {
        this.curPositionX = x;
        this.curPositionY = y;
    }

    public void updatePosition() {
        if (game.map.path.get(pathNumber).size() == pathIterator + 2) ;
        else {
            if (this.direction == 0) {
                if (movingTime.passedTime() >= curSpeedMovingY) {
                    movingTime.updateTime();
                    this.curPositionY += curAmountOfPixelsMovingY;
                    if (this.curPositionY >= game.map.path.get(pathNumber).get(pathIterator + 1).centerY)
                        curPositionY = game.map.path.get(pathNumber).get(pathIterator + 1).centerY;
                }
            } else if (this.direction == 1) {
                if (movingTime.passedTime() >= curSpeedMovingX) {
                    movingTime.updateTime();
                    this.curPositionX += curAmountOfPixelsMovingX;
                    if (this.curPositionX >= game.map.path.get(pathNumber).get(pathIterator + 1).centerX)
                        curPositionX = game.map.path.get(pathNumber).get(pathIterator + 1).centerX;
                }
            } else if (this.direction == 2) {
                if (movingTime.passedTime() >= curSpeedMovingY) {
                    movingTime.updateTime();
                    this.curPositionY -= curAmountOfPixelsMovingY;
                    if (this.curPositionY <= game.map.path.get(pathNumber).get(pathIterator + 1).centerY)
                        curPositionY = game.map.path.get(pathNumber).get(pathIterator + 1).centerY;
                }
            } else if (this.direction == 3) {
                if (movingTime.passedTime() >= curSpeedMovingX) {
                    movingTime.updateTime();
                    this.curPositionX -= curAmountOfPixelsMovingX;
                    if (this.curPositionX <= game.map.path.get(pathNumber).get(pathIterator + 1).centerX)
                        curPositionX = game.map.path.get(pathNumber).get(pathIterator + 1).centerX;
                }
            }

            if (game.map.path.get(pathNumber).get(pathIterator + 1).centerY == curPositionY && game.map.path.get(pathNumber).get(pathIterator + 1).centerX == curPositionX)
                updateDirection(pathIterator + 1);
            //TODO просмотреть выходы за границы
        }
    }


    public void attacked(float damage) {
        curHP -= damage;
        if (curHP <= 0) this.die();
    }

    public boolean isBelonging(int x, int y) {
        if ((x - curPositionX) * (x - curPositionX) + (y - curPositionY) * (y - curPositionY) <= curRadius * curRadius)
            return true;
        else return false;

    }

    public boolean attack() {

        if (isBelonging(game.base.cage.leftX, game.base.cage.rightY)
                || isBelonging(game.base.cage.rightX, game.base.cage.leftY)
                || isBelonging(game.base.cage.leftX, game.base.cage.leftY)
                || isBelonging(game.base.cage.rightX, game.base.cage.rightY)
                || isBelonging(game.base.cage.centerX, game.base.cage.leftY)
                || isBelonging(game.base.cage.centerX, game.base.cage.rightY)
                || isBelonging(game.base.cage.leftX, game.base.cage.centerY)
                || isBelonging(game.base.cage.rightX, game.base.cage.centerY)
                )

        {
            if (attackTime.passedTime() >= curSpeedAttack) {
                game.base.attacked(curAttack);
                attackTime.updateTime();

            }
            return true;
        }
        for (int i = 0; i < game.towers.size(); i++)
        {
            int y = game.towers.get(i).cage.centerY;
            int x = game.towers.get(i).cage.centerX;


            if (isBelonging(game.towers.get(i).cage.leftX, game.towers.get(i).cage.rightY)
                    || isBelonging(game.towers.get(i).cage.rightX, game.towers.get(i).cage.leftY)
                    || isBelonging(game.towers.get(i).cage.leftX, game.towers.get(i).cage.leftY)
                    || isBelonging(game.towers.get(i).cage.rightX, game.towers.get(i).cage.rightY)
                    || isBelonging(game.towers.get(i).cage.centerX, game.towers.get(i).cage.leftY)
                    || isBelonging(game.towers.get(i).cage.centerX, game.towers.get(i).cage.rightY)
                    || isBelonging(game.towers.get(i).cage.leftX, game.towers.get(i).cage.centerY)
                    || isBelonging(game.towers.get(i).cage.rightX, game.towers.get(i).cage.centerY)
                    )
                if (game.towers.get(i).canAttack(enemuID))

                {
                    if (attackTime.passedTime() >= curSpeedAttack) {
                        game.towers.get(i).attacked(curAttack);
                        attackTime.updateTime();

                    }
                    return true;//TODO удалить если бьет всех

                }


        }




        return false;
    }


    public void die() {
        isDead = true;
        game.player.addGold(award);
        game.player.addPoints((int) (award*5));
    }


    public void place() //enemy place around base
    {
    }

    public void updateDirection(int pathIterator) {
        this.pathIterator = pathIterator;
        if (game.map.path.get(pathNumber).size() == pathIterator + 2) ;
        else {
            Cage cageFirst = game.map.path.get(pathNumber).get(pathIterator);
            Cage cageLast = game.map.path.get(pathNumber).get(pathIterator + 1);
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
        game.paint.setColor(Color.BLACK);

        canvas.drawBitmap(enemyBitmap, (curPositionX - width / 2) * game.kWidth, (curPositionY - height / 2) * game.kHeight,game.paint);//TODO Проверить смещения
        new HeathBar(game, this.curPositionX, this.curPositionY, this.height, this.width, this.curHP / this.defHP * 100).draw(canvas);
       // canvas.drawText(attackTime.passedTime()+"",500,500,paint);

    }
}
