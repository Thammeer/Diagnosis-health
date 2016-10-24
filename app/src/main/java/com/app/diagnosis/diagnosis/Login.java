package com.app.diagnosis.diagnosis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public class Login extends AppCompatActivity {
EditText email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);



    }

    Retrofit registration = new Retrofit.Builder().
            baseUrl("http://diag.esy.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void sign(View view) {
        if(email.getText().toString().equals("")){
            Toast.makeText(this,"please enter your email",Toast.LENGTH_SHORT).show();
        }else if(pass.getText().toString().equals("")){
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_SHORT).show();
        }else
        {
            String email2 = email.getText().toString();
            String pass2 = pass.getText().toString();

            Registration apiService = registration.create(Registration.class);
            Call<Results> reg = apiService.postRegestraion(email2,pass2);


            reg.enqueue(new Callback<Results>() {

                @Override
                public void onResponse(Response<Results> response, Retrofit retrofit) {

                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    if (response.body().getMessage().equals("0")) {

                        Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();

                    } else{

                        SharedPreferences sharedPreferencesed=getApplicationContext().getSharedPreferences("id", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferencesed.edit();
                        editor.putString("id",response.body().getMessage());
                        editor.commit();
                        Intent intent = new Intent(Login.this,Home.class);
                        startActivity(intent);

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
        @POST("login.php")
        Call<Results> postRegestraion(@Field("pass") String pass2,
                                      @Field("email") String email2

        );
    }
}
