package com.example.moriya.myappmo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String USER_KEY="com.example.moriya.myappmo.USER";
    Button bLogin;
    EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin = (Button) findViewById(R.id.bLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);

        bLogin.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.bLogin:
                EditText txt =  (EditText) findViewById(R.id.etUsername);
                String usr = txt.getText().toString();
                Log.d("Debug", "**The username: " + usr + " is trying to sign in. verifying him.. ");
                if (hasPermission(usr)) {
                    Toast.makeText(this, "Username " + usr + " is verified", Toast.LENGTH_SHORT).show();
                    Log.d("Debug", "**The username: " + usr + " is verified");
                    startActivity(new Intent(this, InsertCodeActivity.class).putExtra(USER_KEY,usr));
                }
                else{
                    Toast.makeText(this, "Username " + usr + " has No permission", Toast.LENGTH_SHORT).show();
                    Log.d("Debug", "**The username: " + usr + " has No permission");
                }
                break;
        }
    }

    private Boolean hasPermission(String name){
        return name.equals("Admin123");
    }
}
