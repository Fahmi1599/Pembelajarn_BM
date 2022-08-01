package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skiripsi.pembelajaranbm.R;

import java.util.concurrent.TimeUnit;

public class question1 extends AppCompatActivity {

    TextView timer;
    Button next;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    ImageView back;
    CountDownTimer countDownTimer;

    boolean isAnswer = false;
    private int isSelected = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
        timer = findViewById(R.id.timer);
        next = findViewById(R.id.next);
        radioGroup = findViewById(R.id.rg_question1);
        radioButton1 = findViewById(R.id.rb_1);
        radioButton2 = findViewById(R.id.rb_2);
        radioButton3 = findViewById(R.id.rb_3);
        radioButton4 = findViewById(R.id.rb_4);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTimer();
        setAnswer();

    }

    private void setAnswer() {
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAnswer = true;
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAnswer = false;
            }
        });

        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAnswer = false;
            }
        });

        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAnswer = false;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(),"Silahkan pilih jawaban",Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(),question2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Answer1",String.valueOf(isAnswer));
                    countDownTimer.cancel();

                    startActivity(intent);

                }
            }
        });
    }

    private void setTimer() {
        countDownTimer = new CountDownTimer(61000,1000){

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

                if (radioGroup.getCheckedRadioButtonId() == -1){
                    Intent intent = new Intent(getApplicationContext(),question2.class);
                    intent.putExtra("Answer1","false");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(),question2.class);
                    intent.putExtra("Answer1",String.valueOf(isAnswer));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

            }
        }.start();
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        setTimer();
//        setAnswer();
//    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }
}
