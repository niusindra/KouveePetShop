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
import com.kel1.kouveepetshop.Respon.cudCustomer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class layananEdit extends AppCompatActivity {
    public ImageView back;
    public EditText nama;
    public Button editBtn;
    public Button delBtn;
    public Button enableNama;
    public String layanan[];
    public int number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layanan_edit);
        setAtribut();
        Intent intent = getIntent();
        layanan = intent.getStringArrayExtra(RecycleAdapterLayanan.EXTRA_TEXT);
        number = intent.getIntExtra(RecycleAdapterLayanan.EXTRA_NUMBER,0);
        setText(layanan[0]);

        enableNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nama.isEnabled()){
                    enableNama.setBackgroundResource(R.drawable.lock);
                    nama.setEnabled(false);
                }
                else {
                    enableNama.setBackgroundResource(R.drawable.unlocked);
                    nama.setEnabled(true);
                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
                }else{

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<cudCustomer> customerCall = apiService.editLayanan(number,nama.getText().toString());
                    customerCall.enqueue(new Callback<cudCustomer>(){
                        public void onResponse(Call<cudCustomer> call, Response<cudCustomer> response){
                            Toast.makeText(layananEdit.this,"Berhasil edit",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                        public void onFailure(Call<cudCustomer> call, Throwable t){
                            Toast.makeText(layananEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                    });
                }
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<cudCustomer> supplierCall = apiService.deleteSupplier(number);
                supplierCall.enqueue(new Callback<cudCustomer>(){
                    public void onResponse(Call<cudCustomer> call, Response<cudCustomer> response){
                        Toast.makeText(layananEdit.this,"Berhasil dihapus",Toast.LENGTH_SHORT).show();
                        startIntent();
                    }
                    public void onFailure(Call<cudCustomer> call,Throwable t){
                        Toast.makeText(layananEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layananEdit.this, layananShow.class);
                startActivity(intent);
            }
        });

    }
    public void setAtribut (){
        back = findViewById(R.id.backBtnlay1);
        editBtn = findViewById(R.id.editlayBtn1);
        nama = findViewById(R.id.namalayTxt1);
        delBtn = findViewById(R.id.dellayBtn1);
        enableNama = findViewById(R.id.enablelayNama1);
    }

    public void setText(String nama){
        this.nama.setText(nama);
    }

    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(), layananShow.class);
        startActivity(intent);
    }
}
