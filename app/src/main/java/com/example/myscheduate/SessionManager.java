package com.example.myscheduate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "session";
    private static final String is_login = "islogin";
    public static final String KEY_NAMALENGKAP = "nama_lengkap";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ID = "id_users";
    public static final String KEY_HP = "hp";


    public SessionManager (Context context){
        this.context = context;
        //pref = context.getActivity().getSharedPreferences(pref_name, mode);
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void createSession_id(String id_users){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_ID, id_users);
        editor.apply();

    }

    public void createSession_nik(String hp){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_HP, hp);
        editor.apply();

    }

    public void createSession_nama(String nama){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_NAMALENGKAP, nama);
        editor.apply();
    }

    public void createSession_email(String email){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public void logOut(){
        editor.clear();
        editor.apply();
        Intent i = new Intent(context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(pref_name, pref.getString(pref_name, null));
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_NAMALENGKAP, pref.getString(KEY_NAMALENGKAP, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_HP, pref.getString(KEY_HP, null));
        return user;
    }

    public boolean getSPSudahLogin(){
        return pref.getBoolean(is_login, false);
    }

}