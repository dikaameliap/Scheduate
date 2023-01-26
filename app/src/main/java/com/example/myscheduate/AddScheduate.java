package com.example.myscheduate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;



public class AddScheduate extends AppCompatActivity {

    //Deklarasi
    ImageView back, image;
    EditText ETnama, ETnim, ETjudul;
    Button submit;
    String Nama_Mahasiswa, Nim, Judul_Skripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scheduate);

        back = findViewById(R.id.idIconBack);
        image = findViewById(R.id.idImage);
        ETnama = findViewById(R.id.inputNama);
        ETnim = findViewById(R.id.inputNim);
        ETjudul = findViewById(R.id.inputJudul);
        submit = findViewById(R.id.btnSubmit);

    }



}