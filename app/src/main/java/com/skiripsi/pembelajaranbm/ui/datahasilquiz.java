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
import com.skiripsi.pembelajaranbm.data.history;

import java.util.ArrayList;

public class datahasilquiz extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";

    ImageView back;
    RecyclerView rv_list_history;
    DatabaseReference reference2,reference3;
    FirebaseUser user;
    private FirebaseAuth mAuth;
    ArrayList<history> historyList;
    historyadapter historyAdapter;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datanilai_quiz);
        rv_list_history = findViewById(R.id.rv_list_history);
        back = findViewById(R.id.back);


        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();
        String uid = user.getUid();

        rv_list_history.setHasFixedSize(true);
        rv_list_history.setLayoutManager(new LinearLayoutManager(this));
        reference2 = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("History Quiz").child(uid);
        historyList = new ArrayList<>();
        historyAdapter = new historyadapter(this,historyList);
        rv_list_history.setAdapter(historyAdapter);

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren())
                {
                    history history = dataSnapshot1.getValue(history.class);
                    historyList.add(history);

                }

                historyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
