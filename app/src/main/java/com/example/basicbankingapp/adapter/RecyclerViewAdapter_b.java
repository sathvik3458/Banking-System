package com.example.basicbankingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.RecyclerItemClickListener;
import com.example.basicbankingapp.model.Users;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerViewAdapter_b extends RecyclerView.Adapter<RecyclerViewAdapter_b.ViewHolder> {
    private Context context;
    private List<Users> dataHolder_b;
    private RecyclerItemClickListener listener;

    public RecyclerViewAdapter_b(Context context, List<Users> dataHolder_b, RecyclerItemClickListener listener) {
        this.context = context;
        this.dataHolder_b = dataHolder_b;
        this.listener=listener;
    }

    @NotNull
    @Override
    public RecyclerViewAdapter_b.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new RecyclerViewAdapter_b.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewAdapter_b.ViewHolder holder, int position) {
        holder.username.setText(dataHolder_b.get(position).getName());
        holder.usermobile_no.setText(dataHolder_b.get(position).getMob_no());
        holder.useramount.setText(dataHolder_b.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return dataHolder_b.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView username, usermobile_no, useramount;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            usermobile_no = itemView.findViewById(R.id.usermobile_no);
            useramount = itemView.findViewById(R.id.useramount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getBindingAdapterPosition());
                }
            });
        }

    }
}
