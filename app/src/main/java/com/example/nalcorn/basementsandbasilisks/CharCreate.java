package com.example.nalcorn.basementsandbasilisks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CharCreate extends Activity {
    String playerChoice;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.char_create);
        final String cSelect = "The character selected is the ";

        ImageButton btnFArcher = (ImageButton) findViewById(R.id.imageButton);
        ImageButton btnFFighter = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton btnFMage = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton btnMArcher = (ImageButton) findViewById(R.id.imageButton4);
        ImageButton btnMFighter = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton btnMMage = (ImageButton) findViewById(R.id.imageButton6);
        Button btnStart = (Button) findViewById(R.id.btnCreateChar);
        final TextView result = ((TextView)findViewById(R.id.characterSelection));


        btnStart.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(CharCreate.this, DoorAnimation.class);
                Bundle extras = new Bundle();
                extras.putString("player",playerChoice);
                extras.putInt("gold",0);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        btnFArcher.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                result.setText(cSelect + "Female Archer.");
                playerChoice = "female_archer";
            }
        });
        btnFFighter.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                result.setText(cSelect + "Female Fighter.");
                playerChoice = "female_fighter";
            }
        });
        btnFMage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                result.setText(cSelect + "Female Mage.");
                playerChoice = "female_mage";
            }
        });
        btnMArcher.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                result.setText(cSelect + "Male Archer.");
                playerChoice = "male_archer";
            }
        });
        btnMFighter.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                result.setText(cSelect + "Male Fighter.");
                playerChoice = "male_fighter";
            }
        });
        btnMMage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                result.setText(cSelect + "Male Mage.");
                playerChoice = "male_mage";

            }
        });
    }
    public void onBackPressed() {
    }
}