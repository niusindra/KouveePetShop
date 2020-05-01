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
import com.kel1.kouveepetshop.View.Customer.customerAdd;
import com.kel1.kouveepetshop.View.Customer.customerShow;


public class landingPage extends AppCompatActivity {
    public CardView add;
    public CardView show;
    private TextView logout;
    private SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        setAtribut();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(landingPage.this, dashboard.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(landingPage.this, pengadaanMain.class);
                startActivity(intent);
            }
        });
    }
    public void setAtribut (){
        add = findViewById(R.id.intoDashboardBtn);
        show = findViewById(R.id.intoPengadaanBtn);
        logout = findViewById(R.id.landinglogouttv);
    }
    public void onBackPressed() {
    }

}
