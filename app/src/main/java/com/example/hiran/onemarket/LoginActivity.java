package com.example.hiran.onemarket;

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
import android.widget.Toast;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_log_in:
                if (uname.getText().toString().equals("Hiran") && PasswordHash.encryptPassword(password.getText().toString()).equals("40bd001563085fc35165329ea1ff5c5ecbdbbeef")) {
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.class.getName());
                    uname.setText("");
                    password.setText("");
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Login Error", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sign_up:
                Intent intent = new Intent(SignUpActivity.class.getName());
                startActivity(intent);
                Toast.makeText(this, "Sign up", Toast.LENGTH_SHORT).show();
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
