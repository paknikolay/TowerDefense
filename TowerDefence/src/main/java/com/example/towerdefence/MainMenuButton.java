package com.example.towerdefence;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class MainMenuButton extends Cage {
    public Cage cage;
    public MainMenu mainMenu;
    private int type;//1-кнопка продолжить 2-начать игру 3-инфо 4-прогресс 5-настройки 6-выход
private Bitmap buttonBitmap,mask;
    public MainMenuButton(Cage cage, MainMenu mainMenu, int type) {
        super(cage.leftX, cage.leftY, cage.rightX, cage.rightY);
        this.cage = cage;
        this.mainMenu = mainMenu;
        this.type = type;
        if (type == 1) {
            buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.continueoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
        }
        if (type == 2) {
            buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.newgameoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
        }
        if (type == 3) {
            buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.infooff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
        }
        if (type == 4) {
            buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.progressoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
        }
        if (type == 5) {
            buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.settingsoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
        }
        if (type == 6) {
            buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.exitoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
        }
mask= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.mask), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
    }

    public void changeImg(int i,int x,int y) {
        if(i==0) {
            if (type == 1) {
                buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.continueoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
            }
            if (type == 2) {
                buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.newgameoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
            }
            if (type == 3) {
                buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.infooff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
            }
            if (type == 4) {
                buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.progressoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
            }
            if (type == 5) {
                buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.settingsoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
            }
            if (type == 6) {
                buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.exitoff), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
            }
        }
        if (cage.belonging(x, y)) {
            if (i == 1) {
                if (type == 1) {
                    buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.continueon), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
                }
                if (type == 2) {
                    buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.newgameon), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
                }
                if (type == 3) {
                    buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.infoon), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
                }
                if (type == 4) {
                    buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.progresson), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
                }
                if (type == 5) {
                    buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.settingson), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
                }
                if (type == 6) {
                    buttonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mainMenu.getResources(), R.drawable.exiton), Math.round(700 * mainMenu.kWidth), Math.round(150 * mainMenu.kHeight), true);
                }
            }

        }

    }

public void draw(Canvas canvas)
{Paint paint=new Paint();
    canvas.drawBitmap(buttonBitmap,cage.leftX*mainMenu.kWidth,cage.leftY*mainMenu.kHeight,paint);
    paint.setAlpha(110);
    if(!(type==2 || type==6 ||type==3))canvas.drawBitmap(mask,cage.leftX*mainMenu.kWidth,cage.leftY*mainMenu.kHeight,paint);
}
    public void pushed() {
        changeImg(0,0,0);
        if (type == 2) {
mainMenu.startGame();
        }
        if(type==6){mainMenu.mainMenuActivity.finishAffinity();}
        if(type==3){mainMenu.openDevelopers();}
    }


}


