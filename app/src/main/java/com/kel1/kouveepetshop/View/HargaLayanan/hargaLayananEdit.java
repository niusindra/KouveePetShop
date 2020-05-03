package com.kel1.kouveepetshop.View.HargaLayanan;

import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.jenishewanDAO;
import com.kel1.kouveepetshop.DAO.layananDAO;
import com.kel1.kouveepetshop.DAO.ukuranhewanDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readJenisHewan;
import com.kel1.kouveepetshop.Respon.readLayanan;
import com.kel1.kouveepetshop.Respon.readUkuranHewan;
import com.kel1.kouveepetshop.View.ErrorCatch;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hargaLayananEdit extends AppCompatActivity {
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
    public Button editBtn;
    public Button deleteBtn;
    public Button enableHarga;
    public String hargalay[];
    public int number[];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hargalayanan_edit);
        setAtribut();
        getLayanan();
        getJenis();
        getUkuran();
        mSpinnerL.setSelection(1);
        mSpinnerJ.setSelection(1);
        mSpinnerU.setSelection(1);
        Intent intent = getIntent();
        hargalay = intent.getStringArrayExtra(RecycleAdapterHargaLayanan.EXTRA_TEXT);
        number = intent.getIntArrayExtra(RecycleAdapterHargaLayanan.EXTRA_NUMBER);
        setText(hargalay[0]);
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
        enableHarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(harga.isEnabled()){
                    enableHarga.setBackgroundResource(R.drawable.lock);
                    harga.setEnabled(false);
                }
                else {
                    enableHarga.setBackgroundResource(R.drawable.unlocked);
                    harga.setEnabled(true);
                }
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(hargaLayananEdit.this);
                builder.setMessage("Anda yakin untuk menghapus data")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                                Call<cudDataMaster> supplierCall = apiService.deleteHargaLayanan(number[0]);
                                supplierCall.enqueue(new Callback<cudDataMaster>(){
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                                        Toast.makeText(hargaLayananEdit.this,"Berhasil dihapus",Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(hargaLayananEdit.this, hargaLayananShow.class);
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
            Call<cudDataMaster> layananCall = apiService.editHargaLayanan(number[0],Lid,Jid,Uid,hg_layanan);
            layananCall.enqueue(new Callback<cudDataMaster>(){
                public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                    try {
                        if(response.body()!=null){
                            if(response.body().getNama_layanan()!=null)
                                Toast.makeText(hargaLayananEdit.this,response.body().getNama_layanan(),Toast.LENGTH_LONG).show();
                            else{
                                Toast.makeText(hargaLayananEdit.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                                startIntent();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                public void onFailure(Call<cudDataMaster> call, Throwable t){
                    Toast.makeText(hargaLayananEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void setText(String nama){
        this.harga.setText(nama);
    }
    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(), hargaLayananShow.class);
        startActivity(intent);
    }
    public void setAtribut(){
        enableHarga = findViewById(R.id.enableHargaHL);
        harga = findViewById(R.id.hargalayananEdit);
        mSpinnerL = findViewById(R.id.spinnerLayananEdit);
        mSpinnerJ = findViewById(R.id.spinnerJenisEdit);
        mSpinnerU = findViewById(R.id.spinnerUkuranEdit);
        editBtn = findViewById(R.id.editHLBtn1);
        deleteBtn = findViewById(R.id.delHLBtn1);
        back = findViewById(R.id.backBtnHLEdit);
    }
    private void getLayanan(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readLayanan> layananCall = apiService.getLayanan();
        layananCall.enqueue(new Callback<readLayanan>(){

            @Override
            public void onResponse(Call<readLayanan> call, Response<readLayanan> response) {
                if(response.body()!=null) {
                    mListLayanan = response.body().getMessage();
                    ArrayAdapter<layananDAO> adapter = new ArrayAdapter<layananDAO>(hargaLayananEdit.this,
                            android.R.layout.simple_spinner_item, mListLayanan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinnerL.setAdapter(adapter);
                    for (int i = 0; i < mListLayanan.size(); i++) {
                        if(mListLayanan.get(i).getId_layanan()==number[1]){
                            mSpinnerL.setSelection(i);
                        }
                    }
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
                    ArrayAdapter<jenishewanDAO> adapter = new ArrayAdapter<jenishewanDAO>(hargaLayananEdit.this,
                            android.R.layout.simple_spinner_item, mListJenis);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinnerJ.setAdapter(adapter);
                    for (int i = 0; i < mListJenis.size(); i++) {
                        if(mListJenis.get(i).getId_jenis()==number[2]){
                            mSpinnerJ.setSelection(i);
                        }
                    }
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
                    ArrayAdapter<ukuranhewanDAO> adapter = new ArrayAdapter<ukuranhewanDAO>(hargaLayananEdit.this,
                            android.R.layout.simple_spinner_item, mListUkuran);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinnerU.setAdapter(adapter);
                    for (int i = 0; i < mListUkuran.size(); i++) {
                        if(mListUkuran.get(i).getId_ukuran()==number[3]){
                            mSpinnerU.setSelection(i);
                        }
                    }
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
        Intent intent=new Intent(getApplicationContext(), hargaLayananShow.class);
        startActivity(intent);
    }
}
