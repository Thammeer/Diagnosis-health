package com.app.diagnosis.diagnosis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Medical_advices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_advices);


        ListView listView=(ListView)findViewById(R.id.list);


        String[] values = new String[] {
                "* Keep medicines at the recommended temperature, and avoid placing them in a hot spot.",
                "* Always keep medicines in a safe place and out of the reach of children.",
                "* Drink sufficient water every day, at least 1.5 to 2 liters, which are equal to eight large glasses of water.",
                "* Wash your hands well or use the hand sterilizer before and after eating, as well as before taking your medicines.",
                "* Stay away from smoking.",
                "* Play sport to keep your body healthy.",
                "* Never take your medicines with coffee, because of its impact on the effectiveness of medicines.",
                "* Do not miss any main meal.",
                "* Stay away from fast and junk food, due to its bad impact on your body.",
                "* Sleep long enough, the average sleeping hours for a normal person is between 7 to 9 hours.",
                "* Do not drink tea after having the meal for at least 45 minutes, because it loses the body its ability to absorb Iron and Proteins.",
                "* Don't use a large amount of salt in food."
        };



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);



        listView.setAdapter(adapter);
    }
}
