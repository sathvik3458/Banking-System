package com.example.basicbankingapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.model.Transaction_History_Model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_c extends RecyclerView.Adapter<RecyclerViewAdapter_c.ViewHolder> {
    private Context context;
    private List<Transaction_History_Model> dataHolder_c;
    String SUCCESS="Success";
    String FAIL="Fail";

    public RecyclerViewAdapter_c(Context context, ArrayList<Transaction_History_Model> dataHolder_c) {
        this.context = context;
        this.dataHolder_c = dataHolder_c;
    }

    @NotNull
    @Override
    public RecyclerViewAdapter_c.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_history_card_view, parent, false);
        return new RecyclerViewAdapter_c.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerViewAdapter_c.ViewHolder holder, int position) {

        holder.from_name.setText(dataHolder_c.get(position).getFrom_name());
        holder.to_name.setText(dataHolder_c.get(position).getTo_name());
        holder.amount.setText(dataHolder_c.get(position).getAmount());
        holder.date_time.setText(dataHolder_c.get(position).getDate_time());
        if(dataHolder_c.get(position).getStatus().equals("0")){
            ((CardView)holder.itemView).setCardBackgroundColor(Color.rgb(255,80,80));
            holder.status.setText(FAIL);
        }else {
            ((CardView)holder.itemView).setCardBackgroundColor(Color.rgb(80,255,80));
            holder.status.setText(SUCCESS);
        }
    }

    @Override
    public int getItemCount() {
        return dataHolder_c.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView from_name, to_name, amount, status,date_time;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            from_name = itemView.findViewById(R.id.sender_name);
            to_name = itemView.findViewById(R.id.receiver_name);
            amount = itemView.findViewById(R.id.transaction_amount);
            status = itemView.findViewById(R.id.transaction_status);
            date_time=itemView.findViewById(R.id.date_time);

        }
    }
}
