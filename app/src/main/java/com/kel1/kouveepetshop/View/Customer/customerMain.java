package com.kel1.kouveepetshop.View.Customer;

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


public class customerMain extends AppCompatActivity {
    public ImageView back;
    public CardView add;
    public CardView show;
    public SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_main);
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
                Intent intent = new Intent(customerMain.this, customerAdd.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customerMain.this, customerShow.class);
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
        final HashMap<String, String> userDetails = session.getUserDetails();
        if(userDetails.get(SessionManager.KEY_NAME).equalsIgnoreCase("admin")){
            Intent intent=new Intent(getApplicationContext(), dashboard.class);
            startActivity(intent);
        }
    }

}
