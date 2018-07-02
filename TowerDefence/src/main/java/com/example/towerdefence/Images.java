package com.example.towerdefence;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

public class Images   {
    public Bitmap enemy1, enemy2, enemy3, enemy4, enemy5,enemy6;
    public Bitmap tower1, tower2, tower3, tower4, tower5, tower6, tower7, tower8, tower9, tower10,tower1img, tower2img, tower3img, tower4img, tower5img, tower6img, tower7img, tower8img, tower9img, tower10img;

    public Bitmap resumeOn,resumeOff,pauseOn,pauseOff,speed1On,speed2On,speed4On,speed1Off,speed2Off,speed4Off,menuOn,menuOff,ability1,ability2,ability3,ability4,menuMask;
    public Bitmap repairOff,repairOn,upgrateOn,upgrateOff,sellOff,sellOn;
    public Bitmap menuOfTheEndWin,menuOfTheEndLose,retryOn,retryOff,nextLvlOn,nextLvlOff,menuEndOn,menuEndOff;
    public Bitmap gameField;
    public Bitmap mask;
    public Bitmap base;


   public Images(Game game) {
       enemy1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.enemy1), Math.round(game.enemy1.width* game.kWidth), Math.round(game.enemy1.height * game.kHeight), true);
       enemy2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.enemy2), Math.round(game.enemy2.width* game.kWidth), Math.round(game.enemy2.height * game.kHeight), true);
       enemy3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.enemy3), Math.round(game.enemy3.width* game.kWidth), Math.round(game.enemy3.height * game.kHeight), true);
enemy4=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.enemy4), Math.round(game.enemy4.width* game.kWidth), Math.round(game.enemy4.height * game.kHeight), true);
       enemy5=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.enemy5), Math.round(game.enemy5.width* game.kWidth), Math.round(game.enemy5.height * game.kHeight), true);


       tower1=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower1), Math.round(game.tower1.width* game.kWidth), Math.round(game.tower1.height * game.kHeight), true);
       tower2=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower2), Math.round(game.tower2.width* game.kWidth), Math.round(game.tower2.height * game.kHeight), true);
       tower3=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower3), Math.round(game.tower3.width* game.kWidth), Math.round(game.tower3.height * game.kHeight), true);
       tower4=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower4), Math.round(game.tower4.width* game.kWidth), Math.round(game.tower4.height * game.kHeight), true);
   //    tower5=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower5), Math.round(game.tower5.width* game.kWidth), Math.round(game.tower5.height * game.kHeight), true);
   //    tower6=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower6), Math.round(game.tower6.width* game.kWidth), Math.round(game.tower6.height * game.kHeight), true);
   //    tower7=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower7), Math.round(game.tower7.width* game.kWidth), Math.round(game.tower7.height * game.kHeight), true);
      // tower8=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower8), Math.round(game.tower8.width* game.kWidth), Math.round(game.tower8.height * game.kHeight), true);
      // tower9=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower9), Math.round(game.tower9.width* game.kWidth), Math.round(game.tower9.height * game.kHeight), true);
       //tower10=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower10), Math.round(game.tower10.width* game.kWidth), Math.round(game.tower10.height * game.kHeight), true);


       tower1img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower1img), Math.round(game.tower1.width* game.kWidth), Math.round(game.tower1.height * game.kHeight), true);
       tower2img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower2img), Math.round(game.tower2.width* game.kWidth), Math.round(game.tower2.height * game.kHeight), true);
       tower3img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower3img), Math.round(game.tower3.width* game.kWidth), Math.round(game.tower3.height * game.kHeight), true);
       tower4img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower4img), Math.round(game.tower4.width* game.kWidth), Math.round(game.tower4.height * game.kHeight), true);
       tower5img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower5img), Math.round(game.tower5.width* game.kWidth), Math.round(game.tower5.height * game.kHeight), true);
       tower6img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower6img), Math.round(game.tower6.width* game.kWidth), Math.round(game.tower6.height * game.kHeight), true);
       tower7img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower7img), Math.round(game.tower7.width* game.kWidth), Math.round(game.tower7.height * game.kHeight), true);
    tower8img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower8img), Math.round(game.tower8.width* game.kWidth), Math.round(game.tower8.height * game.kHeight), true);
      tower9img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower9img), Math.round(game.tower9.width* game.kWidth), Math.round(game.tower9.height * game.kHeight), true);
      tower10img=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower10img), Math.round(game.tower10.width* game.kWidth), Math.round(game.tower10.height * game.kHeight), true);



       repairOff=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.repairoff), Math.round(160* game.kWidth), Math.round(140 * game.kHeight), true);
       repairOn=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.repairon), Math.round(160* game.kWidth), Math.round(140 * game.kHeight), true);
       upgrateOff=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.uograteoff), Math.round(160* game.kWidth), Math.round(140 * game.kHeight), true);
       upgrateOn=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.upgrateon), Math.round(160* game.kWidth), Math.round(140 * game.kHeight), true);
       sellOn=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.sellon), Math.round(160* game.kWidth), Math.round(140 * game.kHeight), true);
       sellOff=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.selloff), Math.round(160* game.kWidth), Math.round(140 * game.kHeight), true);

       ability1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.ability1), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       ability2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.ability2), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       ability3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.ability3), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       ability4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.ability4), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);

       resumeOn=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.resumeon), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       resumeOff=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.resumeoff), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       menuOn=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.menuon), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       menuOff=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.menuoff), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       pauseOff=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.pauseoff), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       pauseOn=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.pauseon), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       speed1On=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.speed1on), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       speed2On=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.speed2on), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       speed4On=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.speed4on), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       speed1Off=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.speed1off), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       speed2Off=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.speed2off), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);
       speed4Off=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.speed4off), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);

       menuOfTheEndLose= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.lose), Math.round(2150 * game.kWidth), Math.round(1300  * game.kHeight), true);
       menuOfTheEndWin= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.win), Math.round(2150 * game.kWidth), Math.round(1300 * game.kHeight), true);
       retryOn= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.retryon), Math.round(682 * game.kWidth), Math.round(175  * game.kHeight), true);
       retryOff= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.retryoff), Math.round(682 * game.kWidth), Math.round(175  * game.kHeight), true);
       menuEndOff= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.menuendoff), Math.round(682 * game.kWidth), Math.round(175  * game.kHeight), true);
       menuEndOn= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.menuendon ), Math.round(682 * game.kWidth), Math.round(175  * game.kHeight), true);
       nextLvlOn= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.nextlvlon), Math.round(682 * game.kWidth), Math.round(175  * game.kHeight), true);
       nextLvlOff= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.nextlvloff), Math.round(682 * game.kWidth), Math.round(175  * game.kHeight), true);


       mask=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.mask), Math.round(150* game.kWidth), Math.round(150 * game.kHeight), true);

       base=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.base), Math.round(320* game.kWidth), Math.round(280 * game.kHeight), true);
       gameField = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.gamefield), Math.round(2560* game.kWidth), Math.round(1600 * game.kHeight), true);

