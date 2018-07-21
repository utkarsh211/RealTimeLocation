package com.example.utkar.realtimelocation.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utkar.realtimelocation.R;
import com.example.utkar.realtimelocation.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;
    private EditText username;
    private EditText password;
    private TextView register;
    private Button loginButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.pswd);
        register = findViewById(R.id.register);
        loginButton = findViewById(R.id.login_btn);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);

    }

    private void initListeners() {
        loginButton.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                verifyFromSQLite();
                break;
            case R.id.register:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
    public void verifyFromSQLite(){
        String userName;
        userName = username.getText().toString();
        if (userName.matches("")) {
            Toast.makeText(this, "Please enter a valid username", Toast.LENGTH_LONG).show();
            return;
        }
        String Password;
        Password = password.getText().toString();
        if (Password.matches("")) {
            Toast.makeText(this, "You did not enter a password", Toast.LENGTH_LONG).show();
            return;
        }
        if(databaseHelper.checkUser(username.getText().toString(),password.getText().toString())){
            Intent accountsIntent = new Intent(activity, UsersActivity.class);
            accountsIntent.putExtra("USERNAME", username.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        }
        else{
            Toast.makeText(this,"Incorrect username or password",Toast.LENGTH_LONG).show();
        }

    }
    void emptyInputEditText(){
        username.setText(null);
        password.setText(null);
    }
}

