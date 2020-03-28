package com.example.snake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    private TextView idpontlista;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private String userid = mAuth.getCurrentUser().getUid();

    DatabaseReference  myRef=database.getReference().child("felhasznalok").child(userid).child("pont");
    private int aktualispont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponttabla);
        init();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int p = (int)(long)dataSnapshot.getValue();
                aktualispont = p;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        idpontlista.setText(String.valueOf(aktualispont));
    }




   public void init()
    {
        idpontlista = findViewById(R.id.idpontlista);
    }
}
