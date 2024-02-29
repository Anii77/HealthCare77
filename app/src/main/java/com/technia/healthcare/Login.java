package com.technia.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.editLoginPersonName);
        edtPassword = findViewById(R.id.editLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        txt = findViewById(R.id.Registration);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                Database db=new Database(getApplicationContext(),"HealthCare",null,1);
                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
                }

                else {
                    if(db.login(username,password)==1) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(new Intent(Login.this, HomeActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
}