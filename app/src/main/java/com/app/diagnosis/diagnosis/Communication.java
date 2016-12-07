package com.app.diagnosis.diagnosis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Communication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        Button btn = (Button) findViewById(R.id.button4);
        registerForContextMenu(btn);

    }



    public void chat(View view) {
        Intent intent = new Intent(this,ChatRoom.class);
        startActivity(intent);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose a doctor");
        menu.add(0, v.getId(), 0, "Dr.Ayman Al-harbi");
        menu.add(0, v.getId(), 0, "Dr.Yasser Khoujah");
        menu.add(0, v.getId(), 0, "Dr.Mohammed Al-harthi");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Dr.Ayman Al-harbi") {
            Intent T = new Intent(Intent.ACTION_DIAL);
            T.setData(Uri.parse("tel:0542221196"));
            startActivity(T);

        } else if (item.getTitle() == "Dr.Yasser Khoujah") {

            Intent T = new Intent(Intent.ACTION_DIAL);
            T.setData(Uri.parse("tel:0504732763"));
            startActivity(T);

        } else if (item.getTitle() == "Dr.Mohammed Al-harthi") {

            Intent T = new Intent(Intent.ACTION_DIAL);
            T.setData(Uri.parse("tel:0570353419"));
            startActivity(T);


        } else {
            return false;
        }
        return true;
    }

    public void phone(View view) {

        Toast.makeText(this,"please, hold the button",Toast.LENGTH_LONG).show();
    }
}