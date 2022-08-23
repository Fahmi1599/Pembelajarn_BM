package com.skiripsi.pembelajaranbm.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skiripsi.pembelajaranbm.R;

import java.util.ArrayList;
import java.util.List;

public class adapter_datahasilessay_user extends RecyclerView.Adapter<adapter_datahasilessay_user.MyViewHolder>  {

    Context context;

    ArrayList<Essay> essays;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,reference,reference2;

    public adapter_datahasilessay_user(Context context, List<Essay> essays) {
        this.context = context;
        this.essays = (ArrayList<Essay>) essays;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_history_essayquiz,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //history history = list.get(position);
        Essay essay = essays.get(position);
        holder.soal.setText(essay.getSoal());
        holder.Jawaban.setText(essay.getJawaban());
        holder.skor.setText(essay.getSkor());


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
