package com.igoryakovlev.Jotto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */

    Button play;
    Button rules;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        play = (Button)findViewById(R.id.buttonStartPlaying);
        rules = (Button)findViewById(R.id.startRules);

        play.setOnClickListener(this);
        rules.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.buttonStartPlaying:
            {
                intent = new Intent(MyActivity.this, Game.class);
                break;
            }
            case R.id.startRules:
            {
                intent = new Intent(MyActivity.this,Rules.class);
                break;
            }
        }
        startActivity(intent);
    }
}
