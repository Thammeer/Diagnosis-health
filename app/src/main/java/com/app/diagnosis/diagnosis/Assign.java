package com.app.diagnosis.diagnosis;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.chrono.IslamicChronology;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public class Assign extends AppCompatActivity {
    Button date,time;
    int hour, minute;
    Spinner names;
    String [] dr;
    String dr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        date=(Button)findViewById(R.id.date1);
        time=(Button)findViewById(R.id.time1);
        names=(Spinner)findViewById(R.id.spinner);

        dr=getResources().getStringArray(R.array.names);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, dr);

        names.setAdapter(adapter);
        names.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3)
            {
                int index = arg0.getSelectedItemPosition();

                if(dr[index].equals("Dr.Ayman Al-harbi")){
                    dr1="Dr.Ayman Al-harbi";
                }
                else if(dr[index].equals("Dr.Yasser Khoujah")){
                    dr1="Dr.Yasser Khoujah";
                }
                else if(dr[index].equals("Dr.Mohammed Al-harthi")){
                    dr1="Dr.Mohammed Al-harthi";
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });





    }



    public void date(View view) {

        Calendar C=Calendar.getInstance();
        int year =C.get(Calendar.YEAR);
        int month =C.get(Calendar.MONTH);
        int day =C.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepicker =new DatePickerDialog(Assign.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                DateTime dateM = new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0, 0);

              int days = Days.daysBetween(new LocalDate(DateTime.now()), new LocalDate(dateM)).getDays();

                if(days==0){
                    Toast.makeText(Assign.this, "Check your Date", Toast.LENGTH_SHORT).show();
                }
                else if(days<0){
                    Toast.makeText(Assign.this, "Check your Date", Toast.LENGTH_SHORT).show();

                }else {
                    date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                }




            }
        },year,month,day);
        datepicker.setTitle("Select Date");
        datepicker.show();
    }

    public void time(View view) {

        Calendar mcurrentTime = Calendar.getInstance();
         hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
         minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Assign.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if(selectedMinute<10)
                time.setText( selectedHour + ":0" + selectedMinute);
                else
                    time.setText( selectedHour + ":" + selectedMinute);
                hour=selectedHour;
                minute=selectedMinute;

                if((hour>9&&hour<15)||(hour>17&&hour<23))
                    if(hour==14 || hour ==22)
                        if(minute == 0)
                    Toast.makeText(Assign.this,hour+"",Toast.LENGTH_SHORT).show();



                else {
                            Toast.makeText(Assign.this, "Check your time", Toast.LENGTH_SHORT).show();
                            time.setText("Set Time");

                        }



            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }


    Retrofit appointments = new Retrofit.Builder().
            baseUrl("http://diag.esy.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void submit2(View view) {
        if(date.getText().toString().equals("Set date")){
            Toast.makeText(this, "please enter your date", Toast.LENGTH_SHORT).show();
        }else if(time.getText().toString().equals("Set Time")){
            Toast.makeText(this,"Please enter your time",Toast.LENGTH_SHORT).show();
        }
       else
        {
            SharedPreferences sharedPreferencesed=getApplicationContext().getSharedPreferences("id", Context.MODE_PRIVATE);
              String name=sharedPreferencesed.getString("name", "");

            Rppointments apiService = appointments.create(Rppointments.class);
            Call<Results> reg = apiService.postRppointments(dr1,name,date.getText().toString(),time.getText().toString());


            reg.enqueue(new Callback<Results>() {

                @Override
                public void onResponse(Response<Results> response, Retrofit retrofit) {


                    if (response.body().getMessage().equals("1")) {

                        Toast.makeText(getApplicationContext(), "Your information inserted successfully.", Toast.LENGTH_LONG).show();

                        finish();



                    } else{
                        Toast.makeText(getApplicationContext(), "Please choose another time ", Toast.LENGTH_LONG).show();


                    }


                }

                @Override
                public void onFailure(Throwable t) {

                    Toast.makeText(getApplicationContext(), "connection error", Toast.LENGTH_LONG).show();

                }
            });




        }


    }

    public interface Rppointments {
        @FormUrlEncoded
        @POST("appointments.php")
        Call<Results> postRppointments(@Field("dr_name") String dr_name,
                                      @Field("patient_name") String patient_name,
                                      @Field("date") String date,
                                      @Field("a_time") String a_time

        );
    }



}
