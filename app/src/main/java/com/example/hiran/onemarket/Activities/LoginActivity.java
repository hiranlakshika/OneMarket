package com.example.hiran.onemarket.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.DBHelper;

/**
 * Created by hiran on 9/3/16.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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

        //Inserting data into database
//        DBHelper.getInstance(this).insertIntoDB();
        DBHelper.getInstance(this).dropCart();
        DBHelper.getInstance(this).createDB();
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
                DBHelper.getInstance(this).checkUser(uname, password);
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


}
