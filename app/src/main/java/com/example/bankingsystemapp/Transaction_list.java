package com.example.bankingsystemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bankingsystemapp.Adapter.RecyclerViewAdapter_transactionlist;
import com.example.bankingsystemapp.Data.transaction;
import com.example.bankingsystemapp.database.transaction_db_handler;

import java.util.List;

public class Transaction_list extends AppCompatActivity {
    private List<transaction> transactionList;
    private RecyclerView recyclerView;
    private TextView transcations;
    private RecyclerViewAdapter_transactionlist recyclerViewAdapter_transactionlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        transaction_db_handler db = new transaction_db_handler(this);
        transcations = findViewById(R.id.transaction_title);
        transactionList = db.get_transactions();
        recyclerView = findViewById(R.id.transaction_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerViewAdapter_transactionlist = new RecyclerViewAdapter_transactionlist(this,transactionList);
        recyclerView.setAdapter(recyclerViewAdapter_transactionlist);
    }
}
