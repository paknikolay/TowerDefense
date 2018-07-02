package com.example.towerdefence;

import android.graphics.Bitmap;

public class Base extends  Tower {
	public Base(Game game,Cage cage)
    {
        super(game,cage,0);
        defHP=10000;
        width=320;
        height=280;
        defRadius=360;
        defSpeedAttack= (float) 1;
        defAttack=100;
        type=3;

fit();
    }
@Override
    public void fit() {


        defSpeedAttack *= 1000;


        curHP = defHP;
        curRadius = defRadius;
        curSpeedAttack = defSpeedAttack;
        curAttack = defAttack;
        curResistance = defResistance;
        towerBitmap=game.gameActivity.images.base;
    }
    @Override
    public void calculate()

    {
        attack();
    }
    @Override
    public void attacked(float damage) {
        curHP = curHP - damage;
        if(curHP>defHP)curHP=defHP;
        if (curHP <= 0) {curHP=0;die();}
    }
    @Override
    public void die() {

        game.isWon=-1;
        game.map.pauseButton.pauseGame();
        isDead = true;
    }

}
