package com.example.hiran.onemarket.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hiran on 9/4/16.
 */
public class AppDatabase extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor c;

    public void createDB() {
        db = openOrCreateDatabase("store", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS item(item_code VARCHAR,item_name VARCHAR,unit_price int,description VARCHAR,stock int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS bill(trans_id VARCHAR,total int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS sales(trans_id VARCHAR,item_code VARCHAR,quantity int);");
    }

    public void selectDB() {
        c = db.rawQuery("SELECT * FROM sales", null);
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("trans_id: " + c.getString(0) + "\n");
            buffer.append("item_code: " + c.getString(1) + "\n");
            buffer.append("quantity: " + c.getString(2) + "\n\n");
        }
        showMessage("Student Details", buffer.toString());
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void insertIntoDB() {
        db.execSQL("INSERT INTO sales VALUES('10','abcd',50);");
        db.execSQL("INSERT INTO bill VALUES('10',444);");
        db.execSQL("INSERT INTO item VALUES('abcd','Mobile Phone',15000,'Samsung',104);");
    }
}
