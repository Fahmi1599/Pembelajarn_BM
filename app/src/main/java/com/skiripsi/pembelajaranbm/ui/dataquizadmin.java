package com.skiripsi.pembelajaranbm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
import com.skiripsi.pembelajaranbm.data.User;

import java.util.ArrayList;

public class dataquizadmin extends AppCompatActivity {

    RecyclerView rc_listUser;
    ImageView back;
    ProgressBar progressBar;
    ArrayList<User> userList;
    DatabaseReference reference2,reference3;
    FirebaseUser user;
    private FirebaseAuth mAuth;
    userQuizAdapter userQuizAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_admin);
        rc_listUser = findViewById(R.id.rv_show_user);
        back = findViewById(R.id.back);
        progressBar = findViewById(R.id.loading);

        reference2 = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("User");

        userList = new ArrayList<>();
        rc_listUser.setHasFixedSize(true);
        rc_listUser.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.VISIBLE);

        userQuizAdapter = new userQuizAdapter(this,userList);
        rc_listUser.setAdapter(userQuizAdapter);

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    progressBar.setVisibility(View.GONE);
                    rc_listUser.setVisibility(View.VISIBLE);
                    User user = dataSnapshot1.getValue(User.class);
                    userList.add(user);
                }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
