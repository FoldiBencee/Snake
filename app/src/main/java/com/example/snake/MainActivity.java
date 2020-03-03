package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonbelepes,buttonregisztracio;
    private TextView vendeg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonbelepes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent belepes_oldalra = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(belepes_oldalra);
                finish();


            }
        });

        buttonregisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regisztracios_oldalra = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(regisztracios_oldalra);
                finish();
            }

        });

        vendeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Folytatás mint vendég");
                alertDialog.setMessage("Ha biztosan vendégként szeretne játszani, akkor a pontszámaid nem lesznek elmentve, és nem lesz megjelenítve a globális ranglistán");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent menube = new Intent(MainActivity.this, fomenu4Activity.class);
                        startActivity(menube);
                        finish();
                    }
                });

                alertDialog.setNegativeButton("vissza", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.create().show();

            }
        });



    }



    public  void init()
    {
        buttonbelepes = findViewById(R.id.idbttnBelepes);
        buttonregisztracio = findViewById(R.id.idbttnRegisztracio);
        vendeg = findViewById(R.id.txtvendeg);
    }


    //kezdokepernyo
}
