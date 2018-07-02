package com.example.towerdefence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import com.example.towerdefence.Game;
import com.example.towerdefence.R;

import java.util.LinkedList;

public class LevelActivity extends Activity {
    public float height, width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        LinkedList<Images> imageses= (LinkedList)getIntent().getExtras().getParcelable("images");
        setContentView(new com.example.towerdefence.Levels(getApplicationContext(), metrics.widthPixels, metrics.heightPixels, LevelActivity.this));


    }
    @Override
    public void onBackPressed(){

    }
public void exit()
{
    this.startActivity(new Intent(this,MainMenuActivity.class));
    LevelActivity.this.finish();
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
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