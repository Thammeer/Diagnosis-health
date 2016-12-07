package com.app.diagnosis.diagnosis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public class Appointments extends AppCompatActivity {
    ArrayList<HashMap<String, String>> appointment;
    ProgressDialog PD;
    SimpleAdapter adapter;
    ListView myList;
    public static final String _ID = "id";
    public static final String _dr_name = "dr_name";
    public static final String _patient_name= "patient_name";
    public static final String _date = "date";
    public static final String _TIME = "a_time";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        appointment= new ArrayList<HashMap<String, String>>();

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);


        myList = (ListView) findViewById(R.id.listView);


        ReadDataFromDB();




    }
    Retrofit appointments = new Retrofit.Builder().
            baseUrl("http://diag.esy.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    private void ReadDataFromDB() {
        PD.show();
        SharedPreferences sharedPreferencesed=getApplicationContext().getSharedPreferences("id", Context.MODE_PRIVATE);
        String name=sharedPreferencesed.getString("name", "");

        Rppointments apiService = appointments.create(Rppointments.class);
        Call<Results> reg = apiService.getRppointments(name);


        reg.enqueue(new Callback<Results>() {

            @Override
            public void onResponse(Response<Results> response, Retrofit retrofit) {


                if (response.body().getMessage().equals("0")) {

                    Toast.makeText(getApplicationContext(), "No Appointments", Toast.LENGTH_LONG).show();
                    PD.dismiss();



                } else {

                    try {

                        List<json> info = response.body().getAppointments();
                        for (int i = 0; i < info.size(); i++) {

                            HashMap<String, String> data = new HashMap<String, String>();
                            data.put(_ID, info.get(i).getId());
                            data.put(_dr_name,info.get(i).getDr_name());
                            data.put(_patient_name,info.get(i).getPatient_name());
                            data.put(_date, info.get(i).getDate());
                            data.put(_TIME,info.get(i).getA_time());




                            appointment.add(data);

                        } // for loop ends






                   String [] fromFieldNames = new String[] { _patient_name , _date, _TIME };
                    int [] toViewIDs = new int [] { R.id.patient_name, R.id.date,R.id.a_time};

                    adapter = new SimpleAdapter(getApplicationContext(), appointment,R.layout.dr_appointments, fromFieldNames, toViewIDs);

                    myList.setAdapter(adapter);
                    PD.dismiss();

                }
                    catch (JsonSyntaxException e)
                    {
                        Toast.makeText(getApplicationContext(), "Exception "+e, Toast.LENGTH_LONG).show();
                        PD.dismiss();
                    }
                    catch (JsonIOException e)
                    {
                        Toast.makeText(getApplicationContext(), "Exception "+e, Toast.LENGTH_LONG).show();
                        PD.dismiss();
                    }

                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Exception"+e, Toast.LENGTH_LONG).show();
                        PD.dismiss();
                    }


                }


            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "connection error", Toast.LENGTH_LONG).show();
                PD.dismiss();

            }
        });



    }


    public interface Rppointments {
        @FormUrlEncoded
        @POST("dr_appointments.php")
        Call<Results> getRppointments(@Field("dr_name") String dr_name
        );

    }
}
