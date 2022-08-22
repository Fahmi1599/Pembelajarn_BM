package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.skiripsi.pembelajaranbm.R;

public class list_materi extends AppCompatActivity {

    ImageView back;
    ConstraintLayout materi1,materi2,materi3,materi4,materi5,materi6,materi7,materi8,materi9,materi10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_materi);
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        materi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"File Kososng",Toast.LENGTH_SHORT).show();
            }
        });

        materi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),opsi_pilihan_materi.class);
                intent.putExtra("linkPDF","https://firebasestorage.googleapis.com/v0/b/media-pembelajaran-1b8e3.appspot.com/o/PDF%2Fmateri1_Introduction%20To%20LTE.pdf?alt=media&token=f5e2d69b-adf0-4110-ace2-2bf6f14c9bc2");
                intent.putExtra("videoLink","https://www.youtube.com/watch?v=g7UUnr5RBwA");
                startActivity(intent);

            }
        });

        materi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),opsi_pilihan_materi.class);
                intent.putExtra("linkPDF","https://firebasestorage.googleapis.com/v0/b/media-pembelajaran-1b8e3.appspot.com/o/PDF%2Fmateri2_LTE%20SAE.pdf?alt=media&token=a80e35ea-adb7-49ba-8373-90fb0f834837");
                intent.putExtra("videoLink","https://www.youtube.com/watch?v=mn6sKscLHiw");
                startActivity(intent);
            }
        });

        materi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),opsi_pilihan_materi.class);
                intent.putExtra("linkPDF","https://firebasestorage.googleapis.com/v0/b/media-pembelajaran-1b8e3.appspot.com/o/PDF%2Fmateri3_VOLTE.pdf?alt=media&token=a2403fb6-b8ce-4fd3-9168-0b308aff3edb");
                intent.putExtra("videoLink","https://www.youtube.com/watch?v=FZ8hpprRuGc");
                startActivity(intent);
            }
        });

        materi5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),opsi_pilihan_materi.class);
                intent.putExtra("linkPDF","https://firebasestorage.googleapis.com/v0/b/media-pembelajaran-1b8e3.appspot.com/o/PDF%2Fmateri4_pegenalan%20Radio%204G%20LTE.pdf?alt=media&token=312c34a0-80e9-4aca-8da3-4cdbfdd317a8");
                intent.putExtra("videoLink","https://www.youtube.com/watch?v=5lKg_VMRvC8");
                startActivity(intent);
            }
        });

        materi6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),opsi_pilihan_materi.class);
                intent.putExtra("linkPDF","https://firebasestorage.googleapis.com/v0/b/media-pembelajaran-1b8e3.appspot.com/o/PDF%2Fmateri6_Technologi_Backhaul_LTE.pdf?alt=media&token=00bec832-d114-4b3b-b455-d235fd95d5e4");
                intent.putExtra("videoLink","");
                startActivity(intent);

            }
        });

        materi7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),opsi_pilihan_materi.class);
                intent.putExtra("linkPDF","https://firebasestorage.googleapis.com/v0/b/media-pembelajaran-1b8e3.appspot.com/o/PDF%2Fmateri7_Design%20LTE.pdf?alt=media&token=b54913df-2bbc-427b-bd75-835352c49edf");
                intent.putExtra("videoLink","https://www.youtube.com/watch?v=7TqAU8BXllo");
                startActivity(intent);
            }
        });

        materi8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),opsi_pilihan_materi.class);
                intent.putExtra("linkPDF","https://firebasestorage.googleapis.com/v0/b/media-pembelajaran-1b8e3.appspot.com/o/PDF%2Fmateri7_Design%20LTE.pdf?alt=media&token=b54913df-2bbc-427b-bd75-835352c49edf");
                intent.putExtra("videoLink","https://www.youtube.com/watch?v=n41AYYvDEXo");
                startActivity(intent);

            }
        });

        materi9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),opsi_pilihan_materi.class);
                intent.putExtra("linkPDF","https://firebasestorage.googleapis.com/v0/b/media-pembelajaran-1b8e3.appspot.com/o/PDF%2Fmateri8_RNO.pdf?alt=media&token=6aeca764-9a4d-44b5-91af-1b0b46308bea");
                intent.putExtra("videoLink","https://www.youtube.com/watch?v=Q3X9Xc7qSVI");
                startActivity(intent);

            }
        });

        materi10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),opsi_pilihan_materi.class);
                intent.putExtra("linkPDF","https://firebasestorage.googleapis.com/v0/b/media-pembelajaran-1b8e3.appspot.com/o/PDF%2Fmateri9_RF%20Planning.pdf?alt=media&token=82abf85b-f898-48f5-a599-7e244f6990fd");
                intent.putExtra("videoLink","https://www.youtube.com/watch?v=xEyA-a5Z3F4");
                startActivity(intent);
            }
        });
    }
}
