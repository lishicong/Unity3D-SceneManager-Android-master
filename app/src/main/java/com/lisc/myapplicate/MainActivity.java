package com.lisc.myapplicate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(v -> {
            Intent intent = new Intent(this, Unity_01_Activity.class);
            startActivity(intent);
        });
        PlayerHolder.getInstance().getPlayer(this);
    }
}