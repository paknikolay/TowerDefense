package com.example.towerdefence;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower3 extends Tower {
    public Tower3(Game game, Cage cage, int id) {
        super(game, cage, id);
        towerBitmap = game.gameActivity.images.tower3;
        cost = 150;
        defHP = 260;
        defSpeedAttack=2;
defAttack=60;
        amountOfAnemies=1;
        defRadius = 210;
        type=1;
        height=157;
        width=157;
        fit();

    }

    public Tower3() {
        super();
        cost = 150;
        defHP = 260;
        defAttack=60;
        amountOfAnemies=1;
        defSpeedAttack=2;
        defRadius = 210;
        type=1;
        height=157;
        width=157;
    }

    @Override
    public void attack() {
        int p=0;
        if (attackTime.passedTime() >= curSpeedAttack)
            for (int i = game.enemies.size()-1; i >=0 ; i--)//TODO оптимизировать+ проверка на вид врага(летает/не летает)
            {
                int y = game.enemies.get(i).curPositionY;
                int x = game.enemies.get(i).curPositionX;
                float height=game.enemies.get(i).height/2;
                float width=game.enemies.get(i).width/2;
                if( x>=0 && x<=2560 &&(isBelonging(x-width,y+height)||isBelonging(x+width,y-height)|| isBelonging(x-width,y-height)|| isBelonging(x+width,y+height)||isBelonging(x,y-height)|| isBelonging(x,y+height)||isBelonging(x-width,y)||isBelonging(x+width,y)))
                    if(game.enemies.get(i).type==type || type==3)  {
                    game.enemies.get(i).attacked(curAttack);
                    p++;

                }
            }
if(p!=0)attackTime.updateTime();;
    }
}