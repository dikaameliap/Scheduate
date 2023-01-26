package com.example.myscheduate;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CLV_DataUser extends ArrayAdapter<String> {
    //Declarasi variable
    private final Activity context;
    private ArrayList<String> vnama_mahasiswa;
    private ArrayList<String> vnim;
    private ArrayList<String> vjudul;
    private ArrayList<String> vfoto;
//    private ArrayList<String> vruangan;
//    private ArrayList<String> vjam;

    //Choose Delete

    public CLV_DataUser(Activity context, ArrayList<String> nama_mahasiswa, ArrayList<String> nim,ArrayList<String> judul, ArrayList<String> foto)
    {
        super(context, R.layout.list_item,nama_mahasiswa);
        this.context    = context;
        this.vnama_mahasiswa     = nama_mahasiswa;
        this.vnim    = nim;
        this.vjudul    = judul;
        this.vfoto     = foto;
//        this.vruangan  = ruangan;
//        this.vjam    = jam;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item, null, true);

        //Declarasi komponen
        TextView nama  = rowView.findViewById(R.id.idTXTName);
        TextView nim          = rowView.findViewById(R.id.idTXTNim);
//        TextView jam = rowView.findViewById(R.id.TV_jam);
//        TextView ruangan = rowView.findViewById(R.id.TV_ruangan);
        ImageView foto     = rowView.findViewById(R.id.idIVGambar);

        //Set Parameter Value sesuai widget textview
        nama.setText(vnama_mahasiswa.get(position));
        nim.setText(vnim.get(position));
//        jam.setText(vjam.get(position));
//        ruangan.setText(vruangan.get(position));



        if (vfoto.get(position).equals(""))
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_6/Image/noimage.png").into(foto);
            //Picasso.get().load("http://tekajeapunya.com/kelompok_11/image/noimage.png").into(photo);
        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_6/api_register_scheduate/image/"+vfoto.get(position)).into(foto);
            //Picasso.get().load("http://tekajeapunya.com/kelompok_11/image/"+vPhoto.get(position)).into(photo);
        }

        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.down_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }


}

