package com.kel1.kouveepetshop.View.UkuranHewan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.Admin.dashboard;
import com.kel1.kouveepetshop.View.CustomerService.dashboardCS;

import java.util.HashMap;

public class ukuranMain extends AppCompatActivity {
    public ImageView back;
    public CardView add;
    public CardView show;
    public SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ukuran_main);
        setAtribut();
        session = new SessionManager(getApplicationContext());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HashMap<String, String> userDetails = session.getUserDetails();
                if(userDetails.get(SessionManager.KEY_NAME).equalsIgnoreCase("admin")){
                    Intent intent=new Intent(getApplicationContext(), dashboard.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(getApplicationContext(), dashboardCS.class);
                    startActivity(intent);
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ukuranMain.this, ukuranAdd.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ukuranMain.this, ukuranShow.class);
                startActivity(intent);
            }
        });
    }
    public void setAtribut (){
        back = findViewById(R.id.backBtnUkMain);
        add = findViewById(R.id.addBtnUkMain);
        show = findViewById(R.id.showBtnUkMain);
    }
    public void onBackPressed() {
        super.onBackPressed();
        final HashMap<String, String> userDetails = session.getUserDetails();
        if(userDetails.get(SessionManager.KEY_NAME).equalsIgnoreCase("admin")){
            Intent intent=new Intent(getApplicationContext(), dashboard.class);
            startActivity(intent);
        }
        else
        {
            Intent intent=new Intent(getApplicationContext(), dashboardCS.class);
            startActivity(intent);
        }
    }

}
