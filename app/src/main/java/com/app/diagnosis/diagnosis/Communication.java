package com.app.diagnosis.diagnosis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Communication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
    }

    public void phone(View view) {
        Intent T = new Intent(Intent.ACTION_DIAL);
        T.setData(Uri.parse("tel:991"));
        startActivity(T);


    }

    public void chat(View view) {
        Intent intent = new Intent(this,ChatRoom.class);
        startActivity(intent);

    }
}
