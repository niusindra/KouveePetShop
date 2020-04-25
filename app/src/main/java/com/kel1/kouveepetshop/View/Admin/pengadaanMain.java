package com.kel1.kouveepetshop.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.Customer.customerMain;
import com.kel1.kouveepetshop.View.Hewan.hewanMain;
import com.kel1.kouveepetshop.View.JenisHewan.jenisHewanMain;
import com.kel1.kouveepetshop.View.Layanan.layananMain;
import com.kel1.kouveepetshop.View.Produk.produkMain;
import com.kel1.kouveepetshop.View.Supplier.supplierMain;
import com.kel1.kouveepetshop.View.UkuranHewan.ukuranMain;

public class pengadaanMain extends AppCompatActivity {
    public ImageView back;
    public CardView add;
    public CardView show;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_main);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pengadaanMain.this, dashboard.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pengadaanMain.this, pengadaanAdd.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pengadaanMain.this, pengadaanShow.class);
                startActivity(intent);
            }
        });
    }
    public void setAtribut (){
        back = findViewById(R.id.backBtn);
        add = findViewById(R.id.addBtn);
        show = findViewById(R.id.showBtn);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), dashboard.class);
        startActivity(intent);
    }
}

