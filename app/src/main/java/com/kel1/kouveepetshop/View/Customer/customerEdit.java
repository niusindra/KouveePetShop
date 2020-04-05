package com.kel1.kouveepetshop.View.Customer;

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
import com.kel1.kouveepetshop.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class customerEdit extends AppCompatActivity {
    public ImageView back;
    public EditText date;
    public EditText nama;
    public EditText alamat;
    public EditText telp;
    public Button editBtn;
    public Button delBtn;
    public Button enableNama;
    public Button enableAlamat;
    public Button enableDate;
    public Button enableTelp;
    public SessionManager session;
    public String customer[];
    public int number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_edit);
        setAtribut();
        Intent intent = getIntent();
        customer = intent.getStringArrayExtra(RecycleAdapter.EXTRA_TEXT);
        number = intent.getIntExtra(RecycleAdapter.EXTRA_NUMBER,0);
        setText(customer[0],customer[1],customer[2],customer[3]);

        session = new SessionManager(getApplicationContext());
        final HashMap<String, String> userDetails = session.getUserDetails();

        enableNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nama.isEnabled()){
                    nama.setEnabled(false);
                }
                else {
                    nama.setEnabled(true);
                }
            }
        });
        enableAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alamat.isEnabled())
                {
                    nama.setEnabled(false);
                }
                else {
                    alamat.setEnabled(true);
                }
            }
        });
        enableDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date.isEnabled())
                {
                    date.setEnabled(false);
                }
                else {
                    date.setEnabled(true);
                }
            }
        });
        enableTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(telp.isEnabled())
                {
                    telp.setEnabled(false);
                }
                else {
                    telp.setEnabled(true);
                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().isEmpty() || alamat.getText().toString().isEmpty() || date.getText().toString().isEmpty() || telp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
                }else{

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<cudCustomer> customerCall = apiService.editCustomer(number,nama.getText().toString(),
                            alamat.getText().toString(),date.getText().toString(),
                            telp.getText().toString(),userDetails.get(SessionManager.KEY_NAME));
                    customerCall.enqueue(new Callback<cudCustomer>(){
                        public void onResponse(Call<cudCustomer> call, Response<cudCustomer> response){
                            Toast.makeText(customerEdit.this,"Berhasil edit",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                        public void onFailure(Call<cudCustomer> call, Throwable t){
                            Toast.makeText(customerEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
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
                Call<cudCustomer> customerCall = apiService.deleteCustomer(number,userDetails.get(SessionManager.KEY_NAME));
                customerCall.enqueue(new Callback<cudCustomer>(){
                    public void onResponse(Call<cudCustomer> call, Response<cudCustomer> response){
                        Toast.makeText(customerEdit.this,"Berhasil dihapus",Toast.LENGTH_SHORT).show();
                        startIntent();
                    }
                    public void onFailure(Call<cudCustomer> call,Throwable t){
                        Toast.makeText(customerEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customerEdit.this, customerMain.class);
                startActivity(intent);
            }
        });

    }
    public void setAtribut (){
        back = findViewById(R.id.backBtn1);
        date = findViewById(R.id.dateTxt1);
        editBtn = findViewById(R.id.editBtn1);
        nama = findViewById(R.id.namaTxt1);
        alamat = findViewById(R.id.alamatTxt1);
        telp = findViewById(R.id.telpTxt1);
        delBtn = findViewById(R.id.delBtn1);
        enableNama = findViewById(R.id.enableNama1);
        enableAlamat = findViewById(R.id.enableAlamat1);
        enableDate = findViewById(R.id.enableDate1);
        enableTelp = findViewById(R.id.enableTelp1);
    }

    public void setText(String nama, String alamat, String tgllahir, String telp){
        this.nama.setText(nama);
        this.alamat.setText(alamat);
        this.date.setText(tgllahir);
        this.telp.setText(telp);
    }

    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(),customerShow.class);
        startActivity(intent);
    }
}
