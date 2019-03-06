package com.example.nalcorn.basementsandbasilisks;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class HighScore extends Activity {
    Button submit;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText plyrInput;
    final int[] playerGold = {0,0,0,0,0,0,0,0,0,0};
    final String[] playerName = {"-","-","-","-","-","-","-","-","-","-"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score);

        TextView plyrName1 = (TextView)findViewById(R.id.plyrName1);
        TextView plyrName2 = (TextView)findViewById(R.id.plyrName2);
        TextView plyrName3 = (TextView)findViewById(R.id.plyrName3);
        TextView plyrName4 = (TextView)findViewById(R.id.plyrName4);
        TextView plyrName5 = (TextView)findViewById(R.id.plyrName5);
        TextView plyrName6 = (TextView)findViewById(R.id.plyrName6);
        TextView plyrName7 = (TextView)findViewById(R.id.plyrName7);
        TextView plyrName8 = (TextView)findViewById(R.id.plyrName8);
        TextView plyrName9 = (TextView)findViewById(R.id.plyrName9);
        TextView plyrName10 = (TextView)findViewById(R.id.plyrName10);

        TextView plyrGold1 = (TextView)findViewById(R.id.plyrGold1);
        TextView plyrGold2 = (TextView)findViewById(R.id.plyrGold2);
        TextView plyrGold3 = (TextView)findViewById(R.id.plyrGold3);
        TextView plyrGold4 = (TextView)findViewById(R.id.plyrGold4);
        TextView plyrGold5 = (TextView)findViewById(R.id.plyrGold5);
        TextView plyrGold6 = (TextView)findViewById(R.id.plyrGold6);
        TextView plyrGold7 = (TextView)findViewById(R.id.plyrGold7);
        TextView plyrGold8 = (TextView)findViewById(R.id.plyrGold8);
        TextView plyrGold9 = (TextView)findViewById(R.id.plyrGold9);
        TextView plyrGold10 = (TextView)findViewById(R.id.plyrGold10);

        readFile();

        plyrName1.setText(playerName[0]);
        plyrName2.setText(playerName[1]);
        plyrName3.setText(playerName[2]);
        plyrName4.setText(playerName[3]);
        plyrName5.setText(playerName[4]);
        plyrName6.setText(playerName[5]);
        plyrName7.setText(playerName[6]);
        plyrName8.setText(playerName[7]);
        plyrName9.setText(playerName[8]);
        plyrName10.setText(playerName[9]);

        plyrGold1.setText(playerGold[0]+"");
        plyrGold2.setText(playerGold[1]+"");
        plyrGold3.setText(playerGold[2]+"");
        plyrGold4.setText(playerGold[3]+"");
        plyrGold5.setText(playerGold[4]+"");
        plyrGold6.setText(playerGold[5]+"");
        plyrGold7.setText(playerGold[6]+"");
        plyrGold8.setText(playerGold[7]+"");
        plyrGold9.setText(playerGold[8]+"");
        plyrGold10.setText(playerGold[9]+"");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            submit = (Button)findViewById(R.id.Submit);
            radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
            submit.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            plyrInput = (EditText)findViewById(R.id.plyrInput);

            String playerschoice = "";
            int gold = 0;
            if(extras.containsKey("player")) {
                playerschoice = extras.getString("player");
            }
            if(extras.containsKey("gold")){
                gold = extras.getInt("gold");
            }
            if(!playerschoice.equals("")) {
                Toast.makeText(
                        getApplicationContext(),
                        playerschoice + " won " + gold + " gold",
                        Toast.LENGTH_SHORT).show();
            }
            if(compareGold(gold)){
                plyrInput.setVisibility(View.VISIBLE);
            }
            final int finalGold = gold;
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    insertNewScore(plyrInput.getText().toString(), finalGold);
                    writeFile();
                    radioButton = (RadioButton) findViewById(selectedId);
                    if(radioButton.getText().equals("Continue")){
                        startActivity(new Intent(HighScore.this, AndroidTabLayoutActivity.class));
                    }else{
                        finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                    }

                }

            });
        }
    }
    public void onBackPressed() {
    }
    public boolean compareGold(int gold){
        for(int i = 0; i < 10; i++){
            if(gold > playerGold[i]){
                return true;
            }
        }
        return false;
    }
    public void insertNewScore(String name, int gold){
        String tempName;
        int tempGold;
        for(int i = 0; i < 10; i++){
            if(gold > playerGold[i]){
                tempGold = playerGold[i];
                tempName = playerName[i];
                playerGold[i] = gold;
                playerName[i] = name;
                gold = tempGold;
                name = tempName;
            }
        }
    }
    public void writeFile(){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i =0; i<10; i++){
            editor.putString("player"+i, playerName[i]);
            editor.putInt("gold"+i, playerGold[i]);
        }
        editor.commit();
    }
    public void readFile(){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        for(int i = 0; i<10; i++){
            playerName[i] = sharedPreferences.getString("player"+i,"-");
            playerGold[i] = sharedPreferences.getInt("gold"+i,0);
        }
    }
}