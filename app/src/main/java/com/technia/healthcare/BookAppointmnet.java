package com.technia.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmnet extends AppCompatActivity {
    EditText edt1,edt2,edt3,edt4;
    TextView txt;

    private DatePickerDialog datePickerDialog;

    private Button datebutton,timebutton,btnBook,btnBack;
    private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointmnet);
        txt=findViewById(R.id.textViewApptitle);
        edt1=findViewById(R.id.editAppName);
        edt2=findViewById(R.id.editAddress);
        edt3=findViewById(R.id.editTextContactNumber);
        edt4=findViewById(R.id.editFees);
        btnBook=findViewById(R.id.buttonRegister);
        btnBack=findViewById(R.id.buttonBackBM);
        datebutton=findViewById(R.id.buttonBMCartDate);
        timebutton=findViewById(R.id.buttonCartTime);
        edt1.setKeyListener(null);
        edt2.setKeyListener(null);
        edt3.setKeyListener(null);
        edt4.setKeyListener(null);
        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String Contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");
        txt.setText(title);
        edt1.setText(fullname);
        edt2.setText(address);
        edt3.setText(Contact);
        edt4.setText("Cons Fees:"+fees+"/-");
        initDatePicker();
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmnet.this, FindDoctor.class));
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected date and time from the buttons
                String selectedDate = datebutton.getText().toString();
                String selectedTime = timebutton.getText().toString();

                // Perform the booking logic (you can customize this part based on your needs)
                // For example, you can save the booking details to a database or perform any other relevant action.

                // Show a Toast message
                showAppointmentBookedToast();

                // Navigate to FindDoctor (Home) activity after a delay (optional)
                navigateToHomeActivityAfterDelay();
            }
        });
    }
    private void showAppointmentBookedToast() {
        String message = "Your appointment has been booked successfully!";
        Toast.makeText(BookAppointmnet.this, message, Toast.LENGTH_SHORT).show();
    }
    private void navigateToHomeActivityAfterDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(BookAppointmnet.this, FindDoctor.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional, to clear the activity stack
                startActivity(homeIntent);
            }
        }, 3000); // Delay in milliseconds (e.g., 3000 milliseconds = 3 seconds)
    }
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                i1=i1+1;
                datebutton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }
    private void initTimePicker()
    {
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timebutton.setText(i+":"+i1);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}