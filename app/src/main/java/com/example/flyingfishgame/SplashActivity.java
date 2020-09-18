package com.example.flyingfishgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity{
    private Button startthegame, rule, aboutthegame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startthegame = (Button) findViewById(R.id.startthegame);
        rule = (Button) findViewById(R.id.rule);
        aboutthegame = (Button) findViewById(R.id.aboutthegame);

        startthegame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starti = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(starti);
            }
        });

        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rulesi = new Intent(SplashActivity.this, Rules.class);
                startActivity(rulesi);
            }
        });

        aboutthegame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atgi = new Intent(SplashActivity.this, Aboutthegame.class);
                startActivity(atgi);
            }
        });
    }
}
