package com.example.myscheduate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    Button IconBack, editProfile;
    SessionManager sessionManager;
    TextView TVnama, TVemail, TVtelpon, hynama;
    String nama_lengkap, email, no_hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        TVnama = findViewById(R.id.P_nama);
        TVemail = findViewById(R.id.P_email);
        TVtelpon = findViewById(R.id.P_hp);
        hynama = findViewById(R.id.hynama);
        editProfile = findViewById(R.id.ubah);

        IconBack = findViewById(R.id.IconBackProfil);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        nama_lengkap = user.get(SessionManager.KEY_NAMALENGKAP);
        email = user.get(SessionManager.KEY_EMAIL);
        no_hp = user.get(SessionManager.KEY_HP);



        hynama.setText("Hai, "+nama_lengkap);
        TVnama.setText(nama_lengkap);
        TVemail.setText(email);
        TVtelpon.setText(no_hp);


//      Button edit profile
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,EditProfile.class));
            }
        });

        IconBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

            }
        });


    }
}