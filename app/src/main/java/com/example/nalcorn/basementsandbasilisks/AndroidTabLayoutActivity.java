package com.example.nalcorn.basementsandbasilisks;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        // Tab for Character
        TabSpec charCreateSpec = tabHost.newTabSpec("Character");
        // setting Title and Icon for the Tab
        charCreateSpec.setIndicator("Character");
        Intent charIntent = new Intent(this, CharCreate.class);
        charCreateSpec.setContent(charIntent);

        // Tab for High Scores
        TabSpec HighScoreSpec = tabHost.newTabSpec("High Score");
        HighScoreSpec.setIndicator("High Score");
        Intent scoresIntent = new Intent(this, HighScore.class);
        HighScoreSpec.setContent(scoresIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(charCreateSpec); // Adding photos tab
        tabHost.addTab(HighScoreSpec); // Adding songs tab
    }
    public void onBackPressed() {
    }
    public void onPause() {
        super.onPause();
        finish();
    }
}