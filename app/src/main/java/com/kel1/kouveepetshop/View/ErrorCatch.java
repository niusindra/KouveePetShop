package com.kel1.kouveepetshop.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.View.Admin.login_pegawai;

public class ErrorCatch extends AppCompatActivity {
    public Button btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_catch);
        setAtribut();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ErrorCatch.this, login_pegawai.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), login_pegawai.class);
        startActivity(intent);
    }
    public void setAtribut (){
        btn = findViewById(R.id.btnError);
    }

}