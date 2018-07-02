package com.example.towerdefence;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Player {
    protected long gold;
    protected long cristal;
    protected Game game;
    public long score;
public void buy(float cost)
{
    gold-=cost;
}
    public Player(Game game,long gold,long cristal)
    {
        this.game=game;
        this.gold=gold;
        this.cristal=cristal;

    }
public void addPoints(int points)
{
    score+=points;
}
    public boolean canUseAbility(int cost)
    {

        if (cristal >= cost) return true;
        else
            return false;
    }
    public void useAbility(int cost)
    {
        cristal-=cost;
    }


    public boolean canBuyTower(int cost)
    {
        if (gold >= cost) return true;
        else
            return false;
    }

    public void buyTower(int cost) {
        gold -= cost;
    }

public void draw(Canvas canvas)
{
    game.paint.setColor(Color.BLACK);
   game. paint.setTextSize(70*game.kHeight);
    canvas.drawText(""+gold,1380*game.kWidth,110*game.kHeight,game.paint);
    canvas.drawText(""+cristal,1900*game.kWidth,110*game.kHeight,game.paint);

}
    public void addGold(float gold)
    {
        this.gold+=gold;
    }
}