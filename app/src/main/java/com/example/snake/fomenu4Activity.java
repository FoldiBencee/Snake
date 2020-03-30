package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class fomenu4Activity extends AppCompatActivity {
    private Button buttonjatekinditas,buttonTopPont,buttonKijeletnkezes,buttonKilepes;
    MediaPlayer fomenuzene;
private ImageView imageViewmute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomenu4);
        init();
        fomenuzene = MediaPlayer.create(fomenu4Activity.this, R.raw.fomenuzene);
        fomenuzene.start();


        buttonKijeletnkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kezdokepernyore_lepes = new Intent(fomenu4Activity.this,MainActivity.class);
                startActivity(kezdokepernyore_lepes);
                finish();
            }
        });

        buttonKilepes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);


            }
        });

        buttonjatekinditas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(fomenu4Activity.this,JatekActivity.class);
                startActivity(i);
/*
                 SnakeEngine s = new SnakeEngine( fomenu4Activity.this,new Point(11,11));

                frameLayout.addView(s);
                frameLayout.invalidate();

*/

                finish();
            }
        });

        buttonTopPont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scoreboard = new Intent(fomenu4Activity.this, PontTabla.class);
                startActivity(scoreboard);
                finish();
            }
        });


        imageViewmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   if (!fomenuzene.isPlaying())
                   {
                       fomenuzene.start();
                       imageViewmute.setImageResource(R.drawable.mutegomb);
                   }
                   else
                   {
                       fomenuzene.pause();
                       imageViewmute.setImageResource(R.drawable.unmute);
                   }


            }

        });



    }



    public void init()
    {
        buttonjatekinditas = findViewById(R.id.idbttnJatekInditas);
        buttonTopPont = findViewById(R.id.idbttnTopPont);
        buttonKilepes = findViewById(R.id.idbttnKilepes);
        buttonKijeletnkezes = findViewById(R.id.idbttnKilelentkezes);
        imageViewmute = findViewById(R.id.idmuteimageviev);

    }

    @Override
    protected void onPause() {
        super.onPause();
        fomenuzene.release();
        finish();
    }

}
