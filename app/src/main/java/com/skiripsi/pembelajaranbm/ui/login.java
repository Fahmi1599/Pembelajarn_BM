package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skiripsi.pembelajaranbm.R;

public class login extends AppCompatActivity {

    EditText email,password;
    Button btn_login;
    TextView toRegister;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activy_login);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.button_login);
        toRegister = findViewById(R.id.toRegister);

        //Instance / Membuat Objek Firebase Authentication
        auth = FirebaseAuth.getInstance();



        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginAccount();

            }
        });


    }

    private void loginAccount() {
        final String getEmail = email.getText().toString().trim();
        final String getPassword = password.getText().toString().trim();

        if (TextUtils.isEmpty(getEmail)){
            email.setError("Email tidak boleh kosong");
        } else if (TextUtils.isEmpty(getPassword)){
            password.setError("Password tidak boleh kosong");
        } else {
            auth.signInWithEmailAndPassword(getEmail,getPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                if (getEmail.equals("admin@gmail.com")&&getPassword.equals("admin123"))
                                {
                                    Intent i = new Intent(getApplicationContext() , adminactivity.class);
                                    startActivity(i);

                                } else {
                                    final FirebaseUser user = task.getResult().getUser();
                                    if (user.isEmailVerified()) {
                                        Intent i = new Intent(getApplicationContext(), mainmenu.class);
                                        startActivity(i);
                                        Toast.makeText(getApplicationContext(), "Berhasil Masuk ", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext() , "Cek Email Verifikasi ",Toast.LENGTH_SHORT).show();
                                    }
                                }

//                                //final FirebaseUser user = task.getResult().getUser();
//                                Intent intent = new Intent(getApplicationContext(),mainmenu.class);
//                                startActivity(intent);
//                                Toast.makeText(login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(login.this, "Login Gagal , Coba Lagi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), "Gagal , Coba Lagi !! ", Toast.LENGTH_SHORT).show();

                        }
                    });
        }


    }

    @Override
    public void onBackPressed() {

    }
}
