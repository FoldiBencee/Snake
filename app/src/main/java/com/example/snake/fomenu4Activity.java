package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class fomenu4Activity extends AppCompatActivity {
    private Button buttonjatekinditas,buttonBolt,buttonKijeletnkezes,buttonKilepes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomenu4);
        init();




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
                finish();
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



    }


    public void init()
    {
        buttonjatekinditas = findViewById(R.id.idbttnJatekInditas);
        buttonBolt = findViewById(R.id.idbttnBolt);
        buttonKilepes = findViewById(R.id.idbttnKilepes);
        buttonKijeletnkezes = findViewById(R.id.idbttnKilelentkezes);


    }






}
