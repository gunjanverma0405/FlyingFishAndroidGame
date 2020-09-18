package com.example.flyingfishgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private Button b1,b2;
    private TextView t1;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        b1=(Button)findViewById(R.id.playagain);
        b2=(Button)findViewById(R.id.btm);
        t1=(TextView)findViewById(R.id.score);

        score=getIntent().getExtras().get("score").toString();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pa=new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(pa);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btm=new Intent(GameOverActivity.this,SplashActivity.class);
                startActivity(btm);
            }
        });

        t1.setText("Score: "+score);

    }
}
