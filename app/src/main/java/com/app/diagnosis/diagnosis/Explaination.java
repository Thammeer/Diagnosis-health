package com.app.diagnosis.diagnosis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Explaination extends AppCompatActivity {
    EditText explain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explaination);

        explain=(EditText)findViewById(R.id.explaination);


    }

    public void submit(View view) {




    }
}
