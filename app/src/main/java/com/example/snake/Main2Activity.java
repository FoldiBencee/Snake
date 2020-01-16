package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private Button buttonBelepek, buttonVissza;
    private EditText editTextfelhasznalonev, editTextjelszo;
    private Adatbazissegito dbh;


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


        buttonBelepek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jelszo = editTextjelszo.getText().toString();
                String felhasznalonev = editTextfelhasznalonev.getText().toString();
                boolean asd = dbh.checkfelhasznalonevpassword(felhasznalonev,jelszo);
                if (asd == true)
                {
                    Toast.makeText(getApplicationContext(),"Sikeres belépés", Toast.LENGTH_SHORT).show();
                    Intent fomenube_lepes = new Intent(Main2Activity.this,fomenu4Activity.class);
                    startActivity(fomenube_lepes);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Sikertelen belépés", Toast.LENGTH_SHORT).show();
            //= db.felhasznalonevpassword(felhasznalonev,jelszo);

            }
        });

    }

    public  void init()
    {
        buttonBelepek = findViewById(R.id.idbttnBelepek);
        editTextfelhasznalonev = findViewById(R.id.idedittxtfelhnev);
        editTextjelszo = findViewById(R.id.idedittxtjelszo);
        buttonVissza = findViewById(R.id.idbttnVissza);
        dbh = new Adatbazissegito(Main2Activity.this);

    }

    //belepesiadatokmegadasa

}
