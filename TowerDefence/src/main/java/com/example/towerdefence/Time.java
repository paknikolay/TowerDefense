package com.example.towerdefence;

public class Time {
    private long lastTime=0;
    private long passedTime=0;
    private long previousTime=0;

Game game;
    Time(Game game) {
        lastTime = System.currentTimeMillis();previousTime=System.currentTimeMillis();
        passedTime=0;
        this.game=game;
    }
    Time(MainMenu mainMenu) {
        lastTime = System.currentTimeMillis();previousTime=System.currentTimeMillis();
        passedTime=0;

    }
    public long passedTimeMainMenu() {
        passedTime +=( System.currentTimeMillis() - previousTime);
        previousTime = System.currentTimeMillis();
        return passedTime;
    }
    public long passedTime() {
        passedTime +=( System.currentTimeMillis() - previousTime)*game.speedUp;
        previousTime = System.currentTimeMillis();
        return passedTime;
    }
    public long passedTimeForSelling() {
        passedTime +=( System.currentTimeMillis() - previousTime);
        previousTime = System.currentTimeMillis();
        return passedTime;
    }
    public long getAbsolutePassedTimeMainMenu() {
        passedTime +=( System.currentTimeMillis() - previousTime);
        previousTime = System.currentTimeMillis();
        return passedTime;
    }
    public long getPassedTimePaused(){
        return passedTime;
    }

    public long getAbsolutePassedTime() {
        passedTime +=( System.currentTimeMillis() - previousTime)*game.speedUp;
        previousTime = System.currentTimeMillis();
        return passedTime;
    }
    public long getAbsolutePassedTimePaused() {

        return passedTime;
    }

    public void updateTime() {
       previousTime = System.currentTimeMillis();
passedTime=0;
    }
public void updateTimeForGame()
{previousTime = System.currentTimeMillis();}

}
