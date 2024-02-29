package com.technia.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText edusername,edEmail,edPassword,edConfirm;
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edusername=findViewById(R.id.editRegPersonName);
        edPassword=findViewById(R.id.editTextRegPassword);
        edEmail=findViewById(R.id.editLoginRegEmail);
        edConfirm=findViewById(R.id.editRegConfirmPassword2);
        btn=findViewById(R.id.buttonRegister);
        txt=findViewById(R.id.Existinguser);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edusername.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                String emial = edEmail.getText().toString();
                Database db=new Database(getApplicationContext(),"HealthCare",null,1);
                if (username.length() == 0 || password.length() == 0 || emial.length() == 0) {

                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        if (isValid(password)) {
                            db.register(username,emial,password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this, Login.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain atleast 8 digits,having letter,digit and special Symbol", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
            public static boolean isValid(String password)
            {
                int f1=0,f2=0,f3=0;
                if(password.length()<8)
                    return  false;
                else {
                    for(int p=0;p<password.length();p++)
                    {
                        if(Character.isLetter(password.charAt(p)))
                        {
                            f1=1;
                        }
                    }
                    for(int p=0;p<password.length();p++)
                    {
                        if(Character.isDigit(password.charAt(p)))
                        {
                            f2=1;
                        }
                    }
                    for(int p=0;p<password.length();p++)
                    {
                        char c=password.charAt(p);

                        if(c>=33 && c<=46|| c==64)
                            f3=1;
                    }
                    if(f1==1 && f2==1 && f3==1)
                        return  true;
                    return false;
                }
            }
}