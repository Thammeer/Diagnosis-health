package com.app.diagnosis.diagnosis;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Assign extends AppCompatActivity {
    Button date,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        date=(Button)findViewById(R.id.date1);
        time=(Button)findViewById(R.id.time1);
    }

    public void date(View view) {

        Calendar C=Calendar.getInstance();
        int year =C.get(Calendar.YEAR);
        int month =C.get(Calendar.MONTH);
        int day =C.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepicker =new DatePickerDialog(Assign.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);




            }
        },year,month,day);
        datepicker.setTitle("Select Date");
        datepicker.show();
    }

    public void time(View view) {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Assign.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                time.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    public void submit2(View view) {
        if(date.getText().toString().equals("Set date")){
            Toast.makeText(this, "please enter your date", Toast.LENGTH_SHORT).show();
        }else if(time.getText().toString().equals("Set Time")){
            Toast.makeText(this,"Please enter your time",Toast.LENGTH_SHORT).show();
        }
       else
        {

        }


    }
}
