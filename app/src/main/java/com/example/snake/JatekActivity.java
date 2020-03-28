package com.example.snake;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.Display;
import android.graphics.Point;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JatekActivity extends AppCompatActivity {
    SnakeEngine snakeEngine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jatekfelulet);
        final FrameLayout frameLayout = findViewById(R.id.frameLayout);
        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);


        snakeEngine = new SnakeEngine(JatekActivity.this, size);

        frameLayout.addView(snakeEngine);
        frameLayout.invalidate();



    }


    @Override
    protected void onResume() {

        super.onResume();
        snakeEngine.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        snakeEngine.pause();

    }
}
