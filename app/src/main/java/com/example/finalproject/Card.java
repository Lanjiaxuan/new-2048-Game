package com.example.finalproject;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

    public Card(Context context) {
        super(context);

        // initialize tvNumber
        anotherNumber = new TextView(getContext());
        anotherNumber.setTextSize(32);
        anotherNumber.setGravity(Gravity.CENTER);
        anotherNumber.setBackgroundColor(0x33ffffff);

        // add tvNumber
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(anotherNumber, lp);

        setNumber(0);   // set number to be 0
    }

    private int number = 0;
    private TextView anotherNumber;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;   // set number

        if(number > 0) {
            anotherNumber.setText(number + "");  // set tvNumber
        } else {
            anotherNumber.setText("");   //
        }

        switch (number) {
            case 0:
                anotherNumber.setBackgroundColor(0x33FFFFFF);
                break;
            case 2:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor2));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor2));
                break;
            case 4:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor4));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor4));
                break;
            case 8:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor8));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor8));
                break;
            case 16:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor16));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor16));
                break;
            case 32:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor32));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor32));
                break;
            case 64:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor64));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor64));
                break;
            case 128:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor128));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor128));
                break;
            case 256:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor256));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor256));
                break;
            case 512:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor512));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor512));
                break;
            case 1024:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor1024));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor1024));
                break;
            case 2048:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorFor2048));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorFor2048));
                break;
            default:
                anotherNumber.setTextColor(getResources().getColor(R.color.textColorForsuper));
                anotherNumber.setBackgroundColor(getResources().getColor(R.color.backgroundColorForsuper));
                break;
        }

    }

    public boolean equals(Card o) {
        return getNumber() == o.getNumber();
    }


}
