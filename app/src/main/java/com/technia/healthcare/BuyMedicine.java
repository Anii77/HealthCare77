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

public class BuyMedicine extends AppCompatActivity {
    private String [][] packages={
            {"Uprise-D3 1000IU Capsule","","","","50"},
            {"HealthVit Chromium Picolinate 200mcg Capsule","","","","305"},
            {"VitaminB Complex Capsule","","","","4480"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","539"},
            {"Dolo 650 Tablet","","","","30"},
            {"Crocin 650 Advance Tablet","","","","50"},
            {"Strepsils Medicate Lozenges for Sore Throat","","","","40"},
            {"Tata 1mg Calcium +Vitamin D3","","","","30"},
            {"Feronia-XT Tablet","","","","130"},

    };
    private String[] packages_details={
            "Building and keeping bones & teetch Strong\n"+
                    "Rducing Faitgue/stress and mascular Pain\n"+
                    "Boosting Immunity and Increasing resistance against infection",
            "Chromium is an essential trace mineral that plays  an important role in helping involving regule blood glucose",
            "Provide relief from Vitamin B deficincies\n"+"Helps in formation of RedBlood Cells\n"+
                    "Maintains healthy Nervous System",
            "It Promotes health as well as Skin benefits.\n"+"It help in removing skin blemish and pegmentation\n"+
                    "It act as a safgaurd for the skin from the harsh UVA and UVB burn sun rays.",
            "Dolo 650 Tablets help relieve  pain and fever by blocking the relaese of certain chemical messagers responsible for fever and pain.",
            "Helps relieve fever and bring down a high Temperature\n"+
                    "Suitable for a people with high blood pressure or heart condition",
            "Relieves the symptoms of bacterial throat infection and soothes the recovery Process\n"+
                    "provides a warm and comforting feeling during sore Throat",
            "Reduces the risk of calcium deficiency ,Rickets and Osteopresis\n"+"Promote mobolity and flexibilty of Joints",
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"
    };
    HashMap<String,String > item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoTOCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        lst=findViewById(R.id.listViewBM);
        btnBack=findViewById(R.id.buttonBackBM);
        btnGoTOCart=findViewById(R.id.buttonGoToCartBM);
        btnGoTOCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         startActivity(new Intent(BuyMedicine.this,CartBuyMedicine.class));
            }

        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicine.this, HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages_details.length;i++)
        {
            item=new HashMap<String, String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_line,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(BuyMedicine.this,BuyMedicineDetails.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",packages_details[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);
            }
        });
    }
}