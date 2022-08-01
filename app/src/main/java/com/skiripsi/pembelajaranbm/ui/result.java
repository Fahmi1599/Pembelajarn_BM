package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skiripsi.pembelajaranbm.R;

public class result extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private int score;
    private TextView dataquiz;
    private Button menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        dataquiz = findViewById(R.id.tvScore);
        menu = findViewById(R.id.buttonMenu);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            score = extras.getInt(EXTRA_SCORE);
            dataquiz.setText("SKOR BENAR KAMU ADALAH :"+String.valueOf(score));

        }

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mainmenu.class);
                startActivity(intent);
            }
        });





    }
}
