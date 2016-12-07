package com.app.diagnosis.diagnosis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dr_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr__home);

    }

    public void comm(View view) {
        Intent intent = new Intent(this,ChatRoom.class);
        startActivity(intent);
    }


    public void alert(View view) {
        Intent intent = new Intent(this,alert.class);
        startActivity(intent);
    }


    public void appointments(View view) {
        Intent intent = new Intent(this, Appointments.class);
        startActivity(intent);
    }
}
