package cl.mmoscoso.appviews.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManagerType {
    private TypeDatabaseController dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManagerType(Context c) {
        context = c;
    }

    public DBManagerType open() throws SQLException {
        dbHelper = new TypeDatabaseController(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public Long insert(String name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", name);
        return database.insert(dbHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { "id","name"};
        Cursor cursor = database.query(dbHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, Integer amount, Integer quantity, String expiration) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        int i = database.update(dbHelper.TABLE_NAME, contentValues, "ID = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbHelper.TABLE_NAME, "ID = " + _id, null);
    }

}
