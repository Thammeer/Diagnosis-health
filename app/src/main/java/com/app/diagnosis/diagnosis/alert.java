package com.app.diagnosis.diagnosis;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class alert extends AppCompatActivity {
    SampleAlarmReceiver alarm = new SampleAlarmReceiver();
    Button date,time;
    Date date2,date3;
    EditText name,desc;
    int cHour,cMinute;
    final Calendar cal = Calendar.getInstance();
    int cYear = cal.get(Calendar.YEAR);
    int cMonth = cal.get(Calendar.MONTH);
    int cDay = cal.get(Calendar.DAY_OF_MONTH);
    int id;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int selecte;
    String repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        date=(Button)findViewById(R.id.date2);
         time=(Button)findViewById(R.id.time2);
        name=(EditText)findViewById(R.id.name2);
        desc=(EditText)findViewById(R.id.desc);

        SharedPreferences sharedPreferencesed=getApplicationContext().getSharedPreferences("alert", Context.MODE_PRIVATE);
        id =sharedPreferencesed.getInt("id", 0)+1;

        radioGroup = (RadioGroup) findViewById(R.id.repeat);



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
                cHour = selectedHour;
                cMinute = selectedMinute;
            }
        }, hour, minute, true);
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
                cYear= year;
                cMonth=monthOfYear;
                cDay=dayOfMonth;





            }
        },year,month,day);
        datepicker.setTitle("Select Date");
        datepicker.show();
    }


    public void submit(View view) {

        selecte = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selecte);
        repeat=radioButton.getText().toString();

         if(name.getText().toString().equals("")){
            Toast.makeText(this,"Please enter the notification's name",Toast.LENGTH_SHORT).show();
        }
         else if(date.getText().toString().equals("Date")){
            Toast.makeText(this, "please enter your date", Toast.LENGTH_SHORT).show();
        }else if(time.getText().toString().equals("Time")){
            Toast.makeText(this,"Please enter your time",Toast.LENGTH_SHORT).show();
        }else if(desc.getText().toString().equals("")){
             Toast.makeText(this,"Please enter your description",Toast.LENGTH_SHORT).show();
         }
        else
        {

            String dateString = cDay+"/"+cMonth+"/"+cYear;
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date2 = format.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateNow = cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR);
            try {
                date3 = format.parse(dateNow);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(date2.before(date3)){
                Toast.makeText(alert.this, "Please, check your date date",Toast.LENGTH_LONG).show();
            }

            else {

                SharedPreferences sharedPreferencesed=getApplicationContext().getSharedPreferences("alert", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferencesed.edit();
                editor.putInt("id",id);
                editor.commit();

                Intent intent = new Intent(alert.this, SampleAlarmReceiver.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("id", id);
                intent.putExtra("day", cDay);
                intent.putExtra("month", cMonth);
                intent.putExtra("year", cYear);
                intent.putExtra("hour", cHour);
                intent.putExtra("minute", cMinute);
                intent.putExtra("note", desc.getText().toString());
                intent.putExtra("choice",repeat);
                alarm.setAlarm(alert.this, intent);
                Toast.makeText(alert.this, name.getText().toString() + " has been added", Toast.LENGTH_LONG).show();
                finish();

            }


        }
    }
}
