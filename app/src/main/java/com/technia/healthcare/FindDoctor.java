package com.technia.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        CardView exit=findViewById(R.id.logout2);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctor.this,HomeActivity.class));

            }
        });
        CardView familyPhysician=findViewById(R.id.familyphysician);
        familyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Family Physician");
                startActivity(it);
            }
        });
        CardView dietician=findViewById(R.id.dietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Dietician");
                startActivity(it);
            }
        });
        CardView dentist=findViewById(R.id.dentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });
        CardView surgeon=findViewById(R.id.surgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
            }
        });
        CardView cardeologist=findViewById(R.id.cardiologist);
        cardeologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Cardiologist");
                startActivity(it);
            }
        });
    }
}