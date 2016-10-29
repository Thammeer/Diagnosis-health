package com.app.diagnosis.diagnosis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Register extends AppCompatActivity {
    EditText email, pass, mobile, age, name;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email2);
        pass = (EditText) findViewById(R.id.pass2);
        mobile = (EditText) findViewById(R.id.mobile);
        age = (EditText) findViewById(R.id.age);
        name = (EditText) findViewById(R.id.name);
        radioGroup = (RadioGroup) findViewById(R.id.radio);


    }

    Retrofit registration = new Retrofit.Builder().
            baseUrl("http://diag.esy.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void register(View view) {

        String email2 = email.getText().toString();
        String name2 = name.getText().toString();
        String pass2 = pass.getText().toString();
        String mobile2 = mobile.getText().toString();
        String age2 = age.getText().toString();

        if (email2.equals("")) {
            Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show();
        } else if (pass2.equals("")) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        } else if (name2.equals("")) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
        } else if (mobile2.equals("")) {
            Toast.makeText(this, "Please enter your mobile", Toast.LENGTH_SHORT).show();
        } else if (age2.equals("")) {
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show();
        } else {



            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);


            String gender2=radioButton.getText().toString();

            Registration apiService = registration.create(Registration.class);
            Call<Results> reg = apiService.postRegestraion(name2,email2,pass2,age2,mobile2,gender2);


            reg.enqueue(new Callback<Results>() {

                @Override
                public void onResponse(Response<Results> response, Retrofit retrofit) {


                    if (response.body().getMessage().equals("1")) {

                        Toast.makeText(getApplicationContext(), "Your information inserted successfully.", Toast.LENGTH_LONG).show();

                        finish();



                    } else if(response.body().getMessage().equals("0")){

                        Toast.makeText(getApplicationContext(), "connection error", Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }


                }

                @Override
                public void onFailure(Throwable t) {


                }
            });


        }


    }

    public interface Registration {
        @FormUrlEncoded
        @POST("user.php")
        Call<Results> postRegestraion(@Field("name") String name2,
                                      @Field("email") String email2,
                                      @Field("pass") String pass2,
                                      @Field("age") String age2,
                                      @Field("mobile") String mobile2,
                                      @Field("gender") String gender2
        );
    }


}