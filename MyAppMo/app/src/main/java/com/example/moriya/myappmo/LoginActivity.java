package com.example.moriya.myappmo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin = (Button) findViewById(R.id.bLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        connectionClass = new ConnectionClass();

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
                Connection con = connectionClass.CONN();

                String query = "select * from Users where userName='" + etUsername.getText() + "'";
                Statement stmt = null;
                try {
                    stmt = con.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String fullName = "";
                try {
                    ResultSet rs = stmt.executeQuery(query);
                    if(!rs.next()) {
                        Toast.makeText(getApplicationContext(), "Username " + etUsername.getText() + " does not exist.",
                                Toast.LENGTH_LONG).show();
                        break;
                    }
                    fullName = rs.getString("firstName") + " " + rs.getString("secondName");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this, MainActivityMo.class);
                Bundle b = new Bundle();
                b.putString("key",fullName);
                intent.putExtras(b);
                startActivity(intent);
                break;
        }
    }
}
