package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.skiripsi.pembelajaranbm.R;
import com.skiripsi.pembelajaranbm.ui.quiz1.QuizActivity1;
import com.skiripsi.pembelajaranbm.ui.quiz2.QuizActivity2;
import com.skiripsi.pembelajaranbm.ui.quiz4.QuizActivity4;

public class ActivityListQuiz extends AppCompatActivity {

    ImageView back;
    ConstraintLayout materi1,materi2,materi3,materi4,materi5,materi6,materi7,materi8,materi9,materi10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quiz);
        back = findViewById(R.id.back);
        materi1 = findViewById(R.id.materi1);
        materi2 = findViewById(R.id.materi2);
        materi3 = findViewById(R.id.materi3);
        materi4 = findViewById(R.id.materi4);
        materi5 = findViewById(R.id.materi5);
        materi6 = findViewById(R.id.materi6);
        materi7 = findViewById(R.id.materi7);
        materi8 = findViewById(R.id.materi8);
        materi9 = findViewById(R.id.materi9);
        materi10 = findViewById(R.id.materi10);

        materi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity1.class);
                startActivity(intent);
            }
        });

        materi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity2.class);
                startActivity(intent);
            }
        });

        materi5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity4.class);
                startActivity(intent);
            }
        });



    }
}
