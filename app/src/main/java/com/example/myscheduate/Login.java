package com.example.myscheduate;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

public class Login extends AppCompatActivity {


    TextView BTRegis;
    MaterialButton BTlogin;
    EditText ETemail, ETpassword;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    String email, password;
//    int id_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        BTlogin = findViewById(R.id.L_BTN);
        ETemail = findViewById(R.id.L_ETemail);
        ETpassword = findViewById(R.id.L_ETpassword);
        BTRegis = findViewById(R.id.L_TVregis);

        progressDialog = new ProgressDialog(this);

        sessionManager = new SessionManager(getApplicationContext());



        BTRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });


        BTlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(getApplicationContext(),"Berhasil Login",Toast.LENGTH_LONG).show();
                //    startActivity(new Intent(getApplicationContext(), MainFragment.class));
                progressDialog.setMessage("Login..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                email = ETemail.getText().toString();
                password = ETpassword.getText().toString();

                validasiData();
            }
        });

    }

    void validasiData(){
        if(email.equals("") || password.equals("")){
            Toast.makeText(Login.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            cekLogin();
        }
    }

    void cekLogin(){
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_6/api_register_scheduate/cekLogin.php")
                .addBodyParameter("email",""+email)
                .addBodyParameter("password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("Cek Login")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("Cek Login",""+response);

                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan   = response.getString("result");
                            String nama_lengkap = response.getString("nama_lengkap");
                            String email = response.getString("email");
                            String id_user = response.getString("id_user");
                            String no_hp = response.getString("no_hp");
//                          int id_user = response.getInt("id_user");
                            Toast.makeText(Login.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(Login.this)
                                        .setMessage("Berhasil Login")
                                        .setCancelable(false)
                                        .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                sessionManager.createSession_nama(nama_lengkap);
                                                sessionManager.createSession_email(email);
                                                sessionManager.createSession_id(id_user);

                                                Intent intent = new Intent(Login.this, Dashboard.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();
                            }
                            else {
                                new AlertDialog.Builder(Login.this)
                                        .setMessage("Gagal Melakukan Login !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                Intent intent = new Intent(Login.this, Login.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorLogin",""+anError.getErrorBody());
                    }
                });

    }
}