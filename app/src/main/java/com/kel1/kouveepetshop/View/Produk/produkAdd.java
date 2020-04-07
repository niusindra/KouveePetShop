package com.kel1.kouveepetshop.View.Produk;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.SessionManager;

public class produkAdd extends AppCompatActivity {
    //aaa
    public ImageView back;
    public EditText date;
    public EditText nama;
    public EditText alamat;
    public EditText telp;
    public Button addBtn;
    public SessionManager session;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produk_add);
    }
}

