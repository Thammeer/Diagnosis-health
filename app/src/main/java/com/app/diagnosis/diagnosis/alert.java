package com.app.diagnosis.diagnosis;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class alert extends AppCompatActivity {

    Button date,time;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        date=(Button)findViewById(R.id.date2);
         time=(Button)findViewById(R.id.time2);
        name=(EditText)findViewById(R.id.name2);
    }

    public void time(View view) {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(alert.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                time.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }



    public void date(View view) {

         Calendar C=Calendar.getInstance();
        int year =C.get(Calendar.YEAR);
         int month =C.get(Calendar.MONTH);
         int day =C.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepicker =new DatePickerDialog(alert.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

              date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);




            }
        },year,month,day);
        datepicker.setTitle("Select Date");
        datepicker.show();
    }


    public void submit(View view) {
         if(name.getText().toString().equals("")){
            Toast.makeText(this,"Please enter the notification's name",Toast.LENGTH_SHORT).show();
        }
         else if(date.getText().toString().equals("Date")){
            Toast.makeText(this, "please enter your date", Toast.LENGTH_SHORT).show();
        }else if(time.getText().toString().equals("Time")){
            Toast.makeText(this,"Please enter your time",Toast.LENGTH_SHORT).show();
        }
        else
        {

        }
    }
}
