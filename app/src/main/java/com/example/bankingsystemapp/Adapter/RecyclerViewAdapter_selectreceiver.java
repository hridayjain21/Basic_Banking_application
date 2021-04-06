package com.example.bankingsystemapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingsystemapp.Data.user;
import com.example.bankingsystemapp.R;
import com.example.bankingsystemapp.select_receiver;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter_selectreceiver extends RecyclerView.Adapter<RecyclerViewAdapter_selectreceiver.ViewHolder> {
    private select_receiver select_receiver;
    private List<user> userList;
    private Context context;

    public RecyclerViewAdapter_selectreceiver(select_receiver select_receiver,List<user> userList, Context context) {
        this.userList = userList;
        this.context = context;
        this.select_receiver = select_receiver;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_selectreceiver.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card2,parent,false);
        return new RecyclerViewAdapter_selectreceiver.ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_selectreceiver.ViewHolder holder, int position) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en","in"));
        user user = userList.get(position);
        holder.name.setText(user.getName());
        holder.phone_no.setText(user.getPhoneNumber());
        holder.balance.setText(format.format(user.getBalance()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,phone_no,balance;
        private ImageView phone_icon;
        public ViewHolder(@NonNull View view,Context context) {
            super(view);
            name = view.findViewById(R.id.name_on_card);
            phone_no = view.findViewById(R.id.phone_no);
            balance = view.findViewById(R.id.balance);
            phone_icon = view.findViewById(R.id.phone_icon);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    user receiver = userList.get(pos);
                    try {
                        select_receiver.selectuser(receiver);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
