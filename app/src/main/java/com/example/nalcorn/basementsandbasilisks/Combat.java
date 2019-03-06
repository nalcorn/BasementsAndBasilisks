package com.example.nalcorn.basementsandbasilisks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by nicho_000 on 11/22/2015.
 */
public class Combat extends Activity {
    String playerChoice, monsterChoice, combatChoice;
    int animTime = 5500;
    Creature player = new Creature();
    Creature monster = new Creature();
    int count;
    MediaPlayer musicPlayer;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat);
        musicPlayer = MediaPlayer.create(Combat.this, R.raw.music);
        musicPlayer.setLooping(true);
        musicPlayer.start();
        Intent intent = getIntent();
        playerChoice = intent.getExtras().getString("player");
        int i = intent.getExtras().getInt("gold");
        player.setGoldValue(i);
        count = 0;
        final ImageView imgP = (ImageView) findViewById(R.id.imgPlayer);
        final ImageView imgM = (ImageView) findViewById(R.id.imgMonster);
        createMonsterAnimation(imgM);
        createPlayerAnimation(imgP);
        TextView playerType = (TextView) findViewById((R.id.playerType));
        final TextView monsterType = (TextView) findViewById((R.id.monsterType));
        playerType.setText(playerChoice);
        monsterType.setText(monsterChoice);
        final TextView playerHP = (TextView) findViewById((R.id.playerHealth));
        final TextView monsterHP = (TextView) findViewById((R.id.monsterHealth));
        playerHP.setText("HP: "+player.getHP());
        monsterHP.setText("HP: "+monster.getHP());
        final Spinner spin = (Spinner) findViewById(R.id.spnAttackHeal);
        final Button attack = (Button) findViewById(R.id.btnAttackHeal);

        spin.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                combatChoice = spin.getSelectedItem().toString();
                attack.setText(combatChoice);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                combatChoice = spin.getSelectedItem().toString();
                attack.setText(combatChoice);
            }
        });
        attack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count++;
                if (spin.getSelectedItem().toString().toLowerCase().equals("heal")) {
                    int i = player.attack();
                    player.heal(i);
                    playerHP.setText("HP: " + player.getHP());
                }else{
                    int i = player.attack();
                    monster.takeDamage(i);
                    monsterHP.setText("HP: " + monster.getHP());
                    if (monster.getHP() > 0) {
                        int j = monster.monsterAttack();
                        player.takeDamage(j);
                        playerHP.setText("HP: " + player.getHP());
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                "The Monster died",
                                Toast.LENGTH_SHORT).show();
                        player.setGoldValue(
                                (player.getGoldValue() + monster.getGoldValue())/count);
                        onCombatEnd();
                    }
                    if (player.getHP() <= 0){
                        Toast.makeText(
                                getApplicationContext(),
                                "You died",
                                Toast.LENGTH_SHORT).show();
                        onCombatEnd();
                    }
                }
                animateAttacks(imgM, imgP);
                Thread thread1 = new Thread() {
                    public void run() {
                        try {
                            sleep(animTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread1.start();
            }
        });
    }
    public void onCombatEnd(){
        Intent intent = new Intent(Combat.this, HighScore.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle extras = new Bundle();
        extras.putString("player", playerChoice);
        extras.putInt("gold",player.getGoldValue());
        intent.putExtras(extras);
        musicPlayer.stop();
        startActivity(intent);
    }
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
    public void onBackPressed() {
    }
    public void animateAttacks(ImageView imgM, ImageView imgP){
        final AnimationDrawable frameAnimationP = (AnimationDrawable) imgP.getBackground();
        final AnimationDrawable frameAnimationM = (AnimationDrawable) imgM.getBackground();

        Thread thread1 = new Thread() {
            public void run(){
                try {
                    sleep(animTime);
                    frameAnimationP.stop();
                    frameAnimationM.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        frameAnimationP.setVisible(true, true);
                        frameAnimationM.setVisible(true, true);
                        frameAnimationP.start();
                        if(monster.getHP()>0) {
                            frameAnimationM.start();
                        }
                    }
                });
            }
        }).start();
        thread1.start();
    }
    public void createMonsterAnimation(ImageView imgM){
        Random r = new Random();
        int Low = 1;
        int High = 7;
        int mons = r.nextInt(High-Low) + Low;
        switch(mons){
            case 1:
                monster.setCreature("cyclops");
                imgM.setBackgroundResource(R.drawable.cyclops_anim);
                monsterChoice = "Cyclops";
                break;
            case 6:
                monster.setCreature("dragon");
                imgM.setBackgroundResource(R.drawable.dragon_anim);
                monsterChoice = "Dragon";
                break;
            case 3:
                monster.setCreature("gorgon");
                imgM.setBackgroundResource(R.drawable.gorgon_anim);
                monsterChoice = "Gorgon";
                break;
            case 4:
                monster.setCreature("spider");
                imgM.setBackgroundResource(R.drawable.spider_anim);
                monsterChoice = "Spider";
                break;
            case 5:
                monster.setCreature("wolf");
                imgM.setBackgroundResource(R.drawable.wolf_anim);
                monsterChoice = "Wolf";
                break;
            case 2:
                monster.setCreature("zombie");
                imgM.setBackgroundResource(R.drawable.zombie_anim);
                monsterChoice = "Zombie";
                break;
        }
    }
    public void createPlayerAnimation(ImageView imgP){
        player.setCreature(playerChoice);
        playerChoice = player.getName();
        switch (playerChoice.toLowerCase()) {
            case "female archer":
                imgP.setBackgroundResource(R.drawable.female_archer_anim);
                break;
            case "female fighter":
                imgP.setBackgroundResource(R.drawable.female_fighter_anim);
                break;
            case "female mage":
                imgP.setBackgroundResource(R.drawable.female_mage_anim);
                break;
            case "male archer":
                imgP.setBackgroundResource(R.drawable.male_archer_anim);
                break;
            case "male fighter":
                imgP.setBackgroundResource(R.drawable.male_fighter_anim);
                break;
            case "male mage":
                imgP.setBackgroundResource(R.drawable.male_mage_anim);
                break;
        }
    }
}
