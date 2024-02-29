package com.technia.healthcare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicine extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    TextView tvTotal;
    private DatePickerDialog datePickerDialog;

    ListView lst;
    private Button datebutton, timebutton, btnCheckout, btnBack;
    private String[][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        datebutton = findViewById(R.id.buttonBMCartDate);
        btnCheckout = findViewById(R.id.buttonBMcartcheckout);
        btnBack = findViewById(R.id.buttonBMCardBack);
        tvTotal = findViewById(R.id.carttotalcostBM);
        lst = findViewById(R.id.listviewBMCart);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        float amount = 0;
        ArrayList<String> dbdate = db.getCartData(username, "medicine");

        // Initialize the packages array
        packages = new String[dbdate.size()][];
        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5];
        }

        for (int i = 0; i < dbdate.size(); i++) {
            String arrDate = dbdate.get(i).toString();
            String[] strData = arrDate.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "Cost : " + strData[1] + "/-";
            amount = amount + Float.parseFloat(strData[1]);
        }

        tvTotal.setText("Total Cost :" + amount);

        list = new ArrayList<>();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("Line1", packages[i][0]);
            item.put("Line2", packages[i][1]);
            item.put("Line3", packages[i][2]);
            item.put("Line4", packages[i][3]);
            item.put("Line5", packages[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(
                this,
                list,
                R.layout.multi_line,
                new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuyMedicine.this, BuyMedicine.class));
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeBookingDetails(username);
                Intent it = new Intent(CartBuyMedicine.this, BookMedcineDetails.class);
                it.putExtra("price", tvTotal.getText());
                it.putExtra("date", datebutton.getText());
                it.putExtra("time", timebutton.getText());
                startActivity(it);
            }
        });

        initDatePicker();

        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // You can add time button click listener here and implement time picker dialog if needed
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                datebutton.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() * 86400000);

    }

    private void storeBookingDetails(String username) {
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);

        // Assuming "BookMedcine" is your medicine order type
        db.addOrder(username, "Full Name", "Address", "Contact", 123456, datebutton.getText().toString(),
                timebutton.getText().toString(), Float.parseFloat(tvTotal.getText().toString().split(":")[1].replace("/-", "")), "BookMedcine");

        // Clear the medicine items from the cart after booking
        db.removeCart(username, "medicine");
    }
}
