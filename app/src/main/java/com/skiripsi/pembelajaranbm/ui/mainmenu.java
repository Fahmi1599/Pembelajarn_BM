package com.skiripsi.pembelajaranbm.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skiripsi.pembelajaranbm.R;
import com.skiripsi.pembelajaranbm.data.User;

public class mainmenu extends AppCompatActivity {

    TextView mulaiBelajar,mulaiQuiz,namaUser,dataHasilQuiz;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private FirebaseUser user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        mulaiBelajar = findViewById(R.id.mulai_belajar);
        mulaiQuiz = findViewById(R.id.mulai_quiz);
        namaUser = findViewById(R.id.nameUser);
        dataHasilQuiz = findViewById(R.id.history_quiz);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String uid = user.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("User");

        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                namaUser.setText("Hi "+user.getFullname());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mulaiQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                showAllertDialog();


            }
        });

        mulaiBelajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), list_materi.class);
                startActivity(intent);

            }
        });

        dataHasilQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void showAllertDialog() {
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.custom_dialog,
                        null);
        builder.setView(customLayout);

        // add a button
        builder
                .setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int which)
                            {

                                Intent intent = new Intent(getApplicationContext(),question1.class);
                                startActivity(intent);
                                // send data from the
                                // AlertDialog to the Activity
                            }
                        });

        // create and show
        // the alert dialog
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }


    @Override
    public void onBackPressed() {
    }
}
