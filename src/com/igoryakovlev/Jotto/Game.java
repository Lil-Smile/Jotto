package com.igoryakovlev.Jotto;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

    private static ArrayList<String> data = new ArrayList<String>();




    private int randomNumber=0;
    private String wordToGuess;
    private int count=0;

    Button buttonGetIt;
    Button buttonPlay;
    EditText etWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        //init data
        data = fillTheData();

        buttonGetIt = (Button)findViewById(R.id.buttonGetIt);
        buttonPlay = (Button)findViewById(R.id.buttonPlay);
        etWord = (EditText)findViewById(R.id.etWord);

        buttonPlay.setOnClickListener(this);
        buttonGetIt.setOnClickListener(this);


        Random random = new Random(data.size());
        randomNumber = random.nextInt(data.size()+1);
        wordToGuess = data.get(randomNumber);
        Log.d("word",wordToGuess);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonGetIt:
            {
                this.count++;
                String word = etWord.getText().toString();
                word = word.toLowerCase();
                if (word.length()!=5)
                {
                    Toast.makeText(getApplicationContext(),getString(R.string.wrongLength),Toast.LENGTH_LONG).show();
                } else if (word == wordToGuess)
                {
                    if (count==1)
                    {
                        Toast.makeText(getApplicationContext(), getString(R.string.won)+" " + this.count+" "+ getString(R.string.attempt),Toast.LENGTH_LONG).show();

                    } else
                    {
                        Toast.makeText(getApplicationContext(), getString(R.string.won)+" " +this.count+" " +getString(R.string.attempts),Toast.LENGTH_LONG).show();
                    }
                    buttonPlay.setVisibility(View.VISIBLE);
                    buttonGetIt.setVisibility(View.INVISIBLE);
                } else if (!checkingWord(word))
                {
                    Toast.makeText(getApplicationContext(),getString(R.string.wrongLetters),Toast.LENGTH_LONG).show();
                } else
                {
                    int tmpCount=0;

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
                    if (tmpCount<5)
                    {
                        Toast.makeText(getApplicationContext(),getString(R.string.sameLetters)+tmpCount,Toast.LENGTH_LONG).show();
                    } else
                    {
                        if (count==1)
                        {
                            Toast.makeText(getApplicationContext(), getString(R.string.won)+" " +this.count+" " +getString(R.string.attempt),Toast.LENGTH_LONG).show();

                        } else
                        {
                            Toast.makeText(getApplicationContext(), getString(R.string.won)+" " +this.count+" " +getString(R.string.attempts),Toast.LENGTH_LONG).show();
                        }
                        buttonPlay.setVisibility(View.VISIBLE);
                        buttonGetIt.setVisibility(View.INVISIBLE);
                    }
                }
                etWord.setText("");
                break;
            }
            case R.id.buttonPlay:
            {
                this.count=0;
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

    private ArrayList<String> fillTheData()
    {
        ArrayList<String> data = new ArrayList<String>();
        data.add("кровь");
        data.add("песок");
        return data;

    }









}