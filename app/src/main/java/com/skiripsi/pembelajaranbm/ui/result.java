package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skiripsi.pembelajaranbm.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class result extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private int score;
    private TextView dataquiz;
    private Button menu;
    private int jumlahSalah;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,reference;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private String nama;
    private String today;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        dataquiz = findViewById(R.id.tvScore);
        menu = findViewById(R.id.buttonMenu);
        firebaseDatabase = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String uid = user.getUid();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            score = extras.getInt(EXTRA_SCORE);
            dataquiz.setText("SKOR BENAR KAMU ADALAH :"+String.valueOf(score));

        }

        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        today = simpleDateFormat.format(currentDate);


        jumlahSalah = 5 - score;

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String push_key = databaseReference.push().getKey().toString();
                reference = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("History Quiz").child(uid).child(push_key);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("skorBenar", String.valueOf(score));
                hashMap.put("skorSalah", String.valueOf(jumlahSalah));
                hashMap.put("tanggal", today);
                reference.setValue(hashMap);
                Intent intent = new Intent(getApplicationContext(),mainmenu.class);
                startActivity(intent);
            }
        });







    }
}
