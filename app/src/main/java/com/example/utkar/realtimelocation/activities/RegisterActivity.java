package com.example.utkar.realtimelocation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utkar.realtimelocation.R;
import com.example.utkar.realtimelocation.model.User;
import com.example.utkar.realtimelocation.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = RegisterActivity.this;
    private EditText username;
    private EditText mobno;
    private EditText confMob;
    private EditText password;
    private EditText confPassword;
    private Button register;
    private Button cancel;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        username = findViewById(R.id.username);
        mobno = findViewById(R.id.mobno);
        confMob = findViewById(R.id.conf_mobno);
        password = findViewById(R.id.pswd1);
        confPassword = findViewById(R.id.conf_pswd);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);
    }

    private void initListeners() {
        register.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                postDataToSQLite();
                break;
            case R.id.cancel:
                username.getText().clear();
                mobno.getText().clear();
                confMob.getText().clear();
                password.getText().clear();
                confPassword.getText().clear();
                break;

        }
    }

    private void postDataToSQLite() {
        String userName;
        userName = username.getText().toString();
        if (userName.matches("")) {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_LONG).show();
            return;
        }

        String mobNo;
        mobNo = mobno.getText().toString();
        if (mobNo.matches("")) {
            Toast.makeText(this, "Mobile number cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        String conf_Mob;
        conf_Mob = confMob.getText().toString();
        if (conf_Mob.matches("")) {
            Toast.makeText(this, "Mobile number cannot be empty", Toast.LENGTH_LONG).show();
            return;

        }
        String Password;
        Password = password.getText().toString();
        if (Password.matches("")) {
            Toast.makeText(this, "You did not enter a password", Toast.LENGTH_LONG).show();
            return;
        }
        String conf_password;
        conf_password=confPassword.getText().toString();
        if(!conf_password.matches(password.getText().toString())){
            Toast.makeText(this,"Please confirm your password",Toast.LENGTH_LONG).show();
            return;
        }
        if(!databaseHelper.checkUser(username.getText().toString())){
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setMob(mobno.getText().toString());
            databaseHelper.addUser(user);
            Toast.makeText(this,"Registration Successful",Toast.LENGTH_LONG).show();
            emptyInputEditText();


        }
        else{
            Toast.makeText(this,"User already exists",Toast.LENGTH_LONG).show();
        }

    }
    private void emptyInputEditText(){
        username.setText(null);
        mobno.setText(null);
        confMob.setText(null);
        password.setText(null);
        confPassword.setText(null);

    }
}