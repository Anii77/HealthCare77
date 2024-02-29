package com.technia.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDetails extends AppCompatActivity {
    private String[][] orderDetails={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        btn=findViewById(R.id.buttonDDBack);
        lst=findViewById(R.id.listViewOD);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetails.this,HomeActivity.class));

            }
        });
        Database db=new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();
        ArrayList dbdata=db.getOrderData(username);
        orderDetails=new String[dbdata.size()][];
        for(int i=0;i<orderDetails.length;i++)
        {
            orderDetails[i]=new String[5];
            String arrData=dbdata.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            orderDetails[i][0]=strData[0];
            orderDetails[i][1]=strData[1];
            if(strData[7].compareTo("medicine")==0)
            {
                orderDetails[i][3]="Del:"+strData[4];

            }
            else {
                orderDetails[i][3]="Del:"+strData[4]+" "+strData[5];

            }
            orderDetails[i][2]="Rs."+strData[6];
            orderDetails[i][4]=strData[7];

        }
        list=new ArrayList();
        for(int i=0;i<orderDetails.length;i++)
        {
            item=new HashMap<String, String>();
            item.put("Line1",orderDetails[i][0]);
            item.put("Line2",orderDetails[i][1]);
            item.put("Line3",orderDetails[i][2]);
            item.put("Line4",orderDetails[i][3]);
            item.put("Line5",orderDetails[i][4]);
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
    }
}