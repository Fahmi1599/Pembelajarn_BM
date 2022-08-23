package com.skiripsi.pembelajaranbm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skiripsi.pembelajaranbm.R;

import java.util.ArrayList;

public class detailEssayQuizAdmin extends AppCompatActivity {


    ImageView back;
    RecyclerView rv_list_history;
    DatabaseReference reference2,reference3;
    FirebaseUser user;
    private FirebaseAuth mAuth;
    ArrayList<Essay> essays;
    skorEssayQuizAdapter skorEssayQuizAdapter;
    private String uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_essay_quizadmin);

        rv_list_history = findViewById(R.id.rv_show_user);
        back = findViewById(R.id.back);

        uid = getIntent().getStringExtra("userID");


        rv_list_history.setHasFixedSize(true);
        rv_list_history.setLayoutManager(new LinearLayoutManager(this));
        reference2 = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Essay Quiz").child(uid);
        essays = new ArrayList<>();
        skorEssayQuizAdapter = new skorEssayQuizAdapter(this,essays);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                essays.clear();
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren())
                {
                    Essay history = dataSnapshot1.getValue(Essay.class);
                    essays.add(history);

                }
                rv_list_history.setAdapter(skorEssayQuizAdapter);
                skorEssayQuizAdapter.notifyDataSetChanged();
                //historyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}
