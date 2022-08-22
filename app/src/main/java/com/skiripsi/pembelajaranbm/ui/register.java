package com.skiripsi.pembelajaranbm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skiripsi.pembelajaranbm.R;

import java.util.HashMap;

public class register extends AppCompatActivity {

    EditText etEmail,etPassword,etNamaDepan,etNamaBelakang;
    Button btn_regist;
    private FirebaseAuth auth;
    public static FirebaseDatabase database;
    public static DatabaseReference reference,reference1,reference2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.et_email_regist);
        etPassword = findViewById(R.id.et_password_regist);
        etNamaDepan = findViewById(R.id.et_namaDepan);
        etNamaBelakang = findViewById(R.id.et_namaBelakang);
        btn_regist = findViewById(R.id.buttonRegister);

        auth = FirebaseAuth.getInstance();

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekDataUser();
            }
        });

    }

    private void cekDataUser() {
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString();
        final String namaDepan = etNamaDepan.getText().toString();
        final String namaBelakang = etNamaBelakang.getText().toString();

        if (TextUtils.isEmpty(email)){
            etEmail.setError("email kosong");
        } else if (TextUtils.isEmpty(password)){
            etPassword.setError("password kosong");
            if (password.length() <6){
                etPassword.setError("Minimal password 6 huruf");
            }
        } else if (TextUtils.isEmpty(namaDepan)){
            etNamaDepan.setError("Nama Depan Kosong");
        } else if (TextUtils.isEmpty(namaBelakang)){
            etNamaBelakang.setError("Nama Belakang Kosong");
        } else {
            createUserAccount(email,password,namaDepan,namaBelakang);
        }
    }

    private void createUserAccount(String email, String password, String namaDepan, String namaBelakang) {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser SSuser = auth.getCurrentUser();
                            assert SSuser != null;
                            String UserId = SSuser.getUid();
                            reference = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User").child(UserId);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("UserId", UserId);
                            hashMap.put("Fullname", namaDepan+" "+namaBelakang);
                            hashMap.put("namaDepan",namaDepan);
                            hashMap.put("namaBelakang",namaBelakang);
                            hashMap.put("Email", email);
                            hashMap.put("Password", password);
                            hashMap.put("photoProfile", "");
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (SSuser.isEmailVerified()) {

                                        Toast.makeText(getApplicationContext(), "Email Sudah Tervirifikasi , Silahkan Login", Toast.LENGTH_SHORT).show();

                                        // UpdateUI();
                                    } else {
                                        //Mengecek status keberhasilan saat medaftarkan email dan sandi baru
                                        SSuser.sendEmailVerification();
                                        Toast.makeText(getApplicationContext(), "Daftar Akun Berhasi, Cek Pesan Masuk dan Spam Email", Toast.LENGTH_SHORT).show();

//                                        reference1 = FirebaseDatabase.getInstance().getReference("User").child(UserId);
//                                        reference2 = FirebaseDatabase.getInstance().getReference("User").child(UserId);
//
//                                        reference1.child("Status").setValue("Inactive");
//                                        reference2.child("Valid_Date").setValue("Pending");
//

                                        startActivity(new Intent(getApplicationContext(), login.class));
                                        finish();
                                    }

                                }
                            });

//                            Toast.makeText(register.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
//
//                            Intent intent = new Intent(getApplicationContext(),login.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
