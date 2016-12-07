package com.app.diagnosis.diagnosis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class Home extends AppCompatActivity {
    String id,name;
    int PLACE_PICKER_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferencesed=getApplicationContext().getSharedPreferences("id", Context.MODE_PRIVATE);
         id =sharedPreferencesed.getString("id", "");
        name=sharedPreferencesed.getString("name", "");


    }

    public void diagnosis(View view) {
        Intent intent = new Intent(this,diagnosis.class);
        intent.putExtra("id",id);
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
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void alert(View view) {
        Intent intent = new Intent(this,alert.class);
        startActivity(intent);
    }

    public void map(View view) {


        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }


    }

    public void advices(View view) {

        Intent intent = new Intent(this,Medical_advices.class);
        startActivity(intent);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);


//https://developers.google.com/maps/documentation/android-api/intents


                Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude+"?q=Hospital");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);


            }
        }
    }



}
