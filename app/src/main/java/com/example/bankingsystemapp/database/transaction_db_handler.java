package com.example.bankingsystemapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bankingsystemapp.Data.transaction;
import com.example.bankingsystemapp.Data.user;

import java.util.ArrayList;
import java.util.List;

public class transaction_db_handler extends SQLiteOpenHelper {
    public transaction_db_handler(@Nullable Context context) {
        super(context, transaction_util.DATABASE_NAME, null, transaction_util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String transactions_table = "CREATE TABLE " + transaction_util.TABLE_NAME + "(" +
                transaction_util.KEY_ID + " INTEGER PRIMARY KEY," +
                transaction_util.KEY_SENDER_NAME + " TEXT," +
                transaction_util.KEY_RECEIVER_NAME + " TEXT," +
                transaction_util.KEY_AMOUNT + " INTEGER," +
                transaction_util.KEY_STATUS + " INTEGER," +
                transaction_util.KEY_DATE + " VARCHAR"+ ")" ;
        db.execSQL(transactions_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(drop,new String[]{transaction_util.TABLE_NAME});
        onCreate(db);
    }

    public void add_transaction(transaction transaction) {
        SQLiteDatabase databasehandler = this.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(transaction_util.KEY_SENDER_NAME,transaction.getSender());
        values.put(transaction_util.KEY_RECEIVER_NAME,transaction.getReceiver());
        values.put(transaction_util.KEY_AMOUNT,transaction.getAmount());
        values.put(transaction_util.KEY_STATUS,transaction.getStatus());
        values.put(transaction_util.KEY_DATE,transaction.getDate());

        databasehandler.insert(transaction_util.TABLE_NAME,null,values);
        databasehandler.close();
    }
    public void deleteall(){
        String delete = "DELETE FROM " + transaction_util.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(delete);
    }
    public List<transaction> get_transactions(){

        SQLiteDatabase databasehandler = this.getReadableDatabase();
        List<transaction> transactionlist = new ArrayList<>();

        Cursor cursor = databasehandler.query(transaction_util.TABLE_NAME,new String[]{transaction_util.KEY_ID,transaction_util.KEY_SENDER_NAME,
                        transaction_util.KEY_RECEIVER_NAME,transaction_util.KEY_AMOUNT,transaction_util.KEY_STATUS,transaction_util.KEY_DATE},
                null,null,null,null,transaction_util.KEY_ID );

        if(cursor.moveToFirst()){
            do {
                transaction transaction = new transaction();
                transaction.setID(Integer.parseInt(cursor.getString(0)));
                transaction.setSender(cursor.getString(1));
                transaction.setReceiver(cursor.getString(2));
                transaction.setAmount(cursor.getInt(3));
                transaction.setStatus(cursor.getInt(4));
                transaction.setDate(cursor.getString(5));
                transactionlist.add(transaction);
            }while (cursor.moveToNext());
        }
        return transactionlist;
    }
}
