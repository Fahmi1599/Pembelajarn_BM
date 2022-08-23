package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skiripsi.pembelajaranbm.R;

import java.util.HashMap;

public class essayActivity extends AppCompatActivity {

    EditText et_jawab;
    TextView text_view_question;
    Button next;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,reference;
    private FirebaseUser user;
    private FirebaseAuth auth;


    private String soal;
    private String jawaban;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay);

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
                soal = text_view_question.getText().toString();
                jawaban = et_jawab.getText().toString();



                final String push_key = databaseReference.push().getKey().toString();
                reference = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Essay Quiz").child(uid).child(push_key);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("soal", soal);
                hashMap.put("Jawaban", jawaban);
                reference.setValue(hashMap);
                Intent intent = new Intent(getApplicationContext(),resultessay.class);
                startActivity(intent);
            }
        });


    }
}
