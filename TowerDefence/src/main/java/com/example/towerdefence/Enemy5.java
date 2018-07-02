package com.example.towerdefence;


import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class Enemy5 extends Enemy {
    Enemy5(Game game, int pathIterator, int id, double timeOFAppear, int wave, int pathNumber) {
        super(game, pathIterator, id, timeOFAppear, wave, pathNumber);
        defHP = 150000;
        defRadius = 240;
        defAttack = 80;
        defSpeedAttack = (float)2;
        amountOfEnemies = 1;
        type = 1;
        defSpeedMovingY = (float) 1 / 20;
        defSpeedMovingX = (float) 1 / 21;
        defAmountOfPixelsMovingX = 2;
        defAmountOfPixelsMovingY = 1;
        award = 400;

        width=330;
        height= 290;
        enemyBitmap = game.gameActivity.images.enemy5;
        fit();
    }

    Enemy5() {
        super();
        defHP = 150000;
        defRadius = 240;
        defAttack = 80;
        defSpeedAttack = (float)2;
        amountOfEnemies = 1;
        type = 1;
        defSpeedMovingY = (float) 1 / 20;
        defSpeedMovingX = (float) 1 / 21;
        defAmountOfPixelsMovingX = 2;
        defAmountOfPixelsMovingY = 1;
        award = 400;

        width=330;
        height= 290;

    }

    @Override
    public boolean attack() {
        int attackedTowers = 0;

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
                        attackedTowers++;
                    }


                }


        }
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

                attackedTowers++;
                if (attackedTowers > 0) {
                    attackTime.updateTime();

                }
            }
            return true;

        }

        if (attackedTowers > 0) {
            attackTime.updateTime();
            return true;
        }

        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        game.paint.setColor(Color.BLACK);
        Cage cage2=game.map.getCage(curPositionX,curPositionY);
        canvas.drawBitmap(enemyBitmap, (curPositionX - width / 2) * game.kWidth, (curPositionY - height/3*2-20) * game.kHeight,game.paint);//TODO Проверить смещения
        new HeathBar(game, this.curPositionX, this.curPositionY, this.height, this.width, this.curHP / this.defHP * 100).draw(canvas);
        // canvas.drawText(attackTime.passedTime()+"",500,500,paint);

    }
}
