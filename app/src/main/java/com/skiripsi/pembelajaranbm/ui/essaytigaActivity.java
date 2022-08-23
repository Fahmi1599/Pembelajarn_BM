package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skiripsi.pembelajaranbm.R;

import java.util.HashMap;

public class essaytigaActivity extends AppCompatActivity {

    EditText et_jawab;
    TextView text_view_question;
    Button next;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,reference;
    private FirebaseUser user;
    private FirebaseAuth auth;

    private long backPressedTime;
    private String soal;
    private String jawaban;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay_tiga);

        et_jawab = findViewById(R.id.et_jawaban);
        text_view_question = findViewById(R.id.text_view_question);
        next = findViewById(R.id.button_confirm_next);
        firebaseDatabase = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String uid = user.getUid();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_jawab.getText().toString().equals("") || et_jawab.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Tolong Isi Jawaban",Toast.LENGTH_SHORT).show();
                } else {
                    soal = text_view_question.getText().toString();
                    jawaban = et_jawab.getText().toString();

                    final String push_key = databaseReference.push().getKey().toString();
                    reference = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Essay Quiz").child(uid).child(push_key);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("soal", soal);
                    hashMap.put("Jawaban", jawaban);
                    hashMap.put("userID",uid);
                    hashMap.put("pushKey",push_key);
                    reference.setValue(hashMap);
                    Intent intent = new Intent(getApplicationContext(),resultessay.class);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            Intent intent = new Intent(getApplicationContext(),resultessay.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Tekan sekali lagi untuk menyerah", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }


}
