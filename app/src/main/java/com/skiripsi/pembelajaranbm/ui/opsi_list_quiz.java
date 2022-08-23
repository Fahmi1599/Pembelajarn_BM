package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.skiripsi.pembelajaranbm.R;
import com.skiripsi.pembelajaranbm.ui.quiz9.QuizActivityBs9;

public class opsi_list_quiz extends AppCompatActivity {

    CardView pg;
    CardView tf;
    ImageView back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opsi_list_quiz);

        pg = findViewById(R.id.quiz_pg_cv);
        tf = findViewById(R.id.quiz_bs_cv);
        back = findViewById(R.id.back);


        pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityListQuiz.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizActivityBs9.class);
                startActivity(intent);
            }
        });


    }
}
