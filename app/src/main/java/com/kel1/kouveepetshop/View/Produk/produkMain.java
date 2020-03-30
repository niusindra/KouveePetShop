package com.kel1.kouveepetshop.View.Produk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.View.Admin.dashboard;

public class produkMain extends AppCompatActivity {
    public ImageView back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produk_main);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(produkMain.this, dashboard.class);
                startActivity(intent);
            }
        });
    }
    public void setAtribut (){
        back = findViewById(R.id.backBtn);
    }

}

