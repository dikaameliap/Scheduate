package com.example.myscheduate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity {

    SessionManager sessionManager;
    TextView TVnama;
    private CardView btn1, btn2, btn3, btn4;
    String nama_lengkap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        TVnama = findViewById(R.id.T_nama);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        nama_lengkap = user.get(SessionManager.KEY_NAMALENGKAP);

        TVnama.setText(nama_lengkap);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), list.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"list Scheduate", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Progres.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Progress", Toast.LENGTH_SHORT).show();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Done.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Done", Toast.LENGTH_SHORT).show();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), About.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"About App", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.action_menu, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.menu_profile:
                Intent intent = new Intent(Dashboard.this, Profile.class);
                startActivity(intent);
                Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting:
                Intent intent1 = new Intent(Dashboard.this, Setting.class);
                startActivity(intent1);
                Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_logout:
                sessionManager.logOut();


        }
        return super.onOptionsItemSelected(item);
    }
}




