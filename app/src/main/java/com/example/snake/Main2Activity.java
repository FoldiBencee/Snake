package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    private Button buttonBelepek, buttonVissza;
    private EditText editTextfelhasznalonev, editTextjelszo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kezdokepernyore_lepes = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(kezdokepernyore_lepes);
                finish();
            }
        });

    }

    public  void init()
    {
        buttonBelepek = findViewById(R.id.idbttnBelepek);
        editTextfelhasznalonev = findViewById(R.id.idedittxtfelhnev);
        editTextjelszo = findViewById(R.id.idedittxtjelszo);
        buttonVissza = findViewById(R.id.idbttnVissza);

    }

    //belepesiadatokmegadasa

}
