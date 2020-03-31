package com.example.snake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PontTabla extends AppCompatActivity {
    private TextView idpontlista,idpontlista2;
    private Button bttnvissza;
    MediaPlayer fomenuzene;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private String userid = mAuth.getCurrentUser().getUid();

    private DatabaseReference  myRef;
    private int aktualispont;
    private  String nev;
    ImageView imageViewmute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponttabla);
        init();
        fomenuzene = MediaPlayer.create(PontTabla.this, R.raw.fomenuzene);
        fomenuzene.start();

        bttnvissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PontTabla.this, fomenu4Activity.class);
                startActivity(i);
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



        myRef=database.getReference().child("felhasznalok").child(userid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                felhasznalok ember = dataSnapshot.getValue(felhasznalok.class);
                // int p = (int)(long)dataSnapshot.getValue();
                aktualispont = ember.getPont();
                nev = ember.getFelhasznalonev();
                idpontlista.setText(nev + ": " + String.valueOf(aktualispont));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//minden jatekos pontja
        myRef=database.getReference().child("felhasznalok");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()
                     ) {
                    felhasznalok ember = child.getValue(felhasznalok.class);
                    // int p = (int)(long)dataSnapshot.getValue();
                    aktualispont = ember.getPont();
                    nev = ember.getFelhasznalonev();
                    idpontlista2.setText(idpontlista2.getText() + nev + ": " + String.valueOf(aktualispont) + "\n");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }





   public void init()
    {
        idpontlista = findViewById(R.id.idpontlista);
        bttnvissza = findViewById(R.id.bttnvissza);
        idpontlista2 = findViewById(R.id.idpontlista2);
        imageViewmute = findViewById(R.id.idmuteimageviev);
    }

    @Override
    protected void onPause() {
        super.onPause();
        fomenuzene.release();
        finish();
    }

}
