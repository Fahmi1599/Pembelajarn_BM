package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.skiripsi.pembelajaranbm.R;

public class adminactivity extends AppCompatActivity {

    CardView cv_userList;
    CardView cv_dataQuiz;
    TextView tv_logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        cv_userList = findViewById(R.id.cv_listUser);
        tv_logout = findViewById(R.id.adminLogout);
        cv_dataQuiz = findViewById(R.id.datahasilquizUser);

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });

        cv_userList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityListUser.class);
                startActivity(intent);
            }
        });

        cv_dataQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), dataquizadmin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        
    }
}
