package com.kel1.kouveepetshop.View.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.Customer.customerMain;
import com.kel1.kouveepetshop.View.JenisHewan.jenisHewanMain;
import com.kel1.kouveepetshop.View.Layanan.layananMain;
import com.kel1.kouveepetshop.View.Produk.produkMain;
import com.kel1.kouveepetshop.View.Hewan.hewanMain;
import com.kel1.kouveepetshop.View.Supplier.supplierMain;
import com.kel1.kouveepetshop.View.UkuranHewan.ukuranMain;

public class dashboard extends AppCompatActivity {
    public CardView produk;
    public CardView hewan;
    public CardView customer;
    public CardView jhewan;
    public CardView layanan;
    public CardView uhewan;
    public CardView supplier;
    public TextView logout;

    public SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_admin);

        setAtribut();
        hewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, hewanMain.class);
                startActivity(intent);
            }
        });
        produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, produkMain.class);
                startActivity(intent);
            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, customerMain.class);
                startActivity(intent);
            }
        });
        jhewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, jenisHewanMain.class);
                startActivity(intent);
            }
        });
        uhewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, ukuranMain.class);
                startActivity(intent);
            }
        });
        layanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, layananMain.class);
                startActivity(intent);
            }
        });
        supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, supplierMain.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
                finish();
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), landingPage.class);
        startActivity(intent);
    }
    public void setAtribut(){
        produk = findViewById(R.id.produkbtn);
        layanan = findViewById(R.id.layananBtn);
        hewan = findViewById(R.id.btnHewan);
        jhewan = findViewById(R.id.jhewanBtn);
        uhewan = findViewById(R.id.uhewanBtn);
        customer = findViewById(R.id.customerBtn);
        supplier = findViewById(R.id.supplierBtn);
        logout = findViewById(R.id.logOutBtn);
    }
}

