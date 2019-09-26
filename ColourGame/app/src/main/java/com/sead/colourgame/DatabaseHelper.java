package com.sead.colourgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_Users";
    private static final String TB_NAME = "tb_Users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";
    private static  final  String KEY_DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //Tạo database
        String sql = "CREATE TABLE " + TB_NAME + "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT," + KEY_SCORE + " INTEGER, " + KEY_DATE + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+ TB_NAME);
        onCreate(db);
    }

    public void insertUsers(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();
        String nullColumnHack = null; // Allow null value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, users.getName());
        values.put(KEY_SCORE, users.getScore());
        values.put(KEY_DATE,users.getDate());

        db.insert(TB_NAME, nullColumnHack, values);
        db.close();
    }

    public Users getUsers(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] fields = {KEY_ID, KEY_NAME, KEY_SCORE, KEY_DATE};
        String criterials = KEY_ID + "=?";
        String[] parameters = {String.valueOf(id)};
        String groupby = null;
        String having = null;
        String orderby = null;
        Cursor cursor = db.query(TB_NAME, fields, criterials,
                parameters, groupby, having, orderby);
        if (cursor != null)
            cursor.moveToFirst();
        Users users = new Users(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),cursor.getString(3));
        return users;
    }

    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<Users>();
        String[] criterial = null;
        String selectQuery = "SELECT  * FROM " + TB_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        int rank = 0;
        if (cursor.moveToFirst()) {
            do {
                Users user = new Users();
                rank++;
                user.setRank(rank);
                user.setName(cursor.getString(1));
                user.setScore(Integer.parseInt(cursor.getString(2)));
                user.setDate(cursor.getString(3));
                // Add student to list
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }
    public List<Users> getTOP10Users() {
        List<Users> users = new ArrayList<Users>();
        String[] criterial = null;
        String selectQuery = "SELECT  * FROM " + TB_NAME + " ORDER BY " +KEY_SCORE +" DESC LIMIT 10";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        int rank = 0;
        if (cursor.moveToFirst()) {
            do {
                Users user = new Users();
                rank++;
                user.setRank(rank);
                user.setName(cursor.getString(1));
                user.setScore(Integer.parseInt(cursor.getString(2)));
                user.setDate(cursor.getString(3));
                // Add student to list
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }

    public void deleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TB_NAME);
    }
}
