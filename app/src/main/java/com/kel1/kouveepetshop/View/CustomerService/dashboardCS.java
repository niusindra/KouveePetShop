package com.kel1.kouveepetshop.View.CustomerService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.Admin.dashboard;
import com.kel1.kouveepetshop.View.Customer.customerMain;
import com.kel1.kouveepetshop.View.Hewan.hewanMain;
import com.kel1.kouveepetshop.View.JenisHewan.jenisHewanMain;
import com.kel1.kouveepetshop.View.UkuranHewan.ukuranMain;

import java.util.HashMap;

public class dashboardCS extends AppCompatActivity {
    public CardView hewan;
    public CardView jenis_hewan;
    public  CardView ukuran_hewan;
    public CardView customer;
    public TextView logout;
    public TextView username;

    public SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_cs);
        final HashMap<String, String> userDetails = session.getUserDetails();
        setAtribut();
        username.setText("Selamat Datang, "+userDetails.get(SessionManager.KEY_NAME));
        hewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardCS.this, hewanMain.class);
                startActivity(intent);
            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardCS.this, customerMain.class);
                startActivity(intent);
            }
        });
        jenis_hewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardCS.this, jenisHewanMain.class);
                startActivity(intent);
            }
        });
        ukuran_hewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardCS.this, ukuranMain.class);
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
    }
    public void setAtribut(){
        username = findViewById(R.id.usernameIsLoggedInCS);
        hewan = findViewById(R.id.intoHewanBtn);
        customer = findViewById(R.id.intoCustomerBtn);
        jenis_hewan = findViewById(R.id.intoJenisHewanBtn);
        ukuran_hewan = findViewById(R.id.intoUkuranHewanBtn);
        logout = findViewById(R.id.dashboardCsLogout);
    }
}

