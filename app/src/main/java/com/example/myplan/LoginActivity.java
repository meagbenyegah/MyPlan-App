package com.example.myplan;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.useremail);
        password = (EditText) findViewById(R.id.userpass);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(e -> {
            try {
                login(email.getText().toString(), password.getText().toString());
            }
            catch (Exception ex)
            {
                Toast.makeText(this, "Some fields are empty", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void login(String email, String password)
    {
        if(email.equalsIgnoreCase("test@gmail.com") && password.equalsIgnoreCase("password"))
        {
            Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, PlansActivity.class);
            //intent.putExtra("key", value); //Optional parameters
            LoginActivity.this.startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Login Faile", Toast.LENGTH_LONG).show();
        }

    }
}