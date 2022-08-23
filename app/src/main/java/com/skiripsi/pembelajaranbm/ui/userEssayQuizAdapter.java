package com.skiripsi.pembelajaranbm.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skiripsi.pembelajaranbm.R;
import com.skiripsi.pembelajaranbm.data.User;

import java.util.ArrayList;
import java.util.List;

public class userEssayQuizAdapter extends RecyclerView.Adapter<userEssayQuizAdapter.MyViewHolder>{

    Context context;

    ArrayList<User> list;

    public userEssayQuizAdapter(Context context, List<User> userList) {
        this.context = context;
        this.list = (ArrayList<User>) userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.data_quiz_adapter,parent,false);

        context = parent.getContext();
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.nama.setText(user.getFullname());
        holder.email.setText(user.getEmail());
        holder.userId.setText(user.getUserId());

        holder.detailQuizUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,detailEssayQuizAdmin.class);
                intent.putExtra("userID",user.getUserId());
                context.startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama,email,userId;
        ImageView profile;
        TextView detailQuizUser;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.text);
            email = itemView.findViewById(R.id.email);
            userId = itemView.findViewById(R.id.userId);
            detailQuizUser = itemView.findViewById(R.id.detailQuizUser);

        }
    }
}
