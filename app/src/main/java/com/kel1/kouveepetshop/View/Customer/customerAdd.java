package com.kel1.kouveepetshop.View.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.R;


public class customerAdd extends AppCompatActivity {
    //aaa
    public ImageView back;
    public EditText date;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_add);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customerAdd.this, customerMain.class);
                startActivity(intent);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void setAtribut (){
        back = findViewById(R.id.backBtn);
        date = findViewById(R.id.dateTxt);
    }

}
