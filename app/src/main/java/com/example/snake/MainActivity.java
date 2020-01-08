package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonbelepes,buttonregisztracio;



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
                Intent regisztacios_oldalra = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(regisztacios_oldalra);
                finish();
            }
        });


    }



    public  void init()
    {
        buttonbelepes = findViewById(R.id.idbttnBelepes);
        buttonregisztracio = findViewById(R.id.idbttnRegisztracio);
    }


    //kezdokepernyo
}
