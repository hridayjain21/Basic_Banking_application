package com.example.bankingsystemapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingsystemapp.Data.user;
import com.example.bankingsystemapp.R;
import com.example.bankingsystemapp.User_profile;

import java.text.Format;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter_user extends RecyclerView.Adapter<RecyclerViewAdapter_user.ViewHolder> {
    private Context context;
    private List<user> userList;

    public RecyclerViewAdapter_user(Context context, List<user> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_user.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card2,parent,false);
        return new ViewHolder(view,context);
    }

    @SuppressLint({"SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_user.ViewHolder holder, int position) {
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
        public ViewHolder(View view, final Context context) {
            super(view);
            name = view.findViewById(R.id.name_on_card);
            phone_no = view.findViewById(R.id.phone_no);
            balance = view.findViewById(R.id.balance);
            phone_icon = view.findViewById(R.id.phone_icon);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    user user = userList.get(pos);
                    Intent intent = new Intent(context, User_profile.class);
                    intent.putExtra("user_id",user.getID());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
