package com.example.hiran.onemarket.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.PasswordHash;

/**
 * Created by hiran on 9/3/16.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = LoginActivity.class.getSimpleName();
    private SQLiteDatabase db;
    private Cursor c;
    private EditText uname, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button login = (Button) findViewById(R.id.btn_log_in);
        login.setOnClickListener(this);
        uname = (EditText) findViewById(R.id.txt_uname);
        password = (EditText) findViewById(R.id.txt_passwd);
        TextView signUp = (TextView) findViewById(R.id.sign_up);
        signUp.setOnClickListener(this);

        createDB();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_log_in:
                checkUser();
                break;
            case R.id.sign_up:
                Intent intent = new Intent(SignUpActivity.class.getName());
                startActivity(intent);
                break;
        }

    }

    //Changing the back key event
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("Alert !");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Do you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            LoginActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        return super.onKeyDown(keyCode, event);
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

    private void checkUser() {
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

}
