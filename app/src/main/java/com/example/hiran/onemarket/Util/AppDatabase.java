package com.example.hiran.onemarket.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiran.onemarket.Activities.MainActivity;

/**
 * Created by hiran on 9/5/16.
 */
public class AppDatabase extends AppCompatActivity {

    private static AppDatabase instance;
    private SQLiteDatabase db;
    private Cursor c;

    private AppDatabase() {
    }

    public static AppDatabase getInstance() {
        if (instance == null) {
            instance = new AppDatabase();
        }
        return instance;
    }

    public void createDB() {
        db = openOrCreateDatabase("store", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS item(item_code VARCHAR,item_name VARCHAR,unit_price int,description VARCHAR,stock int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS bill(trans_id VARCHAR,total int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS sales(trans_id VARCHAR,item_code VARCHAR,quantity int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS login(username VARCHAR,password VARCHAR);");
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
//        db.execSQL("INSERT INTO sales VALUES('10','abcd',50);");
//        db.execSQL("INSERT INTO bill VALUES('10',444);");
//        db.execSQL("INSERT INTO item VALUES('abcd','Mobile Phone',15000,'Samsung',104);");
        db.execSQL("INSERT INTO login VALUES('Hiran','40bd001563085fc35165329ea1ff5c5ecbdbbeef');");
    }

    public void checkUser(EditText uname, EditText password, String TAG) {
        String pass = "";
        try {
            c = db.rawQuery("SELECT password FROM login where username = '" + uname.getText().toString() + "'", null);
            if (c.getCount() == 0) {
                showMessage("Error", "Wrong details");
                return;
            }
            while (c.moveToNext()) {
                pass = c.getString(c.getColumnIndex("password"));
            }
            if (PasswordHash.encryptPassword(password.getText().toString()).equals(pass)) {
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.class.getName());
                uname.setText("");
                password.setText("");
                startActivity(intent);
            } else {
                Toast.makeText(this, "Login Error", Toast.LENGTH_SHORT).show();
            }
        } catch (android.database.sqlite.SQLiteException ex) {
            Log.v(TAG, ex.getMessage());
        }

    }

}
