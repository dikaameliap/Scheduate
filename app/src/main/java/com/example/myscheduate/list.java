package com.example.myscheduate;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;



public class list extends AppCompatActivity {

    SwipeRefreshLayout srl_main;
    ArrayList<String> array_nama_mahasiswa,array_nim,array_judul,array_foto;
    ProgressDialog progressDialog;
    ListView listProses;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //set variable sesuai dengan widget yang digunakan
        listProses = findViewById(R.id.LV);
        srl_main    = findViewById(R.id.swipe_container);
        progressDialog = new ProgressDialog(this);

        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main.setRefreshing(false);
            }
        });
        // Scheme colors for animation
        srl_main.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)

        );

        scrollRefresh();
    }

    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        },2000);
    }

    void initializeArray(){

        array_nama_mahasiswa = new ArrayList<String>();
        array_nim         = new ArrayList<String>();
        array_judul     = new ArrayList<String>();
        array_foto  = new ArrayList<String>();
//        array_ruangan  = new ArrayList<String>();
//        array_jam  = new ArrayList<String>();


        //clear ini untuk memastikan data empty

        array_nama_mahasiswa.clear();
        array_nim.clear();
        array_judul.clear();
        array_foto.clear();
//        array_ruangan.clear();
//        array_jam.clear();

    }

    public void getData(){
        initializeArray();
        //URL API
        AndroidNetworking.get("https://tkjbpnup.com/kelompok_6/api_register_scheduate/getdata.php")
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try{
                            Boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);


                                    array_nama_mahasiswa.add(jo.getString("nama_mahasiswa"));
                                    array_nim.add(jo.getString("nim"));
                                    array_judul.add(jo.getString("judul"));
//                                    array_ruangan.add(jo.getString("ruang_sidang"));
//                                    array_jam.add(jo.getString("jam_sidang"));

                                    array_foto.add(jo.getString("foto"));

                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_DataUser adapter = new CLV_DataUser(list.this,array_nama_mahasiswa,array_nim, array_judul, array_foto);

                                //Set adapter to list
                                listProses.setAdapter(adapter);

                                //edit and delete
                            }else{
                                Toast.makeText(list.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

                            }


                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }


                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_add){
            Intent i = new Intent(list.this,Dashboard.class);
            startActivityForResult(i,1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                scrollRefresh();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode==2){
            if(resultCode==RESULT_OK){
                scrollRefresh();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
