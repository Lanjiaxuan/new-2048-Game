package com.example.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public int score = 0;

    private TextView tvScore;

    private static MainActivity mainActivity = null;

    public static List<Point> emptyPositions = new ArrayList<Point>();

    public static List<Card[][]> pastPositions = new ArrayList<Card[][]>();

    //private final int boardSize = 4;

    public static Card[][] Board = new Card[4][4];

    public MainActivity() {
        mainActivity = this;
    }

    public void undoBoard() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Card[][] newBoard = pastPositions.get(pastPositions.size() - 1);
                //System.out.println(newBoard[i][j].getNumber());
                Board[i][j].setNumber(newBoard[i][j].getNumber());
            }
        }
        pastPositions.remove(pastPositions.size() - 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = (TextView) findViewById(R.id.tvScore);

        final Button buttonRestart = findViewById(R.id.restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameView.gameStart();
            }
        });

        final Button buttonUndo = findViewById(R.id.undo);
        buttonUndo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                undoBoard();
            }
        });
    }


    public void clearScore () {
        score = 0;
        showScore();
    }
    public void showScore () {
        tvScore.setText(score + " ");
    }

    public void addScore (int s) {
        int number = 0;
        if (s == 1024) {
            number = 4;
        }
        if (s == 512) {
            number = 8;
        }
        if (s == 256) {
            number = 16;
        }
        if (s == 128) {
            number = 32;
        }
        if (s == 64) {
            number = 64;
        }
        if (s == 32) {
            number = 128;
        }
        if (s == 16) {
            number = 256;
        }
        if (s == 8) {
            number = 512;
        }
        if (s == 4) {
            number = 1024;
        }
        if (s == 2) {
            number = 2048;
        }
        score += number;
        showScore();
    }

    public void getHighestScore(int gameScore) {
        TextView HighestScore = (TextView) findViewById(R.id.HighestScore);
        SharedPreferences settings = getSharedPreferences("HighestScore : ", Context.MODE_PRIVATE);
        int highestScore = settings.getInt("Highest Score : ", 0);

        if (highestScore > gameScore) {
            HighestScore.setText("Highest Score : " + highestScore);
        } else {
            HighestScore.setText("Highest Score : " + gameScore);
            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("Highest Score : ", gameScore);
            editor.commit();
        }
    }

    public static MainActivity getMainActivity () {
        return mainActivity;
    }
}
