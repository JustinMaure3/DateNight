package com.justinmaure.datenight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.justinmaure.datenight.Objects.User;

import java.util.ArrayList;

/**
 * Created by IceOf on 2018-03-27.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //database version
    public static final int DATABASE_VERSION = 1;

    //name of the database
    public static final String DATABASE_NAME = "datenight";

    //Create table names
    public static final String TABLE_USER = "User";
    public static final String TABLE_DATE = "Date";

    //Column names
    public static final String COLUMN_ID = "id";

    //User table column names
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";

    //Date table column names
    public static final String COLUMN_NAME = "datename";
//    public static final String COLUMN_

    //Create statements for our tables
    public static final String CREATE_USER_TABLE = "CREATE TABLE " +
            TABLE_USER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_EMAIL + " TEXT)";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    }

    // USER CRUD OPERATIONS
    //Create
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_EMAIL, user.getEmail());
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    //Read
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        Cursor cursor = db.query(TABLE_USER,
                new String[]{COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_EMAIL},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            user = new User(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
        }
        db.close();
        return user;
    }

    public ArrayList<User> getAllLocations(){
        ArrayList<User> userList = new ArrayList<User>();
        String query = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                userList.add(new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        db.close();
        return userList;
    }
    //Update

    //Delete
    public void deleteUser(int user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user)});
        db.close();
    }


    //DATE CRUD OPERATIONS
    //Create
    public void addDate(Date Date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_EMAIL, user.getEmail());
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    //Read
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        Cursor cursor = db.query(TABLE_USER,
                new String[]{COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_EMAIL},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            user = new User(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
        }
        db.close();
        return user;
    }

    public ArrayList<Date> getAllLocations(){
        ArrayList<User> userList = new ArrayList<User>();
        String query = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                userList.add(new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        db.close();
        return userList;
    }
    //Update

    //Delete
    public void deleteDate(int date){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_ID + " = ?",
                new String[]{String.valueOf(date)});
        db.close();
    }

}
