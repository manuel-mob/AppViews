package cl.mmoscoso.appviews.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class DBManager {
    private ProductDatabaseController dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new ProductDatabaseController(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, Integer amount, Integer quantity, String expiration) {
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", name);
        contentValue.put("amount", amount);
        contentValue.put("quantity", quantity);
        contentValue.put("expiration", expiration);
        database.insert(dbHelper.TABLE_NAME, null, contentValue);
    }

    public Long insert(String name, Integer amount, Integer quantity) {
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", name);
        contentValue.put("amount", amount);
        contentValue.put("quantity", quantity);
        return database.insert(dbHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { "id","name","amount","quantity", "expiration"};
        Cursor cursor = database.query(dbHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchProducts() {
        String[] columns = new String[] { "id","name","amount","quantity", "expiration"};
        Cursor cursor = database.query(dbHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, Integer amount, Integer quantity, String expiration) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("amount", amount);
        contentValues.put("quantity", quantity);
        contentValues.put("expiration", expiration);
        int i = database.update(dbHelper.TABLE_NAME, contentValues, "ID = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbHelper.TABLE_NAME, "ID = " + _id, null);
    }

}
