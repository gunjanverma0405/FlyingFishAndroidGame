package com.example.flyingfishgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class flyingfishview extends View {

    private Bitmap fish[]= new Bitmap[2];
    private int fishX=10;
    private int fishY;
    private int fishspeed;

    private int canvasWidth, canvasHeight;

    private int yellowX, yellowY, yellowSpeed=15;
    private Paint yellowPaint= new Paint();

    private int greenX, greenY, greenSpeed=20;
    private Paint greenPaint= new Paint();

    private int redX, redY, redSpeed=25;
    private Paint redPaint= new Paint();

    private int score,lifeCounterofFish;

    private boolean touch=false;

    private Bitmap bgimage;

    private Paint scorep= new Paint();

    private Bitmap life[]=new Bitmap[2];

    public flyingfishview(Context context) {
        super(context);

        fish[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1]= BitmapFactory.decodeResource(getResources(),R.drawable.fish2);

        bgimage=BitmapFactory.decodeResource(getResources(),R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorep.setColor(Color.WHITE);
        scorep.setTextSize(70);
        scorep.setTypeface(Typeface.DEFAULT_BOLD);
        scorep.setAntiAlias(true);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        fishY=550;
        score=0;
        lifeCounterofFish=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight();

        canvas.drawBitmap(bgimage,0,0,null);

        int minFishY=fish[0].getHeight();
        int maxFishY=canvasHeight-fish[0].getHeight()*3;
        fishY=fishY+fishspeed;

        if(fishY<minFishY)
        {
            fishY=minFishY;
        }

        if(fishY>maxFishY)
        {
            fishY=maxFishY;
        }
        fishspeed=fishspeed+2;

        if(touch)
        {
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch=false;
        }
        else
        {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }


        yellowX=yellowX-yellowSpeed;

        if(hitballchecker(yellowX,yellowY))
        {
            score=score+10;
            yellowX=-100;
        }

        if(yellowX<0)
        {
            yellowX=canvasWidth+21;
            yellowY=(int)Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;
        }
        canvas.drawCircle(yellowX,yellowY,25,yellowPaint);

        greenX=greenX-greenSpeed;

        if(hitballchecker(greenX,greenY))
        {
            score=score+20;
            greenX=-100;
        }

        if(greenX<0)
        {
            greenX=canvasWidth+21;
            greenY=(int)Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;
        }
        canvas.drawCircle(greenX,greenY,28,greenPaint);

        redX=redX-redSpeed;

        if(hitballchecker(redX,redY))
        {
            score=score-5;
            redX=-100;
            lifeCounterofFish--;
            if(lifeCounterofFish==0)
            {
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_LONG).show();

                Intent gameoverintent= new Intent(getContext(),GameOverActivity.class);
                gameoverintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameoverintent.putExtra("score",score);
                getContext().startActivity(gameoverintent);
            }
        }

        if(redX<0)
        {
            redX=canvasWidth+21;
            redY=(int)Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;
        }
        canvas.drawCircle(redX,redY,30,redPaint);

        canvas.drawText("Score : "+score, 20, 60, scorep);

        for(int i=0;i<3;i++)
        {
            int x=(int)(580+life[0].getWidth()*1.5*i);
            int y=30;

            if(i<lifeCounterofFish)
                canvas.drawBitmap(life[0],x,y,null);
            else
                canvas.drawBitmap(life[1],x,y,null);
        }


    }

    public boolean hitballchecker(int x, int y)
    {
        if(fishX<x && x<(fishX+fish[0].getWidth())&& fishY<y && y<(fishY+fish[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            touch=true;
            fishspeed=-30;
        }
        return  true;
    }
}
