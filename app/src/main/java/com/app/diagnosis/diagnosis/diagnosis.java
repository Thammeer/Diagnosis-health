package com.app.diagnosis.diagnosis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class diagnosis extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioButton radioButton1;
    int selectedId1;
    String question1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        radioGroup1 = (RadioGroup) findViewById(R.id.radio1);

        final Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();



    }

    public void send(View view) {
        selectedId1 = radioGroup1.getCheckedRadioButtonId();
        radioButton1 = (RadioButton) findViewById(selectedId1);
         question1=radioButton1.getText().toString();


        Toast.makeText(this, question1, Toast.LENGTH_SHORT).show();




    }
}
