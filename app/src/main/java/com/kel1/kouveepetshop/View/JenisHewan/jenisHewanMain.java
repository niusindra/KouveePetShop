package com.kel1.kouveepetshop.View.JenisHewan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.Admin.dashboard;

import java.util.HashMap;

public class jenisHewanMain extends AppCompatActivity {
    public ImageView back;
    public CardView add;
    public CardView show;
    public SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jenishewan_main);
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
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jenisHewanMain.this, jenisHewanAdd.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jenisHewanMain.this, jenisHewanShow.class);
                startActivity(intent);
            }
        });
    }
    public void setAtribut (){
        back = findViewById(R.id.backBtnJHMain);
        add = findViewById(R.id.addBtnJHMain);
        show = findViewById(R.id.showBtnJHMain);
    }
    public void onBackPressed() {
        super.onBackPressed();
        final HashMap<String, String> userDetails = session.getUserDetails();
        if(userDetails.get(SessionManager.KEY_NAME).equalsIgnoreCase("admin")){
            Intent intent=new Intent(getApplicationContext(), dashboard.class);
            startActivity(intent);
        }
    }

}


