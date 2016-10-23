package com.app.diagnosis.diagnosis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);



    }

    public void sign(View view) {
        if(email.getText().toString().equals("")){
            Toast.makeText(this,"please enter your email",Toast.LENGTH_SHORT).show();
        }else if(pass.getText().toString().equals("")){
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_SHORT).show();
        }else
        {

        }


    }
}
