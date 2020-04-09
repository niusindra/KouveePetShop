package com.kel1.kouveepetshop.View.Layanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class layananAdd extends AppCompatActivity {
    //aaa
    public ImageView back;
    public EditText nama;
    public Button addBtn;
    public SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layanan_add);
        setAtribut();
        session = new SessionManager(getApplicationContext())
        ;        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
                }else{
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<cudDataMaster> layananCall = apiService.addLayanan(nama.getText().toString());
                    layananCall.enqueue(new Callback<cudDataMaster>(){
                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                            Toast.makeText(layananAdd.this,"Berhasil tambah",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                        public void onFailure(Call<cudDataMaster> call, Throwable t){
                            Toast.makeText(layananAdd.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layananAdd.this, layananMain.class);
                startActivity(intent);
            }
        });

    }
    public void setAtribut (){
        back = findViewById(R.id.backBtnlay);
        addBtn = findViewById(R.id.addlayBtn);
        nama = findViewById(R.id.namalayTxt);
    }
    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(), layananShow.class);
        startActivity(intent);
    }
}
