package com.kel1.kouveepetshop.View.Layanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.View.Admin.dashboard;

public class layananMain extends AppCompatActivity {
    public ImageView back;
    public CardView add;
    public CardView show;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layanan_main);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layananMain.this, dashboard.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layananMain.this, layananAdd.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layananMain.this, layananShow.class);
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
