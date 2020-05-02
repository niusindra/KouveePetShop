package com.kel1.kouveepetshop.View.Hewan;

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

public class hewanMain extends AppCompatActivity {
    public ImageView back;
    public CardView add;
    public CardView show;
    public SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hewan_main);
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
                Intent intent = new Intent(hewanMain.this, hewanAdd.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hewanMain.this, hewanShow.class);
                startActivity(intent);
            }
        });
    }
    public void setAtribut (){
        back = findViewById(R.id.backBtnHMain);
        add = findViewById(R.id.addBtnHMain);
        show = findViewById(R.id.showBtnHMain);
    }
    public void onBackPressed() {
        final HashMap<String, String> userDetails = session.getUserDetails();
        super.onBackPressed();
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
