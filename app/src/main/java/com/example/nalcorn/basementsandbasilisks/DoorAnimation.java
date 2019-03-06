package com.example.nalcorn.basementsandbasilisks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by nicho_000 on 11/22/2015.
 */
public class DoorAnimation extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door_splash);
        doorAnimation();
    }
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
    public void doorAnimation(){

        final String playerChoice;
        final int gold;
        Intent intent = getIntent();
        playerChoice = intent.getExtras().getString("player");
        gold = intent.getExtras().getInt("gold");
        ImageView img = (ImageView) findViewById(R.id.imgDoor);
        img.setBackgroundResource(R.drawable.door_anim);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.setVisible(true,true);
        frameAnimation.start();

        Thread thread = new Thread() {
            public void run(){
                try {
                    sleep(2000);
                    Intent intent = new Intent(DoorAnimation.this, Combat.class);
                    Bundle extras = new Bundle();
                    extras.putString("player", playerChoice);
                    extras.putInt("gold", 0);
                    intent.putExtras(extras);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
