package com.kel1.kouveepetshop.View.JenisHewan;

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
import com.kel1.kouveepetshop.View.ErrorCatch;
import com.kel1.kouveepetshop.View.UkuranHewan.ukuranAdd;
import com.kel1.kouveepetshop.View.UkuranHewan.ukuranMain;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class jenisHewanAdd extends AppCompatActivity {
    //aaa
    public ImageView back;
    public EditText nama;
    public Button addBtn;
    public SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jenishewan_add);
        setAtribut();
        session = new SessionManager(getApplicationContext());
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
                }else{
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<cudDataMaster> jenisCall = apiService.addJenisHewan(nama.getText().toString());
                    jenisCall.enqueue(new Callback<cudDataMaster>(){
                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                            try {
                                if(response.body()!=null){
                                    if(response.body().getJenis_hewan()!=null)
                                        Toast.makeText(jenisHewanAdd.this,response.body().getJenis_hewan(),Toast.LENGTH_LONG).show();
                                    else{
                                        Toast.makeText(jenisHewanAdd.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                                        startIntent();
                                    }
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        public void onFailure(Call<cudDataMaster> call, Throwable t){
                            Intent intent=new Intent(getApplicationContext(), ErrorCatch.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jenisHewanAdd.this, jenisHewanMain.class);
                startActivity(intent);
            }
        });

    }
    public void setAtribut (){
        back = findViewById(R.id.backBtnJHAdd);
        addBtn = findViewById(R.id.addJHBtn);
        nama = findViewById(R.id.namaJHTxt);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), jenisHewanMain.class);
        startActivity(intent);
    }
    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(), jenisHewanMain.class);
        startActivity(intent);
    }
}
