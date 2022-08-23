package com.skiripsi.pembelajaranbm.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skiripsi.pembelajaranbm.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class skorEssayQuizAdapter extends RecyclerView.Adapter<skorEssayQuizAdapter.MyViewHolder>  {

    Context context;

    ArrayList<Essay> essays;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,reference,reference2;

    public skorEssayQuizAdapter(Context context, List<Essay> essays) {
        this.context = context;
        this.essays = (ArrayList<Essay>) essays;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_custom_skor_essay,parent,false);
        return  new skorEssayQuizAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //history history = list.get(position);
        Essay essay = essays.get(position);
        holder.soal.setText(essay.getSoal());
        holder.Jawaban.setText(essay.getJawaban());

        int maxScore = 100;

        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int skor = Integer.parseInt(holder.skor.getText().toString());

                if (skor >100){
                    Toast.makeText(context.getApplicationContext(), "Nilai Maksimum adalah 100",Toast.LENGTH_SHORT).show();
                } else {
                    firebaseDatabase = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/");
                    databaseReference = firebaseDatabase.getReference();
                    final String push_key = databaseReference.push().getKey().toString();
                    reference2 = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Essay Quiz").child(essay.getUserID()).child(essay.getPushKey());
                    reference = FirebaseDatabase.getInstance("https://media-pembelajaran-1b8e3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Skor Essay Quiz").child(essay.getUserID()).child(push_key);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("soal", essay.getSoal());
                    hashMap.put("Jawaban", essay.getJawaban());
                    hashMap.put("userID",essay.getUserID());
                    hashMap.put("skor",String.valueOf(skor));
                    reference.setValue(hashMap);
                    reference2.removeValue();
                }

//                Intent intent = new Intent(getApplicationContext(),essayduaActivity.class);
//                context.startActivity();
            }
        });


    }


    @Override
    public int getItemCount() {
        return essays.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView soal,Jawaban;
        EditText skor;
        Button submit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            soal = itemView.findViewById(R.id.soal);
            Jawaban = itemView.findViewById(R.id.jawaban);
            skor = itemView.findViewById(R.id.et_skor);
            submit = itemView.findViewById(R.id.submitSkor);


        }
    }

}
