package com.example.finalproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class GameView extends GridLayout {


    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attriSet) {
        super(context, attriSet);
        initGameView();
    }

    public GameView(Context context, AttributeSet attriSet, int definitionStyle) {
        super(context, attriSet, definitionStyle);
        initGameView();
    }

    private void initGameView() {
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new View.OnTouchListener() {

            private float startPositionOne, startPositionTwo, endPositionOne, endPositionTwo;
            private float moveX, moveY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        startPositionOne = event.getX();
                        startPositionTwo = event.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        endPositionOne = event.getX();
                        endPositionTwo = event.getY();

                        moveX = endPositionOne - startPositionOne;
                        moveY = endPositionTwo - startPositionTwo;

                        if (Math.abs(moveX) > Math.abs(moveY)) {
                            if (moveX < -3) {
                                shiftUp();
                            } else if (moveX > 3) {
                                shiftDown();
                            }
                        } else {
                            if (moveY < -3) {
                                shiftLeft();
                            } else if (moveY > 3) {
                                shiftRight();

                            }
                        }
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int width, int height, int origWidth, int origHeight) {
        super.onSizeChanged(width, height, origWidth, origHeight);

        int cardWidth = (Math.min(width, height) - 10) / 4;
        addCards(cardWidth, cardWidth);

        gameStart();
    }


    private void addCards(final int cardWidth, final int cardHeight) {
        Card card;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                card = new Card(getContext());
                card.setNumber(0);
                addView(card, cardWidth, cardHeight);
                MainActivity.Board[j][i] = card;
            }
        }
    }






    public static void rememberEachStep(Card[][] input) {
        MainActivity.pastPositions.add(input);
    }

    public static void gameStart() {
        MainActivity.getMainActivity().clearScore();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                MainActivity.Board[j][i].setNumber(0);
            }
        }
        addRandomNumber();
        addRandomNumber();
    }

    public static void addRandomNumber() {
        MainActivity.emptyPositions.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (MainActivity.Board[j][i].getNumber() <= 0) {
                    MainActivity.emptyPositions.add(new Point(j, i));
                }
            }
        }
        Point position = MainActivity.emptyPositions.remove((int)(Math.random()* MainActivity.emptyPositions.size()));

        MainActivity.Board[position.x][position.y].setNumber(Math.random()>0.1?2048:1024);

    }


    private void shiftUp() {

        Card[][] newBoard = new Card[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(getContext());
                card.setNumber(0);
                newBoard[i][j] = card;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newBoard[i][j].setNumber(MainActivity.Board[i][j].getNumber());
            }
        }
        MainActivity.pastPositions.add(newBoard);

        boolean combined = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int position = j + 1; position < 4; position++) {
                    if (MainActivity.Board[position][i].getNumber() > 0) {
                        if (MainActivity.Board[j][i].getNumber() <= 0) {
                            MainActivity.Board[j][i].setNumber(MainActivity.Board[position][i].getNumber());
                            MainActivity.Board[position][i].setNumber(0);
                            j--;
                            combined = true;

                        } else if (MainActivity.Board[j][i].equals(MainActivity.Board[position][i])) {
                            MainActivity.Board[j][i].setNumber(MainActivity.Board[j][i].getNumber() / 2);
                            MainActivity.Board[position][i].setNumber(0);
                            MainActivity.getMainActivity().addScore(MainActivity.Board[j][i].getNumber());
                            combined = true;
                        }
                        break;
                    }
                }
            }
        }
        if (combined) {
            addRandomNumber();
            isGameOver();
        }
    }


    private void shiftDown() {

        Card[][] newBoard = new Card[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(getContext());
                card.setNumber(0);
                newBoard[i][j] = card;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newBoard[i][j].setNumber(MainActivity.Board[i][j].getNumber());
            }
        }
        MainActivity.pastPositions.add(newBoard);

        boolean combined = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                for (int position = j - 1; position >= 0; position--) {
                    if (MainActivity.Board[position][i].getNumber() > 0) {
                        if (MainActivity.Board[j][i].getNumber() <= 0) {
                            MainActivity.Board[j][i].setNumber(MainActivity.Board[position][i].getNumber());
                            MainActivity.Board[position][i].setNumber(0);
                            j++;
                            combined = true;

                        } else if (MainActivity.Board[j][i].equals(MainActivity.Board[position][i])) {
                            MainActivity.Board[j][i].setNumber(MainActivity.Board[j][i].getNumber() / 2);
                            MainActivity.Board[position][i].setNumber(0);
                            MainActivity.getMainActivity().addScore(MainActivity.Board[j][i].getNumber());
                            combined = true;
                        }
                        break;
                    }
                }
            }
        }
        if (combined) {
            addRandomNumber();
            isGameOver();
        }
    }


    private void shiftLeft() {

        Card[][] newBoard = new Card[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(getContext());
                card.setNumber(0);
                newBoard[i][j] = card;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newBoard[i][j].setNumber(MainActivity.Board[i][j].getNumber());
            }
        }
        MainActivity.pastPositions.add(newBoard);

        boolean combined = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int position = j + 1; position < 4; position++) {
                    if (MainActivity.Board[i][position].getNumber() > 0) {
                        if (MainActivity.Board[i][j].getNumber() <= 0) {
                            MainActivity.Board[i][j].setNumber(MainActivity.Board[i][position].getNumber());
                            MainActivity.Board[i][position].setNumber(0);
                            j--;
                            combined = true;

                        } else if (MainActivity.Board[i][j].equals(MainActivity.Board[i][position])) {
                            MainActivity.Board[i][j].setNumber(MainActivity.Board[i][j].getNumber() / 2);
                            MainActivity.Board[i][position].setNumber(0);
                            MainActivity.getMainActivity().addScore(MainActivity.Board[i][j].getNumber());
                            combined = true;
                        }
                        break;
                    }
                }
            }
        }
        if (combined) {
            addRandomNumber();
            isGameOver();
        }
    }


    private void shiftRight() {

        Card[][] newBoard = new Card[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(getContext());
                card.setNumber(0);
                newBoard[i][j] = card;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newBoard[i][j].setNumber(MainActivity.Board[i][j].getNumber());
            }
        }
        MainActivity.pastPositions.add(newBoard);

        boolean combined = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                for (int position = j - 1; position >= 0; position--) {
                    if (MainActivity.Board[i][position].getNumber() > 0) {
                        if (MainActivity.Board[i][j].getNumber() <= 0) {
                            MainActivity.Board[i][j].setNumber(MainActivity.Board[i][position].getNumber());
                            MainActivity.Board[i][position].setNumber(0);
                            j++;
                            combined = true;

                        } else if (MainActivity.Board[i][j].equals(MainActivity.Board[i][position])) {
                            MainActivity.Board[i][j].setNumber(MainActivity.Board[i][j].getNumber() / 2);
                            MainActivity.Board[i][position].setNumber(0);
                            MainActivity.getMainActivity().addScore(MainActivity.Board[i][j].getNumber());
                            combined = true;
                        }
                        break;
                    }
                }
            }
        }
        if (combined) {
            addRandomNumber();
            isGameOver();
        }
    }


    private void isGameOver() {
        boolean hasEnded = true;
        all:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (MainActivity.Board[j][i].getNumber() == 0 || (j > 1 && MainActivity.Board[j][i].equals(MainActivity.Board[j - 1][i]))
                        || (j < 3 && MainActivity.Board[j][i].equals(MainActivity.Board[j + 1][i]))
                        || (i > 0 && MainActivity.Board[j][i].equals(MainActivity.Board[j][i - 1]))
                        || (i < 3 && MainActivity.Board[j][i].equals(MainActivity.Board[j][i + 1]))) {
                    hasEnded = false;
                    break all;
                }
            }
        }
        MainActivity.getMainActivity().getHighestScore(MainActivity.getMainActivity().score);
        if (hasEnded) {
            new AlertDialog.Builder(getContext()).setTitle("Sorry").setMessage("Game over").setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gameStart();
                }
            }).show();
        }
    }
}

