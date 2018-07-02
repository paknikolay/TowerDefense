package com.example.towerdefence;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.io.IOException;
import java.util.Scanner;

public class MainMenuThread extends Thread {
    protected MainMenu mainMenu;
    public int pause = 0;


    public MainMenuThread(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        pause = 0;
    }

    @Override
    public void run() {

        while (true) {
            if (mainMenu.mainMenuActivity.isOn) {
                if (mainMenu.surfaceHolder.getSurface().isValid()) {
                    {

                       if (!mainMenu.isLoaded) {
//                            mainMenu.images = new Images(mainMenu);

                            mainMenu.isLoaded = true;
                        } else {


                            try {
                                {
                                    try {
                                        Canvas canvas = mainMenu.surfaceHolder.lockCanvas();
                                        mainMenu.calculate();
                                        mainMenu.draw(canvas);

                                        mainMenu.surfaceHolder.unlockCanvasAndPost(canvas);
                                    } catch (IllegalStateException n) {
                                        n.printStackTrace();
                                    }
                                }


                            } catch (NullPointerException n) {
                                n.printStackTrace();


                            }


                        }
                    }
                }
            }

            try {
                synchronized (this) {
                    this.wait(30);

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



