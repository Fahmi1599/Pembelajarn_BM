package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.skiripsi.pembelajaranbm.R;

public class opsi_pilihan_materi extends AppCompatActivity {

    CardView cv_tontonVideo;
    CardView cv_lihatMater;
    private String pdfLink;
    private String videoLink;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_pilihan_materi);


        pdfLink = getIntent().getStringExtra("linkPDF");
        videoLink = getIntent().getStringExtra("videoLink");
        cv_lihatMater = findViewById(R.id.materi);
        back = findViewById(R.id.back);
        cv_tontonVideo = findViewById(R.id.cv_tonton_video);

        if (videoLink.equals("")){
            cv_tontonVideo.setVisibility(View.GONE);
        } else {
            cv_tontonVideo.setVisibility(View.VISIBLE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cv_lihatMater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),detail_materi.class);
                intent.putExtra("linkPDF",pdfLink);
                startActivity(intent);
            }
        });

        cv_tontonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),video.class);
                intent.putExtra("videoLink",videoLink);
                startActivity(intent);
            }
        });



    }
}
