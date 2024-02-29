package com.technia.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetails extends AppCompatActivity {
    private String[][] doctor_details1={
            {"Doctor Name:Govind","Hospital Address:Pimpri","Exp:5yrs","Mobile No:85458","500"},
            {"Doctor Name:Mohan","Hospital Address:Bangalore","Exp:6yrs","Mobile No:22331","700"},
            {"Doctor Name:Shyam","Hospital Address:Pune","Exp:5yrs","Mobile No:89741","500"},
            {"Doctor Name:Keshav","Hospital Address:Mumbai","Exp:4yrs","Mobile No:45698","600"},
            {"Doctor Name:Hari","Hospital Address:Delhi","Exp:3yrs","Mobile No:78941","200"}
    };
    private String[][] doctor_details2={
            {"Doctor Name:Virat","Hospital Address:Delhi","Exp:5yrs","Mobile No:98741","500"},
            {"Doctor Name:Rohit","Hospital Address:Mumbai","Exp:4yrs","Mobile No:55223","700"},
            {"Doctor Name:Dhoni","Hospital Address:Jharkhand","Exp:3yrs","Mobile No:54896","300"},
            {"Doctor Name:Shewag","Hospital Address:Punjab","Exp:2yrs","Mobile No:41236","500"},
            {"Doctor Name:Sachin","Hospital Address:Mumbai","Exp:5yrs","Mobile No:89754","800"}
    };

    private String[][] doctor_details3={
            {"Doctor Name:Sania","Hospital Address:Amethi","Exp:7yrs","Mobile No:22145","800"},
            {"Doctor Name:Smridhhi","Hospital Address:Vridndvan","Exp:5yrs","Mobile No:48996","200"},
            {"Doctor Name:Shoiab","Hospital Address:Ayodhya","Exp:8yrs","Mobile No:54602","500"},
            {"Doctor Name:Santa","Hospital Address:Lucknow","Exp:5yrs","Mobile No:78942","300"},
            {"Doctor Name:Rajeev","Hospital Address:Kanpur","Exp:3yrs","Mobile No:55412","700"}
    };

    private String[][] doctor_details4={
            {"Doctor Name:Cook","Hospital Address:Delhi","Exp:8yrs","Mobile No:89745","400"},
            {"Doctor Name:Stokes","Hospital Address:Chennai","Exp:7yrs","Mobile No:89541","500"},
            {"Doctor Name:Henry","Hospital Address:Hyderabad","Exp:3yrs","Mobile No:78942","300"},
            {"Doctor Name:Monk","Hospital Address:Kanpur","Exp:8yrs","Mobile No:96547","700"},
            {"Doctor Name:Ben","Hospital Address:Lucknow","Exp:8yrs","Mobile No:85421","600"}
    };

    private String[][] doctor_details5={
            {"Doctor Name:Smith","Hospital Address:Lucknow","Exp:5yrs","Mobile No:85421","900"},
            {"Doctor Name:Clark","Hospital Address:Keshavpuram","Exp:4yrs","Mobile No:74123","200"},
            {"Doctor Name:Dev","Hospital Address:Jhasi","Exp:8yrs","Mobile No:78945","500"},
            {"Doctor Name:Rinku","Hospital Address:Shyamnagar","Exp:7yrs","Mobile No:89745","300"},
            {"Doctor Name:Tejas","Hospital Address:Arunchal Pradesh","Exp:3yrs","Mobile No:87416","500"}
    };
    TextView tv;
    Button btn;
    String [][] doctor_details={};
    ArrayList List;
    HashMap<String,String> item;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv=findViewById(R.id.textViewDoctortitle);
        btn=findViewById(R.id.buttonBMCardBack);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Family Physician")==0)
        {
            doctor_details=doctor_details1;
        }
        else if(title.compareTo("Dietician")==0)
        {
            doctor_details=doctor_details2;
        }
        else if(title.compareTo("Dentist")==0)
        {
            doctor_details=doctor_details3;
        }
        else if(title.compareTo("Surgeon")==0)
        {
            doctor_details=doctor_details4;
        }
        else {
            doctor_details=doctor_details4;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetails.this,FindDoctor.class));
            }
        });
        List=new ArrayList();
        for(int i=0;i<doctor_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            List.add(item);
        }
        sa=new SimpleAdapter(this,List,R.layout.multi_line,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.listviewBMCart);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(DoctorDetails.this, BookAppointmnet.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);

                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });

    }
}