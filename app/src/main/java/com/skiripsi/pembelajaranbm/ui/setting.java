package com.skiripsi.pembelajaranbm.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.skiripsi.pembelajaranbm.R;
import com.skiripsi.pembelajaranbm.data.User;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class setting extends AppCompatActivity {

    CircleImageView profPic;
    TextView email,nama;
    ImageView back;

    private StorageReference mStorageRef;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference2;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private String UserId;
    private String Profilphoto;
    private Bitmap bitmap;
    Uri filePath = null;

    private static final int REQUEST_IMAGE_CAPTURE = 1002;
    private static final int REQUEST_IMAGE_SELECTION = 2;
    int PICK_IMAGE = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        profPic = findViewById(R.id.profPic);
        email = findViewById(R.id.email);
        nama = findViewById(R.id.nama);
        back = findViewById(R.id.back);

        storage = FirebaseStorage.getInstance("gs://media-pembelajaran-1b8e3.appspot.com/");
        mStorageRef = storage.getReference();
        firebaseDatabase = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();
        databaseReference2 = firebaseDatabase.getReference("User");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        UserId = user.getUid();

        databaseReference2.child(UserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);
                nama.setText(user.getFullname());
                email.setText(user.getEmail());
                //penambahan
                nama.setEnabled(false);
                email.setEnabled(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        profPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProfile();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        databaseReference2.child(UserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);
                Profilphoto= user.getPhotoProfile();
                if (Profilphoto.equals("")){
                    Picasso.with(getApplicationContext()).load(R.drawable.ic_baseline_account_circle_24).into(profPic);
                } else {
                    Picasso.with(getApplicationContext()).load(Profilphoto).placeholder(R.drawable.ic_baseline_account_circle_24).into(profPic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void chooseProfile() {
        String[] saveOptions = {"Take a Photo", "Select From Gallery" };
        AlertDialog.Builder builder = new AlertDialog.Builder(setting.this);
        builder.setTitle("Select a Photo");
        builder.setItems(saveOptions, new DialogInterface.OnClickListener() {
                    @SuppressLint("QueryPermissionsNeeded")
                    @Override
                    public void onClick(DialogInterface dialog, int selected) {

                        if (selected == 0) {
                            Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (takePhotoIntent.resolveActivity(getPackageManager()) != null) ;
                            try {
                                startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
                            } catch (ActivityNotFoundException e) {
                                // display error state to the user
                            }

                        }
                        if (selected == 1) {
                            Intent intent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            String[] mimeTypes = {"image/jpeg", "image/png", "image/jpg"};
                            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
                        }
                    }
                }
        ).setCancelable(false);
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case 1000:

                    filePath = data.getData();
                    profPic.setImageURI(filePath);
                    uploadImage();

                    break;
            }
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode==Activity.RESULT_OK) {

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                profPic.setImageBitmap(imageBitmap);
//                filePath = data.getData();
                profPic.setImageURI(filePath);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), imageBitmap, "Title", null);
                filePath=Uri.parse(path);
                uploadImage();
                //Toast.makeText(setting.this, String.valueOf(filePath), Toast.LENGTH_SHORT).show();

                // bytearrayoutputstream = null;
                //imgPath = getResizedBitmap(uploadimage);
            }
        } else {
            Toast.makeText(this, "Image/File Not Selected!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void uploadImage() {

        if (filePath  != null){


            final StorageReference FilePath = mStorageRef.child("users_image").child(UserId + "jpg");
            FilePath.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(setting.this, "Profil Pic Uploaded", Toast.LENGTH_SHORT).show();

                            FilePath.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    final String ProfilPic_url = task.getResult().toString();
                                    final String push_key = databaseReference.push().getKey().toString();
                                    databaseReference.getRoot().child("ProfileUser").child(push_key).setValue(ProfilPic_url);
                                    databaseReference = firebaseDatabase.getReference().child("User").child(UserId);
//                                    Glide.with(profil.this)
//                                            .load(ProfilPic_url);
                                    databaseReference.child("photoProfile").setValue(ProfilPic_url);
//                                    Intent finishprof = new Intent (SetupActivity.this , Login.class);
//                                    finish();
//                                    startActivity(finishprof);
                                }
                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(setting.this, "File Upload Fail!" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });





        }
    }
}
