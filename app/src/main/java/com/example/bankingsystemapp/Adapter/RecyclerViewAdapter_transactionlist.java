package com.example.bankingsystemapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingsystemapp.Data.transaction;
import com.example.bankingsystemapp.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter_transactionlist extends RecyclerView.Adapter<RecyclerViewAdapter_transactionlist.ViewHolder>{
    private Context context;
    private List<transaction> transactionList;

    public RecyclerViewAdapter_transactionlist(Context context, List<transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter_transactionlist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_card,parent,false);
        return new RecyclerViewAdapter_transactionlist.ViewHolder(view,context);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_transactionlist.ViewHolder holder, int position) {
        transaction transaction = transactionList.get(position);
        holder.sender.setText(transaction.getSender());
        String receiver = transaction.getReceiver();
        if(transaction.getStatus() == 0){
            holder.receiver.setText("Not selected");
            holder.receiver.setTextColor(Color.RED);
        }else if (transaction.getStatus() == 1){
            holder.receiver.setText(receiver);
        }
        holder.date.setText(transaction.getDate());
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en","in"));
        holder.amount.setText(format.format(transaction.getAmount())+"/-");
        int status = transaction.getStatus();
        if(status == 0){
            holder.status.setText("Failed");
            holder.status.setTextColor(Color.RED);
        }else if(status == 1){
            holder.status.setText("Success");
            holder.status.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sender,receiver,date,status,amount;
        private ImageView arrow;
        public ViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender_name);
            receiver = itemView.findViewById(R.id.reciever_name);
            date = itemView.findViewById(R.id.date_on_transaction_card);
            amount = itemView.findViewById(R.id.amount_on_transaction_card);
            status = itemView.findViewById(R.id.status_on_transaction_card);
            arrow  = itemView.findViewById(R.id.arrow);
        }
    }
}
