package com.example.towerdefence;


import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

public class Enemy4 extends Enemy {
    Enemy4(Game game, int pathIterator, int id, double timeOFAppear, int wave, int pathNumber) {
        super(game, pathIterator, id, timeOFAppear, wave, pathNumber);
        defHP = 450;
        defRadius = 81;
        defAttack = 10;
        defSpeedAttack = (float) 1;
        amountOfEnemies = 1;
        type = 2;
        defSpeedMovingY = (float) 1 / 20;
        defSpeedMovingX = (float) 1 / 20;
        defAmountOfPixelsMovingX = 8;
        defAmountOfPixelsMovingY = 7;
        award = 15;
        type = 2;
        enemyBitmap = game.gameActivity.images.enemy4;
        fit();
    }

    Enemy4() {
        super();
        defHP = 450;
        defRadius = 81;
        defAttack = 10;
        defSpeedAttack = (float) 1;
        amountOfEnemies = 1;
        type = 2;
        defSpeedMovingY = (float) 1 / 20;
        defSpeedMovingX = (float) 1 / 20;
        defAmountOfPixelsMovingX = 8;
        defAmountOfPixelsMovingY = 7;
        award = 15;
        type = 2;

    }

    @Override
    public boolean attack() {
        int attackedTowers = 0,haveAttacked=0;
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

haveAttacked++;
            }
            attackedTowers++;
        }
        for (int i = 0; i < game.towers.size(); i++) {
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
                        haveAttacked++;
                    }
                    attackedTowers++;

                }


        }


        if (attackedTowers > 0) {
          if(haveAttacked>0)  attackTime.updateTime();
            return true;
        }

        return false;
    }
@Override
    public void draw(Canvas canvas) {
    game.paint.setColor(Color.BLACK);

        canvas.drawBitmap(enemyBitmap, (curPositionX - width / 2) * game.kWidth, (curPositionY - height / 5*4-10) * game.kHeight,game.paint);//TODO Проверить смещения
        new HeathBar(game, this.curPositionX, this.curPositionY, this.height, this.width, this.curHP / this.defHP * 100).draw(canvas);
        // canvas.drawText(attackTime.passedTime()+"",500,500,paint);

    }
}
