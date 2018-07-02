package com.example.towerdefence;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

public class Tower implements Comparable<Tower> {
    protected float defHP = 200, defRadius = 250, defAttack = 10, defResistance, defSpeedAttack=1;
    protected float curHP = 80, curRadius = 300, curAttack = 10, curResistance, curSpeedAttack = 2;
    protected int type, amountOfAnemies,  repairCost=4,removingCost=1,upgrateCost=6;//1-земля 2-воздух 3-все подряд
    private int curlevel=1,maxlevel=3;
    public  int cost=50;
    public int width = 160, height = 140;
    public boolean isDead = false;
    public Cage cage = new Cage(0, 0, 160, 140);
    protected Bitmap towerBitmap;

    public int x, y;
    protected Game game;

    Time attackTime;
    public int towerID;
private  Time timeOfselling;
    private float kOfSell=1;
public HashSet<Integer> attackingEnemiesID=new HashSet<>();
    public Tower()
    {}
    public Tower(Game game, Cage cage, int id)  {
        //   towerBitmap=BitmapFactory.decodeResource( game.getResources(), R.drawable.pen);
this.towerID=id;
        this.game = game;
        this.cage = cage;
        x = cage.leftX;
        y = cage.leftY;
        attackTime = new Time(game);
//TODO fit в конце
    }

    @Override
    public int compareTo(Tower tower) {
        if(this.cage.centerY<tower.cage.centerY)return -1;else if (this.cage.centerY>tower.cage.centerY)return 1; else return this.cage.centerX-tower.cage.centerX;
    }

public boolean isMaxLVL()
{if(curlevel==maxlevel)return true;else return false;

}
    public void fit() {


        defSpeedAttack *= 1000;


        curHP = defHP;
        curRadius = defRadius;
        curSpeedAttack = defSpeedAttack;
        curAttack = defAttack;
        curResistance = defResistance;
        attacked(0);
        timeOfselling=new Time(game);


    }


    public void attacked(float damage) {
        curHP = curHP - damage;
        if(curHP>defHP)curHP=defHP;
        repairCost =(int)Math.round ((defHP - curHP) / defHP * cost*0.4);
        removingCost=Math.round(curHP/defHP*cost*kOfSell);
        if (curHP <= 0) die();
    }



    public void repair() {
    if(game.player.canBuyTower(repairCost))game.player.buy(repairCost);
        curHP=defHP;
    }

    public void upgrate()
    {
       if(curlevel<maxlevel) if(game.player.canBuyTower(upgrateCost)){game.player.buy(upgrateCost);curlevel++;
           double k=curHP/defHP;
       defHP+=(defHP/(curlevel-1)*curlevel)/3;
       defAttack+=(defAttack/(curlevel-1)*curlevel)/3;
           curRadius=defRadius+curlevel*30;
       curHP=(float)(defHP*k);
        curAttack+=defAttack;
           game.circles.get(0).changeRadius(curRadius);
           upgrateCost=Math.round(cost/4*curlevel);

       }

    }
public void updateTime()
{
    timeOfselling.updateTimeForGame();;
    attackTime.updateTimeForGame();
}
    public void sell()
    {game.player.buy(-removingCost);
die();
    }




    public void die() {
        isDead = true;
    }

    public void draw(Canvas canvas) {
        Rect src;
        removingCost=Math.round(curHP/defHP*cost*kOfSell);
        repairCost =(int )Math.round ((defHP - curHP) / defHP * cost*0.4);
        upgrateCost=Math.round(cost/4*curlevel);
        canvas.drawBitmap(towerBitmap, (cage.centerX-width/2) * game.kWidth, (cage.rightY - height) * game.kHeight, game.paint);
        new HeathBar(game, this.cage.centerX, this.cage.centerY, this.height, this.width, this.curHP / this.defHP * 100).draw(canvas);

    }
public boolean isBelonging(float x,float y)
{
     if ((x - cage.centerX) * (x - cage.centerX) + (y - cage.centerY) * (y - cage.centerY) <= curRadius * curRadius  )return true;else
        return false;

}
    public void attack() {

        if (attackTime.passedTime() >= curSpeedAttack)
            for (int i = game.enemies.size()-1; i >=0 ; i--)//TODO оптимизировать+ проверка на вид врага(летает/не летает)
            {
                int y = game.enemies.get(i).curPositionY;
                int x = game.enemies.get(i).curPositionX;
                float height=game.enemies.get(i).height/2;
                float width=game.enemies.get(i).width/2;
              if( x>=0 && x<=2560 &&(isBelonging(x-width,y+height)||isBelonging(x+width,y-height)|| isBelonging(x-width,y-height)|| isBelonging(x+width,y+height)||isBelonging(x,y-height)|| isBelonging(x,y+height)||isBelonging(x-width,y)||isBelonging(x+width,y)))
             if(game.enemies.get(i).type==type || type==3)   {
                    game.enemies.get(i).attacked(curAttack);
                    attackTime.updateTime();
                   break; //TODO удалить если бьет всех

                }
            }

    }

    public void calculate()

    {

        this.curSpeedAttack=defSpeedAttack/(game.kIncreasingSpeedAttack+1);
        this.curAttack=defAttack*(game.kIncreasingSpeedAttack+1);

        if(timeOfselling.passedTimeForSelling()>=5*1000)kOfSell= (float) 0.6;



        attack();
    }
 public boolean canAttack(int enemyID)
 {
     if(attackingEnemiesID.size()<4)attackingEnemiesID.add(enemyID);
     if(attackingEnemiesID.contains(enemyID)) return true;else    return false;
 }



}