menuMask=      Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.mask), Math.round(2560* game.kWidth), Math.round(1600 * game.kHeight), true);

   }

    public void destroy()
    {
        enemy1 =null;
        enemy2 =null;
        enemy3 = null;
        enemy4=null;
        enemy5=null;


        tower1=null;
        tower2=null;
        tower3=null;
        tower4=null;
        //    tower5=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower5), Math.round(game.tower5.width* game.kWidth), Math.round(game.tower5.height * game.kHeight), true);
        //    tower6=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower6), Math.round(game.tower6.width* game.kWidth), Math.round(game.tower6.height * game.kHeight), true);
        //    tower7=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower7), Math.round(game.tower7.width* game.kWidth), Math.round(game.tower7.height * game.kHeight), true);
        // tower8=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower8), Math.round(game.tower8.width* game.kWidth), Math.round(game.tower8.height * game.kHeight), true);
        // tower9=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower9), Math.round(game.tower9.width* game.kWidth), Math.round(game.tower9.height * game.kHeight), true);
        //tower10=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResources(), R.drawable.tower10), Math.round(game.tower10.width* game.kWidth), Math.round(game.tower10.height * game.kHeight), true);


        tower1img=null;
        tower2img=null;
        tower3img=null;
        tower4img=null;
        tower5img=null;
        tower6img=null;
        tower7img=null;
        tower8img=null;
        tower9img=null;
        tower10img=null;


        repairOff=null;
        repairOn=null;
        upgrateOff=null;
        upgrateOn=null;
        sellOn=null;
        sellOff=null;

        ability1 = null;
        ability2 = null;
        ability3 = null;
        ability4 = null;

        resumeOn=null;
        resumeOff=null;
        menuOn=null;
        menuOff=null;
        pauseOff=null;
        pauseOn=null;
        speed1On=null;
        speed2On=null;
        speed4On=null;
        speed1Off=null;
        speed2Off=null;
        speed4Off=null;

        menuOfTheEndLose=null;
        menuOfTheEndWin= null;
        retryOn= null;
        retryOff= null;
        menuEndOff= null;
        menuEndOn= null;
        nextLvlOn= null;
        nextLvlOff= null;


        mask=null;
        base=null;
        gameField =null;

        menuMask=      null;

    }



}
