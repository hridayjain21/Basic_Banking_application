package com.example.bankingsystemapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bankingsystemapp.Data.user;
import com.example.bankingsystemapp.R;
import com.example.bankingsystemapp.database.users_db_handler;

import java.text.NumberFormat;
import java.util.Locale;

public class User_profile extends AppCompatActivity {
    private int ID;
    private TextView name,email,account,phone,balance,ifsc;
    private Button transfer_money;
    private AlertDialog dialog;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            ID = bundle.getInt("user_id");
        }
        users_db_handler db = new users_db_handler(this);
        final user user = db.getuser(ID);

        name = findViewById(R.id.user_profile_name);
        email = findViewById(R.id.user_email_profile);
        phone = findViewById(R.id.user_phone_profile);
        account = findViewById(R.id.user_account_profile);
        balance = findViewById(R.id.user_Balance_profile);
        ifsc = findViewById(R.id.user_ifsc_profile);

        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en","in"));
        name.setText("Name : "+user.getName());
        email.setText("Email : "+user.getEmail());
        phone.setText("Phone : "+user.getPhoneNumber());
        account.setText("Account no : XXXXXX"+user.getAccountNumber());
        balance.setText("Balance : "+format.format(user.getBalance()));
        ifsc.setText("IFSC code : "+user.getIfscCode());

        transfer_money = findViewById(R.id.transfer_money_button);
        transfer_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_amount_dialog_box(v,user.getBalance());
            }
});
    }

    private void enter_amount_dialog_box(final View v, final int balance) {
        final EditText enteramount = new EditText(v.getContext());
        enteramount.setInputType(InputType.TYPE_CLASS_NUMBER);
        final AlertDialog.Builder enteramount_dialogbox = new AlertDialog.Builder(v.getContext());
        enteramount_dialogbox.setTitle("Enter Amount : ");
        enteramount_dialogbox.setView(enteramount);
        enteramount_dialogbox.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        enteramount_dialogbox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                transaction_cancel_dialog_box(v,balance);
            }
        });
        dialog = enteramount_dialogbox.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enteramount.getText().toString().isEmpty()){
                    enteramount.setError("Amount cannot be empty");
                }
                else if(Integer.parseInt(enteramount.getText().toString()) > balance){
                    enteramount.setError("Not enough balance in your account");
                }
                else{
                    Intent intent = new Intent(v.getContext(),select_receiver.class);
                    int amount_send = Integer.parseInt(enteramount.getText().toString());
                    Log.d("id from user_profile", "onClick: " + ID);
                    intent.putExtra("user_id_from_profile",ID);
                    intent.putExtra("amount_send",amount_send);
                    startActivity(intent);finish();
                }
            }
        });
    }

    private void transaction_cancel_dialog_box(final View v, final int balance) {
        AlertDialog.Builder cancel_box = new AlertDialog.Builder(v.getContext());
        cancel_box.setTitle("Do you want to cancel the trasaction : ");
        cancel_box.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        cancel_box.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                enter_amount_dialog_box(v,balance);
            }
        });
        cancel_box.show();
    }
}
