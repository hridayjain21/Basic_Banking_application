package com.example.bankingsystemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankingsystemapp.database.transaction_db_handler;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView title;
    private Button button;
    private transaction_db_handler db = new transaction_db_handler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        button = findViewById(R.id.view_user_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,User_list.class));
            }
        });

    }
}
