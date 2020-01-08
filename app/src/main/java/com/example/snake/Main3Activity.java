package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private Button buttonRegisztralok,buttonVissza;
    private EditText editTextfelhasznev, editTextjelszo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kezdolapra = new Intent(Main3Activity.this,MainActivity.class);
                startActivity(kezdolapra);
                finish();
            }
        });

        buttonRegisztralok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adatRogzites();

                finish();
            }
        });

    }

    public void init()
    {
        buttonRegisztralok = findViewById(R.id.idbttnRegisztralok);
        editTextfelhasznev = findViewById(R.id.idedittxtfelhnev);
        editTextjelszo = findViewById(R.id.idedittxtjelszo);
        buttonVissza = findViewById(R.id.idbttnVissza);
    }


   /* public  void adatRogzites()
    {
        String jelszo = editTextjelszo.getText().toString();
        String felhasznalonev = editTextfelhasznev.getText().toString();

        Boolean eredmeny = adatbazissegito.adatRogzites(felhasznalonev,felhasznalonev);
        if (eredmeny)
            Toast.makeText(this, "Adatrogzites sikeres", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Adatrogzites sikertelen", Toast.LENGTH_SHORT).show();

    }
*/
    //regisztraciosfelulet
}
