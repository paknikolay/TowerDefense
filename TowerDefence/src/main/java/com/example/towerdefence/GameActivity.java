package com.example.towerdefence;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.towerdefence.Game;
import com.example.towerdefence.R;

public class GameActivity extends Activity {
    public float height, width;
    public boolean isOn=true;
public Images images;
    AlertDialog.Builder pauseMenu;
    AlertDialog.Builder exitQ;
    Context context;
    public int paused=0;
    public int level=1;
    public int yesPlace = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);


        // setContentView(new Game(getApplicationContext(),metrics.xdpi,metrics.ydpi));
        context = GameActivity.this;

        pauseMenu = new AlertDialog.Builder(context);
        pauseMenu.setTitle(R.string.menu);  // заголовок
        pauseMenu.setPositiveButton(R.string.resume, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(context, R.string.resumed,
                        Toast.LENGTH_SHORT).show();paused=1;
            }
        });
        pauseMenu.setNegativeButton(R.string.mainMenu, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                exitQ.show();
              //TODO убрать

            }
        });
        pauseMenu.setCancelable(false);


        exitQ = new AlertDialog.Builder(context);
        exitQ.setTitle(R.string.doYouWantToExit);  // заголовок
        exitQ.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                isOn=false;
                exit();;
            }
        });
        exitQ.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                paused=1;
            }
        });
        exitQ.setCancelable(false);

level=(int)getIntent().getExtras().getInt("level");

        setContentView(new Game(getApplicationContext(), metrics.widthPixels, metrics.heightPixels,level,  GameActivity.this));

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        images.destroy();
    }
public void exit()
{
isOn=false;
    images.destroy();
    {
        try {
            synchronized (this){   wait(60);}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    this.startActivity(new Intent(this,MainMenuActivity.class));
    this.finish();



}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }
    @Override
    public void onBackPressed(){

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
