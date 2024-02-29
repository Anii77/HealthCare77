package com.technia.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {
    EditText edtname,edaddress,edcontact,edpincode;
    Button btnbooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        btnbooking = findViewById(R.id.buttonBMBBook);
        edtname=findViewById(R.id.editTextBMBFullname);
        edaddress=findViewById(R.id.editBMBAddress);
        edcontact=findViewById(R.id.editBMBcontactnumber);
        edpincode=findViewById(R.id.editBMBpincode);
        Intent intent=getIntent();
        String [] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");
        btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(
                        username,
                        edtname.getText().toString(),
                        edaddress.getText().toString(),
                        edcontact.getText().toString(),
                        Integer.parseInt(edpincode.getText().toString()),
                        date.toString(),
                        time.toString(),
                        Float.parseFloat(price[1].toString()),
                        "lab"
                );
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(), "Your Booking is done Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
            }
        });
    }
}