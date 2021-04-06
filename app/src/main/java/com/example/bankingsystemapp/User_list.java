package com.example.bankingsystemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bankingsystemapp.Adapter.RecyclerViewAdapter_user;
import com.example.bankingsystemapp.Data.user;
import com.example.bankingsystemapp.database.transaction_db_handler;
import com.example.bankingsystemapp.database.users_db_handler;

import java.util.ArrayList;
import java.util.List;

public class User_list extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<user> userList;
    private RecyclerViewAdapter_user recyclerViewAdapter_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.recycler_view_userlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        userList = new ArrayList<>();
        users_db_handler db = new users_db_handler(this);
        if(db.is_db_empty()){
            db.add_user(new user("Shivam","shivam@gmail.com",5447,80000,"9425144855","ADI45678"));
            db.add_user(new user("Utkarsh","utkarsh@gmail.com",4569,60000,"9845654756","ADI44569"));
            db.add_user(new user("Harsh","harsh@gmail.com",2236,45600,"8423651458","ADI42236"));
            db.add_user(new user("Sanjay","sanjay@gmail.com",6354,25000,"8896523147","ADI46354"));
            db.add_user(new user("Parth","parth@gmail.com", 4573,35000,"7756321458","ADI44573"));
            db.add_user(new user("Alisa","alisa@gmail.com",4532,95000,"9874547852","ADI44532"));
            db.add_user(new user("Aman","aman@gmail.com",1258,58000,"9995632147","ADI41258"));
            db.add_user(new user("Samrat","samrat@gmail.com",9856,54333,"8989745214","ADI49856"));
            db.add_user(new user("Rajeev","rajeev@gmail.com",7568,45896,"9898456752","ADI47568"));
            db.add_user(new user("Saurav","saurav@gmail.com",3216,12225,"9452145874","ADI43216"));
        }
        userList = db.getitemList();
        recyclerViewAdapter_user = new RecyclerViewAdapter_user(this,userList);
        recyclerView.setAdapter(recyclerViewAdapter_user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.transaction_list_button){
            startActivity(new Intent(User_list.this,Transaction_list.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(User_list.this,MainActivity.class));
    }
}
