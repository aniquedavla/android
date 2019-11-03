package com.aniquedavla.login_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;

public class LoginActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    Button loginBtn, showHideBtn;
    String correctUserName = "010547563";
    String correctPassword = "CMPE#137";
    int loginCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);
        showHideBtn = findViewById(R.id.showHideBtn);
        System.out.println();
        System.out.println();
         loginCount = 0;
        showHideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showHideBtn.getText().toString().equals("Show")){
                    passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showHideBtn.setText("Hide");
                } else{
                    passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showHideBtn.setText("Show");
                }
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //track login attempts
                    loginCount ++;
                    if(loginCount > 3){
                        Toast.makeText(LoginActivity.this,"Too many attempts! Please try again after 30 Seconds", Toast.LENGTH_SHORT).show();
//                        Timer loginAttemptResetTimer = new Timer();
//                        int seconds = 30 * 1000;
//                        //wait 30 seconds.
//                        try {
//                            loginAttemptResetTimer.wait(seconds);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }

                        //reset attempts allowed.
                        loginCount = 0;
                    }
                    String userName = usernameInput.getText().toString();
                    String password = passwordInput.getText().toString();
                    if (userName.isEmpty()) {
                        usernameInput.setError("Please enter your SJSU ID");
                        usernameInput.requestFocus();
                    }
                    else if (password.isEmpty()) {
                        passwordInput.setError("Please enter your password");
                        passwordInput.requestFocus();
                    }
                    else if (userName.isEmpty() && password.isEmpty()) {
                        Toast.makeText(LoginActivity.this,"Please enter a username and password to login", Toast.LENGTH_SHORT).show();
                    }
                    else if(!(userName.isEmpty() && password.isEmpty())){
                        //username and password entered
                        boolean isPasswordLowerCase = !(password.equals(password.toUpperCase()));
                        if(!isPasswordLowerCase){
                            if(!userName.equals(correctUserName)){
                                usernameInput.setError("Invalid Username");
                                usernameInput.requestFocus();
                            } else if(!password.equals(correctPassword)){
                                passwordInput.setError("Invalid Password");
                                passwordInput.requestFocus();

                            } else if(userName.equals(correctUserName) && password.equals(correctPassword)){
                                setContentView(R.layout.hello_world);
                            }

                        } else {
                            passwordInput.setError("Try using upper-case password");
                            passwordInput.requestFocus();

                        }
                    }
                }
            }
        );
    }
}
