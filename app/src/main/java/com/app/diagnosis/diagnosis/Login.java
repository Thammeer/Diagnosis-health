package com.app.diagnosis.diagnosis;

import android.app.ProgressDialog;
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
    ProgressDialog PD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);


    }

    Retrofit login = new Retrofit.Builder().
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
            PD.show();
            String email2 = email.getText().toString();
            String pass2 = pass.getText().toString();

            Login_ apiService = login.create(Login_.class);
            Call<Results> reg = apiService.postLogin(email2,pass2);


            reg.enqueue(new Callback<Results>() {

                @Override
                public void onResponse(Response<Results> response, Retrofit retrofit) {


                    if (response.body().getMessage().equals("0")) {

                        Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
                        PD.dismiss();

                    } else{

                        PD.dismiss();

                        SharedPreferences sharedPreferencesed=getApplicationContext().getSharedPreferences("id", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferencesed.edit();
                        editor.putString("id",response.body().getMessage());
                        editor.putString("name",response.body().getname());
                        editor.commit();

                        if(response.body().getname().equals("Dr.Ayman Al-harbi")||response.body().getname().equals("Dr.Yasser Khoujah")||response.body().getname().equals("Dr.Mohammed Al-harthi")){
                            Intent intent = new Intent(Login.this,Dr_Home.class);
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(Login.this,Home.class);
                            startActivity(intent);
                        }


                    }


                }

                @Override
                public void onFailure(Throwable t) {
                    PD.dismiss();


                }
            });



        }


    }

    public interface Login_ {
        @FormUrlEncoded
        @POST("login.php")
        Call<Results> postLogin(@Field("email") String email2,
                                      @Field("pass") String pass2


        );
    }
}
