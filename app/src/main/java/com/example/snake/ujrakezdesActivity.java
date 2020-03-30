package com.example.snake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ujrakezdesActivity extends AppCompatActivity {
   Button bttnigen, bttnnem;
   TextView txtviewelertpont;
    private  int aktualispont;

   // FirebaseDatabase database = FirebaseDatabase.getInstance();
   // DatabaseReference myRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String userid = mAuth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujrakezdes);
        init();
        firedb();





        bttnnem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kezdolapra = new Intent(ujrakezdesActivity.this,fomenu4Activity.class);
                startActivity(kezdolapra);

            }
        });


        bttnigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ujjatek = new Intent(ujrakezdesActivity.this,JatekActivity.class);
                startActivity(ujjatek);


            }
        });




    }
    public void firedb()
    {
        mAuth = FirebaseAuth.getInstance();


    }

    public void offlinepontszammentes()
    {


    }



    public void init()
    {
        bttnigen = findViewById(R.id.bttnigen);
        bttnnem = findViewById(R.id.bttnnem);
       // txtviewelertpont = findViewById(R.id.txtviewelertpont);
    }
}
