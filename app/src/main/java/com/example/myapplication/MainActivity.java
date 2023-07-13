package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.C1.C1Activity;
import com.example.myapplication.C2.C2Activity;

public class MainActivity extends AppCompatActivity {
    private Button btnW1;
    private Button btnW2;
    private Class hm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnW1 = (Button) findViewById(R.id.btn_w1);
        btnW2 = (Button) findViewById(R.id.btn_w2);

        btnW1.setOnClickListener(v -> executeExercise(1));
        btnW2.setOnClickListener(v -> executeExercise(2));
    }

    private void executeExercise(int exerciseNumber) {
        switch (exerciseNumber) {
            case 1:
                hm = C1Activity.class;
                break;

            case 2:
                hm = C2Activity.class;

        }

        startActivity(new Intent(MainActivity.this, hm));
    }
}