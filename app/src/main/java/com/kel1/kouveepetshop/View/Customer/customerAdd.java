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
import com.kel1.kouveepetshop.Respon.cudCustomer;
import com.kel1.kouveepetshop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class customerAdd extends AppCompatActivity {
    //aaa
    public ImageView back;
    public EditText date;
    public EditText nama;
    public EditText alamat;
    public EditText telp;
    public Button addBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_add);
        setAtribut();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().isEmpty() || alamat.getText().toString().isEmpty() || date.getText().toString().isEmpty() || telp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Field can't be empty!", Toast.LENGTH_SHORT).show();
                }else{
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<cudCustomer> customerCall = apiService.addCustomer(nama.getText().toString(),
                            alamat.getText().toString(),date.getText().toString(),
                            telp.getText().toString(),nama.getText().toString());
                    customerCall.enqueue(new Callback<cudCustomer>(){
                        public void onResponse(Call<cudCustomer> call, Response<cudCustomer> response){
                            Toast.makeText(customerAdd.this,"Hapie Success",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                        public void onFailure(Call<cudCustomer> call, Throwable t){
                            Toast.makeText(customerAdd.this,"Hapie eror",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customerAdd.this, customerMain.class);
                startActivity(intent);
            }
        });

    }
    public void setAtribut (){
        back = findViewById(R.id.backBtn);
        date = findViewById(R.id.dateTxt);
        addBtn = findViewById(R.id.addBtn);
        nama = findViewById(R.id.namaTxt);
        alamat = findViewById(R.id.alamatTxt);
        telp = findViewById(R.id.telpTxt);
    }
    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(),customerEdit.class);
        startActivity(intent);
    }
}
