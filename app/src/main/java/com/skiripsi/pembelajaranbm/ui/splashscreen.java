package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skiripsi.pembelajaranbm.R;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Handler handler = new Handler();
        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(runnable, 3000);
    }
}
