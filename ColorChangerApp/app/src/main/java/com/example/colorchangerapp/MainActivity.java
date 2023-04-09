/*
---------------------------------------------------------------------------------------
Name: Rainbow colors
Author: Benny Cascarino
Date: 09.04.2023
---------------------------------------------------------------------------------------
 */

package com.example.colorchangerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int[] rainbowColors = {Color.RED, Color.parseColor("#FFA500"), Color.YELLOW,
            Color.GREEN, Color.BLUE, Color.parseColor("#8B00FF")};
    int randomColor = rainbowColors[new Random().nextInt(rainbowColors.length)];
    public void topClick(View view) {
        Button topButton = (Button) findViewById(R.id.topButton);
        topButton.setBackgroundColor(randomColor);
    }
    public void bottomClick(View view) {
        Button bottomButton = (Button) findViewById(R.id.bottomButton);
        bottomButton.setBackgroundColor(randomColor);
    }
    public void leftClick(View view) {
        Button leftButton = (Button) findViewById(R.id.leftButton);
        leftButton.setBackgroundColor(randomColor);
    }
    public void rightClick(View view) {
        Button rightButton = (Button) findViewById(R.id.rightButton);
        rightButton.setBackgroundColor(randomColor);
    }
}