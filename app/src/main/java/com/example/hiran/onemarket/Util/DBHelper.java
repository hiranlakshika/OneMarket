package com.example.hiran.onemarket.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiran.onemarket.Activities.MainActivity;


/**
 * Created by hiran on 9/5/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = DBHelper.class.getSimpleName();
    private static final String DATABASE_FILE = "my_db_script.sql";
    private static final String DATABASE_NAME = "store.db";
    private static final int DATABASE_VERSION = 1;
    private static final int BUFFER_SIZE = 2048;

    private static SQLiteDatabase db;
    private static  Cursor c;
    private static DBHelper instance;
    private static Context context;

    private DBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        DBHelper.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();

        try {
            readDatabaseScript(db);
            db.setTransactionSuccessful();
        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
        } catch (SQLException sqle) {
            Log.e(TAG, sqle.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    private void readDatabaseScript(SQLiteDatabase db) throws IOException, SQLException {
        InputStream script = context.getAssets().open(DATABASE_FILE);
        byte[] buffer = new byte[BUFFER_SIZE];

        for (int byteRead = script.read(); byteRead != -1; byteRead = script.read()) {
            // resets the buffer
            Arrays.fill(buffer, (byte) 0);

            // reads a SQL statement to the buffer
            for (int i = 0; byteRead != -1 && i != BUFFER_SIZE; byteRead = script.read(), i++) {
                buffer[i] = (byte) byteRead;

                if (byteRead == ';')
                    break;
            }

            // execute the SQL statement from the buffer
            if (byteRead != -1)
                db.execSQL(new String(buffer));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public synchronized void close() {
        if (instance != null)
            db.close();
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
            db = instance.getWritableDatabase();
        }

        return instance;
    }

    public int getNumberRows(String table) {
        return (int) DatabaseUtils.queryNumEntries(db, table);
    }

    public void checkUser(EditText uname, EditText password) {

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
                Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.class.getName());
                uname.setText("");
                password.setText("");
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Login Error", Toast.LENGTH_SHORT).show();
            }
        } catch (android.database.sqlite.SQLiteException ex) {
            Log.e(TAG, ex.getMessage());
        }

    }
    public void createDB() {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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

    public void signUp(EditText uname,EditText passwd) {
        try {
            db.execSQL("INSERT INTO login VALUES('" + uname.getText().toString() + "','" + PasswordHash.encryptPassword(passwd.getText().toString()) + "');");
        } catch (android.database.sqlite.SQLiteException ex) {
            Log.v(TAG, ex.getMessage());
        }

    }

}
