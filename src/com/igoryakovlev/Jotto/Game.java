package com.igoryakovlev.Jotto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Smile on 02.09.15.
 */
public class Game extends Activity implements View.OnClickListener{

    private static ArrayList<String> data;
    static {
        data.add("Кровь");
        data.add("Песок");
    }

    private int randomNumber=0;
    private String wordToGuess;
    private int count;

    Button buttonGetIt;
    Button buttonPlay;
    EditText etWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        buttonGetIt = (Button)findViewById(R.id.buttonGetIt);
        buttonPlay = (Button)findViewById(R.id.buttonPlay);
        etWord = (EditText)findViewById(R.id.etWord);

        buttonPlay.setOnClickListener(this);
        buttonGetIt.setOnClickListener(this);

        count = 0;
        Random random = new Random(data.size());
        randomNumber = random.nextInt();
        wordToGuess = data.get(randomNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonGetIt:
            {
                String word = etWord.getText().toString();
                if (word.length()!=5)
                {
                    Toast.makeText(getApplicationContext(),getString(R.string.wrongWord),Toast.LENGTH_LONG).show();
                } else if (word == wordToGuess)
                {
                    Toast.makeText(getApplicationContext(), getString(R.string.won)+count+R.string.attempts,Toast.LENGTH_LONG).show();
                    buttonPlay.setVisibility(View.VISIBLE);
                    buttonGetIt.setVisibility(View.INVISIBLE);
                } else if (checkingWord(word))
                {
                    Toast.makeText(getApplicationContext(),getString(R.string.wrongWord),Toast.LENGTH_LONG).show();
                } else
                {
                    int tmpCount=0;
                    count++;
                    for (int i = 0; i<word.length(); i++)
                    {
                        char c = word.charAt(i);
                        for (int j = 0; j<wordToGuess.length(); j++)
                        {
                            if (c==wordToGuess.charAt(j))
                            {
                                tmpCount++;
                                break;
                            }
                        }
                    }
                    Toast.makeText(getApplicationContext(),getString(R.string.sameLetters)+tmpCount,Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.buttonPlay:
            {
                buttonGetIt.setVisibility(View.VISIBLE);
                buttonPlay.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),getString(R.string.gameStart),Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    private boolean checkingWord(String word)
    {
        for (int i = 0; i<word.length(); i++)
        {
            char c = word.charAt(i);
            for (int j = i+1; j<word.length(); j++)
            {
                if (c==word.charAt(j))
                {
                    return false;
                }
            }
        }
        return true;
    }








}