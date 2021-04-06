package com.example.bankingsystemapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingsystemapp.Adapter.RecyclerViewAdapter_selectreceiver;
import com.example.bankingsystemapp.Data.transaction;
import com.example.bankingsystemapp.Data.user;
import com.example.bankingsystemapp.database.transaction_db_handler;
import com.example.bankingsystemapp.database.users_db_handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class select_receiver extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_selectreceiver recyclerViewAdapter_selectreceiver;
    private List<user> userList;
    private users_db_handler db;
    private int balance_send;
    private int sender_id;private TextView text;
    private transaction_db_handler transaction_db;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_receiver);
        text = findViewById(R.id.text);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            sender_id = bundle.getInt("user_id_from_profile");
            balance_send = bundle.getInt("amount_send");
        }
        db = new users_db_handler(this);


        transaction_db = new transaction_db_handler(this);
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat date_form = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
        date = date_form.format(calendar.getTime());

        userList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_select_receiver);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        userList = db.getitemList();
        recyclerViewAdapter_selectreceiver = new RecyclerViewAdapter_selectreceiver(select_receiver.this,userList,this);
        recyclerView.setAdapter(recyclerViewAdapter_selectreceiver);
    }
    public  void selectuser(user receiver) throws InterruptedException {
        user sender = db.getuser(sender_id);
        changed_balance(sender, receiver);
        db.updateitem(sender);
        db.updateitem(receiver);
        transaction transaction = new transaction();
        transaction.setSender(sender.getName());
        transaction.setReceiver(receiver.getName());
        transaction.setAmount(balance_send);
        transaction.setStatus(1);
        transaction.setDate(date);
        transaction_db.add_transaction(transaction);
        Toast.makeText(this, "Transaction Successfull", Toast.LENGTH_LONG).show();
        startActivity(new Intent(select_receiver.this,User_list.class));
    }

    public void changed_balance(user sender, user receiver) {
        sender.setBalance(sender.getBalance() - balance_send);
        receiver.setBalance(receiver.getBalance() + balance_send);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder cancel_box = new AlertDialog.Builder(this);
        cancel_box.setTitle("Do you want to cancel the trasaction : ");
        cancel_box.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user sender = db.getuser(sender_id);
                transaction transaction = new transaction();
                transaction.setSender(sender.getName());
                transaction.setReceiver("Not selected");
                transaction.setAmount(balance_send);
                transaction.setStatus(0);
                transaction.setDate(date);
                transaction_db.add_transaction(transaction);
                Toast.makeText(select_receiver.this, "Transaction Cancelled ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(select_receiver.this,User_list.class));
            }
        });
        cancel_box.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        cancel_box.show();
    }
}
