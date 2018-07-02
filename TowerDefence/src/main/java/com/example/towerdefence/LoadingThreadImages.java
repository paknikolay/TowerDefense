package com.example.towerdefence;


import android.graphics.Canvas;

public class LoadingThreadImages extends Thread {

    private MainMenu mainMenu;

    public LoadingThreadImages(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void run() {
        while (true) { //убрать если надо
            if (!mainMenu.isLoaded) {
                {

                    try

                    {
                        Canvas canvas = mainMenu.surfaceHolder.lockCanvas();
                        mainMenu.drawLoadingImg(canvas);
                        mainMenu.surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (NullPointerException n) {
                        n.printStackTrace();
                    }
                    try {
                        synchronized (this) {
                            wait(700);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

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
