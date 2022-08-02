package com.skiripsi.pembelajaranbm.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skiripsi.pembelajaranbm.R;
import com.skiripsi.pembelajaranbm.data.history;

import java.util.ArrayList;
import java.util.List;

public class historyadapter extends RecyclerView.Adapter<historyadapter.MyViewHolder> {

    Context context;

    ArrayList<history> list;

    public historyadapter(Context context, List<history> historyList) {
        this.context = context;
        this.list = (ArrayList<history>) historyList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_custom_result,parent,false);
        return  new historyadapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        history history = list.get(position);
        holder.tanggal.setText(history.getTanggal());
        holder.totalBenar.setText(history.getSkorBenar());
        holder.totalSalah.setText(history.getSkorSalah());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tanggal,totalBenar,totalSalah;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tanggal = itemView.findViewById(R.id.tanggal);
            totalBenar = itemView.findViewById(R.id.totalBenar);
            totalSalah = itemView.findViewById(R.id.totalSalah);
        }
    }


}
