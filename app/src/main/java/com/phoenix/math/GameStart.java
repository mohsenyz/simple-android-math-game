package com.phoenix.math;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameStart extends AppCompatActivity {

    int color2 = Color.parseColor("#57caa8");
    int color3 = Color.parseColor("#7141e2");
    int color4 = Color.parseColor("#c44e4e");
    int color5 = Color.parseColor("#c6b147");
    int color6 = Color.parseColor("#44c74b");
    int color7 = Color.parseColor("#d46cb3");
    int colors[] = {color2, color3, color4, color5, color6, color7};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
    }
}
