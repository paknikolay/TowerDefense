package com.example.towerdefence;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class AbilityButton extends Cage {
    protected Game game;
    public int ability;//1-хил 2-удар по всем 3- замедление врагов 4-ускорение и увеличение урона у всех башен
    private Time cdPassed, timeOfLastingPassed;
    private int cd, timeOfLasting;
    protected Bitmap maskBitmap;
    protected int cost;

    public AbilityButton(Game game, int leftX, int leftY, int rightX, int rightY, int ability) {
        super(leftX, leftY, rightX, rightY);
        maskBitmap = BitmapFactory.decodeResource(game.getResources(), R.drawable.mask);
        this.game = game;
        this.ability = ability;
        //TODO считать кд параметры абилок из бд
        if (ability == 1) {
            buttonBitmap = game.gameActivity.images.ability1;
            cd = 25 * 1000;
            timeOfLasting = 0 * 1000;
            cost = 13;
        } else if (ability == 2) {
            buttonBitmap = game.gameActivity.images.ability2;
            cd = 40 * 1000;
            timeOfLasting = 0 * 1000;
            cost = 20;
        } else if (ability == 3) {
            buttonBitmap = game.gameActivity.images.ability3;
            cd = 25 * 1000;
            timeOfLasting = 6 * 1000;
            cost = 10;
        } else {
            buttonBitmap = game.gameActivity.images.ability4;
            cd = 45 * 1000;
            timeOfLasting = 5 * 1000;
            cost = 10;

        }

        maskBitmap = game.gameActivity.images.mask;
        cdPassed = new Time(game);
        timeOfLastingPassed = new Time(game);
    }

    public void resetAbility() {


    }

    public boolean canUseAbility() {
        if (!game.paused) {
            if (timeOfLastingPassed.passedTime() >= timeOfLasting) {
                if (ability == 3) {
                    game.kSlowingDown = 1;
                }
                if (ability == 4) {
                    game.kIncreasingDamageOfAttack = 1;
                    game.kIncreasingSpeedAttack = 1;
                }
            }
        } else {
            if (timeOfLastingPassed.getPassedTimePaused() >= timeOfLasting) {
                if (ability == 3) {
                    game.kSlowingDown = 1;
                }
                if (ability == 4) {
                    game.kIncreasingDamageOfAttack = 1;
                    game.kIncreasingSpeedAttack = 1;
                }
            }
        }
        if (game.paused) {
            if (cd - cdPassed.getPassedTimePaused() <= 0) return true;
        } else if (cd - cdPassed.passedTime() <= 0) return true;
        return false;
    }

    public void useAbility() {
        if (game.player.canUseAbility(cost)) {
            game.player.useAbility(cost);
            if (ability == 1) {
                for (int i = 0; i < game.towers.size(); i++)
                    game.towers.get(i).attacked((float) (-game.towers.get(i).defHP * 0.3));
                cdPassed.updateTime();
            } else if (ability == 2) {
                for (int i = 0; i < game.enemies.size(); i++)
                    game.enemies.get(i).attacked((float) (100));
                cdPassed.updateTime();

            } else if (ability == 3) {
                game.kSlowingDown = (float) 0.8;
                cdPassed.updateTime();
                timeOfLastingPassed.updateTime();
            } else {
                game.kIncreasingDamageOfAttack = (float) 0.5;
                game.kIncreasingSpeedAttack = (float) 0.5;
                cdPassed.updateTime();
                timeOfLastingPassed.updateTime();
            }
        }
    }


    public void updateTime()
    {
        timeOfLastingPassed.updateTimeForGame();
        cdPassed.updateTimeForGame();
    }

    public void draw(Canvas canvas) {


        canvas.drawBitmap(buttonBitmap, leftX * game.kWidth, leftY * game.kHeight, game.paint);

game.paint.setColor(Color.BLACK);
        game.paint.setTextSize(70*game.kHeight);

          if(!canUseAbility() || !game.player.canUseAbility(cost)) {game.paint.setAlpha(150); canvas.drawBitmap(maskBitmap, leftX * game.kWidth, leftY * game.kHeight, game.paint);
          }

        if(!canUseAbility()){game.paint.setColor(Color.RED);if(!game.paused)canvas.drawText(Math.round((cd-cdPassed.passedTime())/1000+1)+"",(leftX)*game.kWidth,(leftY+140)*game.kHeight,game.paint);
            if(game.paused) canvas.drawText(Math.round((cd-cdPassed.getPassedTimePaused())/1000+1)+"",(leftX)*game.kWidth,(leftY+140)*game.kHeight,game.paint);
        }
        else {canvas.drawText(cost+"",(leftX)*game.kWidth,(leftY+140)*game.kHeight,game.paint);}
    game.paint.setColor(Color.BLACK);
    }
}