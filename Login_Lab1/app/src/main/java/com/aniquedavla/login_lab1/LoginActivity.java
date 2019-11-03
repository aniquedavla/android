package com.aniquedavla.login_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText usernameInput, password;
        usernameInput = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        System.out.println(usernameInput.getText().toString());
        System.out.println(password.getText().toString());
    }
}
