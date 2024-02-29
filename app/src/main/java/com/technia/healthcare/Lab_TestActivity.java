package com.technia.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Lab_TestActivity extends AppCompatActivity {
    private String[][] packages={
            {"Package1 : Full Body CheckUp","","","","999"},
            {"Package2 : Blood Glucose Fasting","","","","299"},
            {"Package3 : COVID_19 AntiBody","","","","899"},
            {"Package4 : Thyroid Check","","","","499"},
            {"Package5 : Immunity Check","","","","699"}

    };private String[][] packageDetails = {
            {"Blood Glucose Fasting", "Complete Hemogram", "HbA1c", "Iron Studies"},
            {"Kidney Function Test", "LDH (Lactate Dehydrogenase) + Serum",
                    "Lipid Profile", "Liver Function Test"},
            {"Blood Glucose Fasting"},{"COVID-19 Antibody-IgG"},{"Thyroid Profile-Total(T3,T4 &TSH Ultra-Sensitive"},{
            "Complete Hemogram","Liver Function Test","Lipid Profile","Vitamin-D Total25 Hydroxy","Iron Studies"
    }
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart,btnBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnGoToCart=findViewById(R.id.buttonBMcartcheckout);
        btnBack=findViewById(R.id.buttonBMCardBack);
        listView=findViewById(R.id.listviewBMCart);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lab_TestActivity.this,HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_line,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.listviewBMCart);
        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(Lab_TestActivity.this,LabTestDetails.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",packageDetails[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);
            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lab_TestActivity.this,CardLabActivity.class));


            }
        });

    }
}