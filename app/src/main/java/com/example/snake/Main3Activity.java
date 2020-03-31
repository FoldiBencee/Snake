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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.mindrot.jbcrypt.BCrypt;

public class Main3Activity extends AppCompatActivity {

    private Button buttonRegisztralok,buttonVissza,buttonofflineRegisztralok;
    private EditText editTextfelhasznev, editTextjelszo,editTextjelszoismet, editTextemail;
    private Adatbazissegito adatbseg;
    private felhasznalok felhasznalok;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String email, password;
    private long maxid;


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
                Intent i = new Intent(Main3Activity.this,Main2Activity.class);
                startActivity(i);
                adatrogzitesFirebaseba();


            }
        });
        buttonofflineRegisztralok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main3Activity.this,Main2Activity.class);
                adatRogzites();
            }
        });

    }

    public void init()
    {
        buttonRegisztralok = findViewById(R.id.idbttnRegisztralok);
        editTextfelhasznev = findViewById(R.id.idedittxtfelhnev);
        editTextjelszo = findViewById(R.id.idedittxtjelszo);
        editTextemail = findViewById(R.id.idedittxtemail);
        buttonVissza = findViewById(R.id.idbttnVissza);
        editTextjelszoismet = findViewById(R.id.idedittxtjelszoismet);
        buttonofflineRegisztralok = findViewById(R.id.idbttnofflineRegisztralok);
        adatbseg = new Adatbazissegito(this);
        felhasznalok = new felhasznalok();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("felhasznalok");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public  void adatRogzites()
    {
        String jelszo = editTextjelszo.getText().toString();
        String felhasznalonev = editTextfelhasznev.getText().toString();
        String email = editTextemail.getText().toString();
        String jelszoismet = editTextjelszoismet.getText().toString();

        if (jelszo.isEmpty()|| felhasznalonev.isEmpty()||email.isEmpty()||jelszoismet.isEmpty())
        {
            Toast.makeText(this, "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
        }
        else
        {

            if (!adatbseg.checkjelszojelszoismet(jelszo,jelszoismet))
            {
                Toast.makeText(this, "A jelszó félre lett gépelve", Toast.LENGTH_SHORT).show();
                return;
            }

            if(adatbseg.checkFelhasznalonevEsEmail(felhasznalonev,email))
            {
                Toast.makeText(this, "Felhasználónév vagy az email foglalt", Toast.LENGTH_SHORT).show();
                return;
            }


            String hash = BCrypt.hashpw(jelszo, BCrypt.gensalt());

            Boolean eredmeny = adatbseg.adatRogzites(felhasznalonev,jelszo,jelszoismet,email);
            if (eredmeny && adatbseg.checkjelszojelszoismet(jelszo,jelszoismet)&& adatbseg.checkFelhasznalonevEsEmail(felhasznalonev,email)) {
                Toast.makeText(this, "Adatrogzites sikeres", Toast.LENGTH_SHORT).show();
                Intent kezdolap = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(kezdolap);
                finish();
            }

            else
                Toast.makeText(this, "Adatrogzites sikertelen", Toast.LENGTH_SHORT).show();



        }



    }

    public  void adatrogzitesFirebaseba()
    {
        if(editTextfelhasznev.getText().toString().isEmpty() ||
                editTextjelszo.getText().toString().isEmpty() ||
                editTextjelszoismet.getText().toString().isEmpty() ||
                editTextemail.getText().toString().isEmpty()
        )
        {
            Toast.makeText(Main3Activity.this, "Minden mezot ki kell tolteni", Toast.LENGTH_SHORT).show();
        }
        else
        {
            felhasznalok.setFelhasznalonev(editTextfelhasznev.getText().toString());


            felhasznalok.setPont(0);

            mAuth.createUserWithEmailAndPassword(editTextemail.getText().toString(),editTextjelszo.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                        mAuth.signInWithEmailAndPassword(editTextemail.getText().toString(),editTextjelszo.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    databaseReference.child(firebaseUser.getUid()).setValue(felhasznalok);
                                    if (!firebaseUser.isEmailVerified()) {
                                        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(Main3Activity.this, "Sikeres", Toast.LENGTH_SHORT).show();
                                                Intent sikeres = new Intent(Main3Activity.this, Main2Activity.class);
                                                startActivity(sikeres);
                                                finish();
                                                mAuth.signOut();

                                            }

                                        });
                                    } else {
                                        Toast.makeText(Main3Activity.this, "Sikertelen", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });






                }
            });


        }
    }
    }


    //regisztraciosfelulet

