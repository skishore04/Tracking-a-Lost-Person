package com.example.missingperson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    EditText textInputEditTextUsername,textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignUP;
    ProgressBar progressBar;
    private long backPressedTime;

    @Override
    public void onBackPressed() {
        if(backPressedTime +2000 > System.currentTimeMillis()){
            super.onBackPressed();
            finishAffinity();
        }else
        {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView register = (TextView) findViewById(R.id.regtext);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
                finishAffinity();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        textInputEditTextUsername = findViewById(R.id.editTextTextPersonName);
        textInputEditTextPassword = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.butsign);
        progressBar = findViewById(R.id.progress);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname, username,password,email;
                username= String.valueOf(textInputEditTextUsername.getText());
                password= String.valueOf(textInputEditTextPassword.getText());

                if( !username.equals("") && !password.equals("")) {

                    progressBar.setVisibility(View.VISIBLE);
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.10.214/lostperson/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    if(result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent  = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields are Required",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}