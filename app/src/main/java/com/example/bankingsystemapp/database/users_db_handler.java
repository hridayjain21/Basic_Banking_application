package com.example.bankingsystemapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;

import androidx.annotation.Nullable;

import com.example.bankingsystemapp.Data.user;

import java.util.ArrayList;
import java.util.List;

public class users_db_handler extends SQLiteOpenHelper {
    public users_db_handler(@Nullable Context context) {
        super(context,user_util.DATABASE_NAME,null,user_util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Users_table = "CREATE TABLE " + user_util.TABLE_NAME + "(" +
                user_util.KEY_ID + " INTEGER PRIMARY KEY," +
                user_util.KEY_NAME + " TEXT," +
                user_util.KEY_EMAIL + " VARCHAR," +
                user_util.KEY_ACCOUNT_NO + " INTEGER," +
                user_util.KEY_BALANCE + " INTEGER NOT NULL," +
                user_util.KEY_PHONE_NUMBER + " VARCHAR," +
                user_util.KEY_IFSC_CODE + " VARCHAR" + ")" ;
        db.execSQL(Users_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(drop,new String[]{user_util.TABLE_NAME});
        onCreate(db);
    }

    public void add_user(user user) {
        SQLiteDatabase databasehandler = this.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(user_util.KEY_NAME,user.getName());
        values.put(user_util.KEY_EMAIL,user.getEmail());
        values.put(user_util.KEY_ACCOUNT_NO,user.getAccountNumber());
        values.put(user_util.KEY_BALANCE,user.getBalance());
        values.put(user_util.KEY_PHONE_NUMBER,user.getPhoneNumber());
        values.put(user_util.KEY_IFSC_CODE,user.getIfscCode());

        databasehandler.insert(user_util.TABLE_NAME,null,values);
        databasehandler.close();
    }
    public user getuser(int id){
        SQLiteDatabase databasehandler = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = databasehandler.query(user_util.TABLE_NAME,new String[]{user_util.KEY_ID,user_util.KEY_NAME,user_util.KEY_EMAIL,
                user_util.KEY_ACCOUNT_NO,user_util.KEY_BALANCE,user_util.KEY_PHONE_NUMBER,user_util.KEY_IFSC_CODE},user_util.KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null);

        user user = new user();
        if(cursor.moveToFirst()){
            user.setID(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail((cursor.getString(2)));
            user.setAccountNumber(cursor.getInt(3));
            user.setBalance(cursor.getInt(4));
            user.setPhoneNumber(cursor.getString(5));
            user.setIfscCode(cursor.getString(6));
        }
        cursor.close();
        return user;

    }
    public boolean is_db_empty(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("select * from User_table", null);
        return !cursor.moveToFirst();
    }
    public List<user> getitemList(){
        SQLiteDatabase databasehandler = this.getReadableDatabase();
        List<user> userList = new ArrayList<>();


        Cursor cursor = databasehandler.query(user_util.TABLE_NAME,new String[]{user_util.KEY_ID,user_util.KEY_NAME,user_util.KEY_EMAIL,
                user_util.KEY_ACCOUNT_NO,user_util.KEY_BALANCE,user_util.KEY_PHONE_NUMBER,user_util.KEY_IFSC_CODE},
                null,null,null,null,user_util.KEY_ID );

        if(cursor.moveToFirst()){
            do {
                user user = new user();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setEmail((cursor.getString(2)));
                user.setAccountNumber(cursor.getInt(3));
                user.setBalance(cursor.getInt(4));
                user.setPhoneNumber(cursor.getString(5));
                user.setIfscCode(cursor.getString(6));
                userList.add(user);
            }while (cursor.moveToNext());
        }
        return userList;
    }
    public void delete_all(){
        String delete = "DELETE FROM " + user_util.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(delete);
    }
    public int updateitem(user user){
        SQLiteDatabase databasehandler = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(user_util.KEY_NAME,user.getName());
        values.put(user_util.KEY_EMAIL,user.getEmail());
        values.put(user_util.KEY_ACCOUNT_NO,user.getAccountNumber());
        values.put(user_util.KEY_BALANCE,user.getBalance());
        values.put(user_util.KEY_PHONE_NUMBER,user.getPhoneNumber());
        values.put(user_util.KEY_IFSC_CODE,user.getIfscCode());

        return databasehandler.update(user_util.TABLE_NAME,values,user_util.KEY_ID+"=?",new String[]{String.valueOf(user.getID())});
    }
}
