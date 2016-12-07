package com.app.diagnosis.diagnosis;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public class diagnosis extends AppCompatActivity {
    ProgressDialog PD;
CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10,ch11,ch12,ch13,ch14,ch15,ch16,ch17,ch18,ch19;
    String diagnosis="You have got: \n\n";
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);


        final Intent intent = getIntent();
         id = intent.getStringExtra("id");


        ch1=(CheckBox)findViewById(R.id.checkBox1);
        ch2=(CheckBox)findViewById(R.id.checkBox2);
        ch3=(CheckBox)findViewById(R.id.checkBox3);
        ch4=(CheckBox)findViewById(R.id.checkBox4);
        ch5=(CheckBox)findViewById(R.id.checkBox5);
        ch6=(CheckBox)findViewById(R.id.checkBox6);
        ch7=(CheckBox)findViewById(R.id.checkBox7);
        ch8=(CheckBox)findViewById(R.id.checkBox8);
        ch9=(CheckBox)findViewById(R.id.checkBox9);
        ch10=(CheckBox)findViewById(R.id.checkBox10);
        ch11=(CheckBox)findViewById(R.id.checkBox11);
        ch12=(CheckBox)findViewById(R.id.checkBox12);
        ch13=(CheckBox)findViewById(R.id.checkBox13);
        ch14=(CheckBox)findViewById(R.id.checkBox14);
        ch15=(CheckBox)findViewById(R.id.checkBox15);
        ch16=(CheckBox)findViewById(R.id.checkBox16);
        ch17=(CheckBox)findViewById(R.id.checkBox17);
        ch18=(CheckBox)findViewById(R.id.checkBox18);
        ch19=(CheckBox)findViewById(R.id.checkBox19);






    }

    Retrofit registration = new Retrofit.Builder().
            baseUrl("http://diag.esy.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void send(View view) {

        if(ch1.isChecked() || ch2.isChecked()){
            diagnosis = diagnosis.concat("* Headache \n");
        }

        if(ch3.isChecked() || ch6.isChecked() || ch14.isChecked()){
            diagnosis = diagnosis.concat("* Cold \n");
        }

        if(ch4.isChecked()){
            diagnosis =diagnosis.concat("* Coughing with sputum \n");
        }

        if(ch5.isChecked()){
            diagnosis =  diagnosis.concat("* Dry coughing \n");
        }

        if(ch7.isChecked() || ch12.isChecked() || ch19.isChecked()){
            diagnosis =  diagnosis.concat("* Constipation \n");
        }

        if(ch8.isChecked() || ch9.isChecked() || ch15.isChecked()){
            diagnosis = diagnosis.concat("* High Temperature \n");
        }

        if(ch10.isChecked() || ch11.isChecked() || ch18.isChecked()){
            diagnosis =diagnosis.concat("* Nausea \n");
        }

        if(ch13.isChecked() || ch17.isChecked()){
            diagnosis =diagnosis.concat("* Acidity \n");
        }

        if(ch16.isChecked()){
            diagnosis =  diagnosis.concat("* Shortness of Breath \n");
        }





        Registration apiService = registration.create(Registration.class);
        Call<Results> reg = apiService.postRegestraion(id,diagnosis);

        reg.enqueue(new Callback<Results>() {

            @Override
            public void onResponse(Response<Results> response, Retrofit retrofit) {


                if (response.body().getMessage().equals("1")) {


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(diagnosis.this);

// set title
                    alertDialogBuilder.setTitle("Your Diagnosis");

// set dialog message
                    alertDialogBuilder
                            .setMessage(diagnosis)
                            .setCancelable(false)
                            .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
// if this button is clicked, close
// current activity
                                    diagnosis = "You have got \n\n";
                                    dialog.cancel();
                                }
                            });

// create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

// show it
                    alertDialog.show();



                    PD.dismiss();




                } else{

                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    PD.dismiss();

                }


            }

            @Override
            public void onFailure(Throwable t) {
                PD.dismiss();
                Toast.makeText(getApplicationContext(), "connection error", Toast.LENGTH_LONG).show();


            }
        });


}

    public interface Registration {
        @FormUrlEncoded
        @POST("diagnosis.php")
        Call<Results> postRegestraion(@Field("id") String id,
                                      @Field("diagnosis") String diagnosis

        );
    }


    }


