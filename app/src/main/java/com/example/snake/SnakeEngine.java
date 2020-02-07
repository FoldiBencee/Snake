package com.example.snake;
import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.Random;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.content.res.AssetFileDescriptor;

import android.view.SurfaceView;

public class SnakeEngine extends SurfaceView implements Runnable {

    private Thread thread = null;
    float jobb;
    float bal;
    float fel;
    float le;

    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;


    private Context context;

    public enum Heading {FEL, LE, JOBB, BAL}

    private Heading heading = Heading.JOBB;

//kepernyomeret
    private int screenX;
    private int screenY;


    private int snakeHossz;


    private int kajaX;
    private int kajaY;


    private int blockSize;
//kepernyo ahol jatszunk
    private final int NUM_BLOCKS_WIDE = 40;
    private int numBlocksHigh;

    private long nextFrameTime;
    private final long FPS = 10;

    private final long MILLIS_PER_SECOND = 1000;


    private int elertpont;


    private int[] snakeXs;
    private int[] snakeYs;

    private volatile boolean isPlaying;


    public SnakeEngine(Context context, Point size)
    {
        super(context);
        context = context;
        screenX = size.x;
        screenY = size.y;

        blockSize = screenX / NUM_BLOCKS_WIDE;
        numBlocksHigh = screenY / blockSize -5;//hogy  ne menjen le menu bar ala

        surfaceHolder = getHolder();
        paint = new Paint();


        snakeXs = new int[200];
        snakeYs = new int[200];

        ujJatek();


    }
    @Override
    public void run() {
        while (isPlaying)
        {
            if(updateRequired()) {
                update();
                draw();
            }

        }
    }
    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // error
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }




    public void ujJatek() {

        snakeHossz = 1;
        snakeXs[0] = NUM_BLOCKS_WIDE / 2;
        snakeYs[0] = numBlocksHigh / 2;


        kajaspawnolas();
        elertpont = 0;
        nextFrameTime = System.currentTimeMillis();
    }



    public void kajaspawnolas() {
        Random random = new Random();
        kajaX = random.nextInt(NUM_BLOCKS_WIDE - 1) + 1;
        kajaY = random.nextInt(numBlocksHigh - 1) + 1;
    }


    private void eat(){

        snakeHossz++;

        kajaspawnolas();

        elertpont = elertpont + 1;

    }



    private void kigyomozgatas(){
        // test
        for (int i = snakeHossz; i > 0; i--) {


            snakeXs[i] = snakeXs[i - 1];
            snakeYs[i] = snakeYs[i - 1];


        }

        //fej
        switch (heading) {
            case FEL:
                snakeYs[0]--;
                break;

            case JOBB:
                snakeXs[0]++;
                break;

            case LE:
                snakeYs[0]++;
                break;

            case BAL:
                snakeXs[0]--;
                break;
        }
    }




    private boolean halal(){

        boolean halal = false;

        //falhozeres
        if (snakeXs[0] == -1) halal = true;
        if (snakeXs[0] >= NUM_BLOCKS_WIDE) halal = true;
        if (snakeYs[0] == -1) halal = true;
        if (snakeYs[0] == numBlocksHigh) halal = true;

        //magabaharap
        for (int i = snakeHossz - 1; i > 0; i--) {
            if ((i > 4) && (snakeXs[0] == snakeXs[i]) && (snakeYs[0] == snakeYs[i])) {
                halal = true;
            }
        }

        return halal;
    }


    public void update() {
//eszikakigyo
        if (snakeXs[0] == kajaX && snakeYs[0] == kajaY) {
            eat();
        }

        kigyomozgatas();

        if (halal()) {
            ujJatek();
        }
    }


    public void draw() {

        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();

//hatter
            canvas.drawColor(Color.argb(255, 0, 00, 0));

//kigyo szin
            paint.setColor(Color.argb(255, 0, 255, 0));

            //pontozas
            paint.setTextSize(90);
            canvas.drawText("Pont: " + elertpont, 10, 70, paint);

//kigyorajz
            for (int i = 0; i < snakeHossz; i++) {
                canvas.drawRect(snakeXs[i] * blockSize,
                        (snakeYs[i] * blockSize),
                        (snakeXs[i] * blockSize) + blockSize,
                        (snakeYs[i] * blockSize) + blockSize,
                        paint);
            }

//kaja rajz
            paint.setColor(Color.argb(255, 255, 0, 0));


            canvas.drawRect(kajaX * blockSize,
                    (kajaY * blockSize),
                    (kajaX * blockSize) + blockSize,
                    (kajaY * blockSize) + blockSize,
                    paint);


            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }


    public boolean updateRequired() {

        // frame frissites
        if(nextFrameTime <= System.currentTimeMillis()){
            nextFrameTime =System.currentTimeMillis() + MILLIS_PER_SECOND / FPS;
            //igaz visszateresiertek ezert az update es a draw lefut
            return true;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionevent) {
        switch (motionevent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (motionevent.getX() >= screenX / 2) {
                    switch(heading){
                        case FEL:
                            heading = Heading.JOBB;
                            break;
                        case JOBB:
                            heading = Heading.LE;
                            break;
                        case LE:
                            heading = Heading.BAL;
                            break;
                        case BAL:
                            heading = Heading.FEL;
                            break;
                    }
                } else {
                    switch(heading){
                        case FEL:
                            heading = Heading.BAL;
                            break;
                        case BAL:
                            heading = Heading.LE;
                            break;
                        case LE:
                            heading = Heading.JOBB;
                            break;
                        case JOBB:
                            heading = Heading.FEL;
                            break;
                    }
                }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(snakeXs[0],snakeYs[0],snakeXs[0] + blockSize, snakeYs[0] + blockSize,paint);
    }
}



