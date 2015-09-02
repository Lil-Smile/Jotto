package com.igoryakovlev.Jotto;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Smile on 02.09.15.
 */
public class Game extends Activity {

    private static ArrayList<String> data;
    static {
        data.add("Кровь");
    }

    private int randomNumber=0;
    private String word;

    Button buttonGetIt;
    Button buttonPlay;
    EditText etWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        

        Random random = new Random(data.size());
        randomNumber = random.nextInt();
        word = data.get(randomNumber);
    }

}