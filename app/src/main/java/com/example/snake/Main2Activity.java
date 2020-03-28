package com.example.snake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.mindrot.jbcrypt.BCrypt;

public class Main2Activity extends AppCompatActivity {
    private Button buttonBelepek, buttonVissza, buttonofflinebelepek;
    private EditText editTextemail, editTextjelszo;
    private Adatbazissegito dbh;
    private FirebaseAuth mAuth;


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
              /*  String jelszo = editTextjelszo.getText().toString();
                String email = editTextemail.getText().toString();
                boolean asd = dbh.checkemailpassword(email,jelszo);//firebasenel kerdojel nem aradhat igy megoldva
                //boolean jojelszo = BCrypt.checkpw(jelszo, asd);
                if (asd == true)
                {
                    Toast.makeText(getApplicationContext(),"Sikeres belépés", Toast.LENGTH_SHORT).show();
                    Intent fomenube_lepes = new Intent(Main2Activity.this,fomenu4Activity.class);
                    startActivity(fomenube_lepes);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sikertelen belépés", Toast.LENGTH_SHORT).show();
                    //= db.felhasznalonevpassword(felhasznalonev,jelszo);
                }

                sqlite belepes nem kell //Jatek vendegkent funkcio hozzaadva
                */
                firebasebelepes();
            }
        });
        buttonofflinebelepek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String jelszo = editTextjelszo.getText().toString();
                String email = editTextemail.getText().toString();
                boolean asd = dbh.checkemailpassword(email,jelszo);
                //boolean jojelszo = BCrypt.checkpw(jelszo, asd);
                if (asd == true)
                {
                    Toast.makeText(getApplicationContext(),"Sikeres belépés", Toast.LENGTH_SHORT).show();
                    Intent fomenube_lepes = new Intent(Main2Activity.this,fomenu4Activity.class);
                    startActivity(fomenube_lepes);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sikertelen belépés", Toast.LENGTH_SHORT).show();
                    //= db.felhasznalonevpassword(felhasznalonev,jelszo);
                }
            }
        });

    }

    public  void init()
    {
        buttonBelepek = findViewById(R.id.idbttnBelepek);
        editTextemail = findViewById(R.id.idedittxtemail);
        editTextjelszo = findViewById(R.id.idedittxtjelszo);
        buttonVissza = findViewById(R.id.idbttnVissza);
        buttonofflinebelepek =findViewById(R.id.idbttnofflineBelepek);
        dbh = new Adatbazissegito(Main2Activity.this);
        mAuth = FirebaseAuth.getInstance();

    }

    public void firebasebelepes()
    {
        if (editTextemail.getText().toString().isEmpty() || editTextjelszo.getText().toString().isEmpty())
        {
            Toast.makeText(Main2Activity.this, "minden mezot ki kell tolteni", Toast.LENGTH_SHORT).show();
        }
        else
        {


            mAuth.signInWithEmailAndPassword(editTextemail.getText().toString(),editTextjelszo.getText().toString())
                    .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (!user.isEmailVerified())
                                {
                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Main2Activity.this,"erositsd meg az emailed", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(Main2Activity.this,"Hello" + user.getEmail(), Toast.LENGTH_SHORT).show();
                                    Intent belepes = new Intent(Main2Activity.this,fomenu4Activity.class);
                                    startActivity(belepes);
                                    finish();
                                }
                            }
                            else
                            {
                                Toast.makeText(Main2Activity.this,"hibas email vagy jelszo!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }


    //belepesiadatokmegadasa

}
