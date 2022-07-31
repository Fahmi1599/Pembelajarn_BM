package com.skiripsi.pembelajaranbm.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skiripsi.pembelajaranbm.R;

import java.util.concurrent.TimeUnit;

public class question9 extends AppCompatActivity {

    TextView timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question9);

        timer = findViewById(R.id.timer);

        setTimer();
    }

    private void setTimer() {
        new CountDownTimer(121000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("Waktu : "+String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                timer.setTextColor(Color.BLACK);
                timer.setEnabled(false);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}
