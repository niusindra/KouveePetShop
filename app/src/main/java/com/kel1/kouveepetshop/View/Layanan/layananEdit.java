package com.kel1.kouveepetshop.View.Layanan;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.kel1.kouveepetshop.View.ErrorCatch;

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
                    Call<cudDataMaster> customerCall = apiService.editLayanan(number,nama.getText().toString());
                    customerCall.enqueue(new Callback<cudDataMaster>(){
                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                            try {
                            if(response.body()!=null){
                                if(response.body().getNama_layanan()!=null)
                                    Toast.makeText(layananEdit.this,response.body().getNama_layanan(),Toast.LENGTH_LONG).show();
                                else{
                                    Toast.makeText(layananEdit.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
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
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(layananEdit.this);

                builder.setMessage("Anda yakin untuk menghapus data")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                                Call<cudDataMaster> supplierCall = apiService.deleteLayanan(number);
                                supplierCall.enqueue(new Callback<cudDataMaster>(){
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                                        Toast.makeText(layananEdit.this,"Berhasil dihapus",Toast.LENGTH_SHORT).show();
                                        startIntent();
                                    }
                                    public void onFailure(Call<cudDataMaster> call, Throwable t){
                                        Intent intent=new Intent(getApplicationContext(), ErrorCatch.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), layananShow.class);
        startActivity(intent);
    }
}
