package com.example.towerdefence;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.io.IOException;
import java.util.Scanner;

public class GameThread extends Thread {
    protected Game game;
    public int pause = 0;


    public GameThread(Game game) {
        this.game = game;
        pause = 0;
    }

    @Override
    public void run() {

        while (game.gameActivity.isOn) {


            if (game.surfaceHolder.getSurface().isValid()) {


                if (!game.paused && pause == 0 && !game.isLoading) {
                    try {
                        try {
                            Canvas canvas = game.surfaceHolder.lockCanvas();
                            game.calculate();

                            game.draw(canvas);

                            synchronized (this) {
                                this.wait(50 / game.speedUp);
                            }

                            game.surfaceHolder.unlockCanvasAndPost(canvas);

                        } catch (NullPointerException n) {
                            pause = 1;
                            game.isLoading = true;
                            n.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {

                    if (game.isLoading)
                        if (!game.isLoaded) {


                            try {
                                game.in = new Scanner(game.context.getAssets().open("game_settings" + game.level + ".gs"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            game.makeExampleOfTowers();
                            game.makeExampleOfEnemies();
                            game.gameActivity.images=new Images(game);
                            game.paint=new Paint();
                            Typeface typeface = Typeface.createFromAsset(game.getContext().getAssets(), "Sochi2014 Regular.ttf");
                            game.paint.setTypeface(typeface);

                            game.player = new Player(game, 10000, 1000);
                            game.map = new Map(game);
                            game.getEnemies();
game.waveTime=new Time(game);
game.finalMenu=new FinalMenu(game);
                            game.time = new Time(game);
                            game.makeBase();
                            game.waveTime.updateTime();
                            game.menuMask=game.gameActivity.images.menuMask;
                            game.isLoaded = true;
                            game.isLoading = false;


                        }


                    try {

                        try {
                            {
                                try {
                                    Canvas canvas = game.surfaceHolder.lockCanvas();

                                    game.draw(canvas);


                                    game.surfaceHolder.unlockCanvasAndPost(canvas);
                                } catch (IllegalStateException n) {
                                    n.printStackTrace();
                                }
                            }
                        } catch (NullPointerException n) {
                            n.printStackTrace();
                            game.isLoading = true;
                            pause = 1;
                        }

                        synchronized (this) {
                            this.wait(50 / game.speedUp);

                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            } else {


                try {
                    synchronized (this) {
                        this.wait(50);
                    }

                } catch (InterruptedException e) {

                }
            }
        }
    }
}



