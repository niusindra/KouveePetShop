package com.kel1.kouveepetshop.View.Supplier;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class supplierEdit extends AppCompatActivity {
    public ImageView back;
    public EditText nama;
    public EditText alamat;
    public EditText telp;
    public Button editBtn;
    public Button delBtn;
    public Button enableNama;
    public Button enableAlamat;
    public Button enableDate;
    public Button enableTelp;
    public String supplier[];
    public int number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplier_edit);
        setAtribut();
        Intent intent = getIntent();
        supplier = intent.getStringArrayExtra(RecycleAdapterSupplier.EXTRA_TEXT);
        number = intent.getIntExtra(RecycleAdapterSupplier.EXTRA_NUMBER,0);
        setText(supplier[0],supplier[1],supplier[2]);

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
        enableAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alamat.isEnabled())
                {
                    enableAlamat.setBackgroundResource(R.drawable.lock);
                    alamat.setEnabled(false);
                }
                else {
                    enableAlamat.setBackgroundResource(R.drawable.unlocked);
                    alamat.setEnabled(true);
                }
            }
        });
        enableTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(telp.isEnabled())
                {
                    enableTelp.setBackgroundResource(R.drawable.lock);
                    telp.setEnabled(false);
                }
                else {
                    enableTelp.setBackgroundResource(R.drawable.unlocked);
                    telp.setEnabled(true);
                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().isEmpty() || alamat.getText().toString().isEmpty() || telp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
                }else{

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<cudDataMaster> customerCall = apiService.editSupplier(number,nama.getText().toString(),
                            alamat.getText().toString(),telp.getText().toString());
                    customerCall.enqueue(new Callback<cudDataMaster>(){
                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                            Toast.makeText(supplierEdit.this,"Berhasil edit",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                        public void onFailure(Call<cudDataMaster> call, Throwable t){
                            Toast.makeText(supplierEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
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
                Call<cudDataMaster> supplierCall = apiService.deleteSupplier(number);
                supplierCall.enqueue(new Callback<cudDataMaster>(){
                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                        Toast.makeText(supplierEdit.this,"Berhasil dihapus",Toast.LENGTH_SHORT).show();
                        startIntent();
                    }
                    public void onFailure(Call<cudDataMaster> call, Throwable t){
                        Toast.makeText(supplierEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(supplierEdit.this, supplierShow.class);
                startActivity(intent);
            }
        });

    }
    public void setAtribut (){
        back = findViewById(R.id.backBtnsupp1);
        editBtn = findViewById(R.id.editsuppBtn1);
        nama = findViewById(R.id.namasuppTxt1);
        alamat = findViewById(R.id.alamatsuppTxt1);
        telp = findViewById(R.id.telpsuppTxt1);
        delBtn = findViewById(R.id.delsuppBtn1);
        enableNama = findViewById(R.id.enablesuppNama1);
        enableAlamat = findViewById(R.id.enablesuppAlamat1);
        enableTelp = findViewById(R.id.enablesuppTelp1);
    }

    public void setText(String nama, String alamat, String telp){
        this.nama.setText(nama);
        this.alamat.setText(alamat);
        this.telp.setText(telp);
    }

    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(), supplierShow.class);
        startActivity(intent);
    }
}
