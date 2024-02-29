package com.technia.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetails extends AppCompatActivity {
    TextView tvPackagename,tvTotalCost;
    EditText edDetails;
    Button btnAddToCart,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvPackagename=findViewById(R.id.textViewBMDPackageName);
        tvTotalCost=findViewById(R.id.textViewBMDTotalCost);
        edDetails=findViewById(R.id.editTextTextBMDMultiLine);
        btnAddToCart=findViewById(R.id.buttonBMDAddToCart);
        btnBack=findViewById(R.id.buttonBMDGoBack);
        edDetails.setKeyListener(null);
        Intent intent=getIntent();
        tvPackagename.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("TotalCost: " + intent.getStringExtra("text3") + "/-");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetails.this,Lab_TestActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LabTestDetails", "Add to Cart button clicked");
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvPackagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                Log.d("LabTestDetails", "Username: " + username + ", Product: " + product + ", Price: " + price);
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addCart(username, product, price, "lab");
                    Toast.makeText(getApplicationContext(), "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetails.this, Lab_TestActivity.class));
                }
            }
        });
    }
}