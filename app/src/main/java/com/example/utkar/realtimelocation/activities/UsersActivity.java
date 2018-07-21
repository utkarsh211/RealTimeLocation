package com.example.utkar.realtimelocation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.utkar.realtimelocation.LocationLayout;
import com.example.utkar.realtimelocation.MapsActivity;
import com.example.utkar.realtimelocation.R;


public class UsersActivity extends Activity {
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        textViewName = (TextView) findViewById(R.id.text1);
        String nameFromIntent = getIntent().getStringExtra("USERNAME");
        textViewName.setText("Welcome " + nameFromIntent);

        }
    public void turntrackerOn(View view){
        boolean checked=((ToggleButton)view).isChecked();
        if(checked){
            Intent intent= new Intent(UsersActivity.this,LocationLayout.class);
            startActivity(intent);
        }
        else
        {
            Intent intent1=new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent1);

        }
    }
}

