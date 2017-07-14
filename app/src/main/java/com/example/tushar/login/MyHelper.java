package com.example.tushar.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by tushar on 13/7/17.
 */

public class MyHelper extends SQLiteOpenHelper {
    private Context con;
    public MyHelper(Context context) {
        super(context, "MyDatabase", null,1);
        con = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table MyTable (xValues INTEGER , yValues INTEGER);";
        db.execSQL(createTable);
        Toast.makeText(con, "Table Created ",  Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertData(int x ,int y){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("xValues",x);
        contentValues.put("yValues",y);
        db.insert("MyTable",null,contentValues);
        Toast.makeText(con,"Data Inserted", Toast.LENGTH_LONG).show();
    }
}
