package com.kel1.kouveepetshop.View.Supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.View.Admin.dashboard;
import com.kel1.kouveepetshop.View.Customer.customerMain;

public class supplierMain extends AppCompatActivity {
    public ImageView back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplier_main);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(supplierMain.this, dashboard.class);
                startActivity(intent);
            }
        });
    }
    public void setAtribut (){
        back = findViewById(R.id.backBtn);
    }

}

