package com.app.diagnosis.diagnosis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void diagnosis(View view) {
        Intent intent = new Intent(this,diagnosis.class);
        startActivity(intent);
    }

    public void explain(View view) {
        Intent intent = new Intent(this,Explaination.class);
        startActivity(intent);
    }

    public void comm(View view) {
        Intent intent = new Intent(this,Communication.class);
        startActivity(intent);
    }

    public void assign(View view) {
        Intent intent = new Intent(this,Assign.class);
        startActivity(intent);
    }

    public void provide(View view) {
        Intent intent = new Intent(this,previous.class);
        startActivity(intent);
    }

    public void alert(View view) {
        Intent intent = new Intent(this,alert.class);
        startActivity(intent);
    }
}
