package com.kel1.kouveepetshop.View.HargaLayanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.hargalayananDAO;
import com.kel1.kouveepetshop.DAO.jenishewanDAO;
import com.kel1.kouveepetshop.DAO.layananDAO;
import com.kel1.kouveepetshop.DAO.ukuranhewanDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readJenisHewan;
import com.kel1.kouveepetshop.Respon.readLayanan;
import com.kel1.kouveepetshop.Respon.readUkuranHewan;
import com.kel1.kouveepetshop.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hargaLayananAdd extends AppCompatActivity {
    public List<layananDAO> mListLayanan;
    public List<jenishewanDAO> mListJenis;
    public List<ukuranhewanDAO> mListUkuran;
    public ImageView back;
    public EditText harga;
    public Button add;
    public Spinner mSpinnerL;
    public Spinner mSpinnerJ;
    public Spinner mSpinnerU;
    public int idlay;
    public int idjenis;
    public int idukuran;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hargalayanan_add);
        setAtribut();
        getLayanan();
        getJenis();
        getUkuran();
        mSpinnerL.setSelection(1);
        mSpinnerJ.setSelection(1);
        mSpinnerU.setSelection(1);
        mSpinnerL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                layananDAO user = (layananDAO) parent.getSelectedItem();
                idlay=user.getId_layanan();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerJ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jenishewanDAO user = (jenishewanDAO) parent.getSelectedItem();
                idjenis=user.getId_jenis();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ukuranhewanDAO user = (ukuranhewanDAO) parent.getSelectedItem();
                idukuran=user.getId_ukuran();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hargaLayananAdd.this, hargaLayananMain.class);
                startActivity(intent);
            }
        });
    }
    public void upload()
    {
        if(this.harga.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
        }else{
            int Lid = this.idlay;
            int Jid = this.idjenis;
            int Uid = this.idukuran;
            int hg_layanan = Integer.parseInt(this.harga.getText().toString());
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<cudDataMaster> layananCall = apiService.addHargaLayanan(Lid,Jid,Uid,hg_layanan);
            layananCall.enqueue(new Callback<cudDataMaster>(){
                public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                    try {
                        if(response.body()!=null){
                            if(response.body().getNama_layanan()!=null)
                                Toast.makeText(hargaLayananAdd.this,response.body().getNama_layanan(),Toast.LENGTH_LONG).show();
                            else{
                                Toast.makeText(hargaLayananAdd.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                                startIntent();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                public void onFailure(Call<cudDataMaster> call, Throwable t){
                    Toast.makeText(hargaLayananAdd.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(), hargaLayananShow.class);
        startActivity(intent);
    }
    public void setAtribut(){
        add = findViewById(R.id.addHBtn);
        harga = findViewById(R.id.hargalayananTxt);
        mSpinnerL = findViewById(R.id.layananHLSpin);
        mSpinnerJ = findViewById(R.id.jenisHLSpin);
        mSpinnerU = findViewById(R.id.ukuranHLSpin);
        back = findViewById(R.id.backBtnHAdd);
    }
    private void getLayanan(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readLayanan> layananCall = apiService.getLayanan();
        layananCall.enqueue(new Callback<readLayanan>(){

            @Override
            public void onResponse(Call<readLayanan> call, Response<readLayanan> response) {
                if(response.body()!=null) {
                    mListLayanan = response.body().getMessage();
                    ArrayAdapter<layananDAO> adapter = new ArrayAdapter<layananDAO>(hargaLayananAdd.this,
                            android.R.layout.simple_spinner_item, mListLayanan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinnerL.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<readLayanan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getJenis(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readJenisHewan> jenisHewanCall = apiService.getJenisHewan();
        jenisHewanCall.enqueue(new Callback<readJenisHewan>(){

            @Override
            public void onResponse(Call<readJenisHewan> call, Response<readJenisHewan> response) {
                if(response.body()!=null) {
                    mListJenis= response.body().getMessage();
                    ArrayAdapter<jenishewanDAO> adapter = new ArrayAdapter<jenishewanDAO>(hargaLayananAdd.this,
                            android.R.layout.simple_spinner_item, mListJenis);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinnerJ.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<readJenisHewan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getUkuran(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readUkuranHewan> ukuranHewanCall = apiService.getUkuranHewan();
        ukuranHewanCall.enqueue(new Callback<readUkuranHewan>(){

            @Override
            public void onResponse(Call<readUkuranHewan> call, Response<readUkuranHewan> response) {
                if(response.body()!=null) {
                    mListUkuran = response.body().getMessage();
                    ArrayAdapter<ukuranhewanDAO> adapter = new ArrayAdapter<ukuranhewanDAO>(hargaLayananAdd.this,
                            android.R.layout.simple_spinner_item, mListUkuran);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinnerU.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<readUkuranHewan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), hargaLayananMain.class);
        startActivity(intent);
    }
}


