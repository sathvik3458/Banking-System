package com.example.basicbankingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.model.Users;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Users> dataHolder;

    public RecyclerViewAdapter(Context context, List<Users> dataHolder) {
        this.context = context;
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.username.setText(dataHolder.get(position).getName());
        holder.usermobile_no.setText(dataHolder.get(position).getMob_no());
        holder.useramount.setText(dataHolder.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView username, usermobile_no, useramount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username =itemView.findViewById(R.id.username);
            usermobile_no =itemView.findViewById(R.id.usermobile_no);
            useramount =itemView.findViewById(R.id.useramount);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
