package com.app.diagnosis.diagnosis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void register(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

    }

    public void exit(View view) {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
       // finish();
    }
}
