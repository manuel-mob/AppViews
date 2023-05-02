package cl.mmoscoso.appviews.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProductDatabaseController extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "product.db";
    private static final int DATABASE_VERSION = 2;
    private static final String CREATE_TABLE = "CREATE TABLE product (id INTEGER PRIMARY KEY, name TEXT, amount INTEGER, quantity INTEGER, expiration DATE)";

    public String TABLE_NAME = "product";

    public ProductDatabaseController(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ProductDatabaseController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS product");
        onCreate(db);
    }
}
