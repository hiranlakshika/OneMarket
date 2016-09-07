package com.example.hiran.onemarket.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiran.onemarket.R;
import com.example.hiran.onemarket.Util.DBHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hiran on 9/4/16.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText uname, passwd, repasswd, email, reemail;
    private CheckBox terms;
    private Button sign;
    private final String TAG = SignUpActivity.class.getSimpleName();
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
                if (!uname.getText().toString().equals("") && !passwd.getText().toString().equals("") &&
                        !repasswd.getText().toString().equals("") && !email.getText().toString().equals("") &&
                        !reemail.getText().toString().equals("") && terms.isChecked()) {
                    if (!passwd.getText().toString().equals(repasswd.getText().toString())) {
                        Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
                    } else if (!email.getText().toString().equals(reemail.getText().toString())) {
                        Toast.makeText(this, "Emails didn't match", Toast.LENGTH_SHORT).show();
                    } else if (validateEmail(email.getText().toString()) == false || validateEmail(reemail.getText().toString()) == false) {
                        Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    } else {
                        DBHelper.getInstance(this).signUp(uname, passwd);
                        Toast.makeText(this, "User has been added", Toast.LENGTH_SHORT).show();
                        super.onBackPressed();
                    }
                } else {
                    Toast.makeText(this, "Please complete above details", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
