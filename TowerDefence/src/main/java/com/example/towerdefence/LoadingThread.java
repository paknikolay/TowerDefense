package com.example.towerdefence;


import android.graphics.Canvas;

public class LoadingThread extends Thread {

    private Game game;

    public LoadingThread(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (true) { //убрать если надо
            if (game.isLoading) {
                {
                    try {
                        try

                        {
                            Canvas canvas = game.surfaceHolder.lockCanvas();
                            game.drawLoadingImg(canvas);
                            game.surfaceHolder.unlockCanvasAndPost(canvas);
                        } catch (NullPointerException n) {
                            n.printStackTrace();
                        }
                        try {
                            synchronized (this) {
                                wait(70);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } catch (IllegalArgumentException e)

                    {e.printStackTrace();}
                }
            }
            try {
                synchronized (this) {
                    wait(30);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
