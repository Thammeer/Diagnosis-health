package com.app.diagnosis.diagnosis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText email,pass,mobile,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=(EditText)findViewById(R.id.email2);
        pass=(EditText)findViewById(R.id.pass2);
        mobile=(EditText)findViewById(R.id.mobile);
        age=(EditText)findViewById(R.id.age);


    }

    public void register(View view) {
        if(email.getText().toString().equals("")){
            Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show();
        }else if(pass.getText().toString().equals("")){
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_SHORT).show();
        }
        else if(mobile.getText().toString().equals("")){
            Toast.makeText(this,"Please enter your mobile",Toast.LENGTH_SHORT).show();
        }else if(age.getText().toString().equals("")){
            Toast.makeText(this,"Please enter your age",Toast.LENGTH_SHORT).show();
        }else
        {

        }


    }
}
