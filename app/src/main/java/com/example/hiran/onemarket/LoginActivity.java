package com.example.hiran.onemarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
                if (uname.getText().toString().equals("Hiran") && password.getText().toString().equals("123")) {
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.class.getName());
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sign_up:
                Intent intent= new Intent(SignUpActivity.class.getName());
                startActivity(intent);
                Toast.makeText(this, "Sign up", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
