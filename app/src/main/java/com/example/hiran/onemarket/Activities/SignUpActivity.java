package com.example.hiran.onemarket.Activities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.PasswordHash;

/**
 * Created by hiran on 9/4/16.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText uname, passwd, repasswd, email, reemail;
    private CheckBox terms;
    private Button sign;
    private final String TAG = SignUpActivity.class.getSimpleName();
    private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        //db =((LoginActivity)getApplication()).db
        uname = (EditText) findViewById(R.id.txt_uname);
        email = (EditText) findViewById(R.id.txt_email);
        passwd = (EditText) findViewById(R.id.txt_passwrd);
        repasswd = (EditText) findViewById(R.id.txt_retype_passwd);
        reemail = (EditText) findViewById(R.id.txt_retype_email);
        terms = (CheckBox) findViewById(R.id.checkbox_terms);
        sign = (Button) findViewById(R.id.btn_sign_up);
        terms.setOnClickListener(this);
        sign.setOnClickListener(this);
    }

    private void signUp() {
        try {
            db.execSQL("INSERT INTO login VALUES('" + uname.getText().toString() + "','" + PasswordHash.encryptPassword(passwd.getText().toString()) + "');");
        } catch (android.database.sqlite.SQLiteException ex) {
            Log.v(TAG, ex.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
                try {
                    if (uname.getText().toString() != "" && passwd.getText().toString() != "" &&
                            repasswd.getText().toString() != "" && email.getText().toString() != "" &&
                            reemail.getText().toString() != "" && terms.isChecked()) {
                        signUp();
                    } else {
                        Toast.makeText(this, "Please complete above details", Toast.LENGTH_SHORT).show();
                    }
                } catch (NullPointerException ex) {
                    Log.v(TAG, ex.getMessage());
                }
                break;
        }
    }
}
