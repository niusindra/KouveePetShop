package com.kel1.kouveepetshop.View.Admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.DAO.supplierDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readProduk;
import com.kel1.kouveepetshop.Respon.readSupplier;
import com.kel1.kouveepetshop.View.ErrorCatch;
import com.kel1.kouveepetshop.View.Produk.FileUtils;
import com.kel1.kouveepetshop.View.Produk.produkMain;
import com.kel1.kouveepetshop.View.Produk.produkShow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pengadaanAdd extends AppCompatActivity {
    private RecyclerView myRc;
    private List<detailPengadaanDAO> detailPengadaanList = new ArrayList<>();
    public List<produkDAO> produkDAOList = new ArrayList<>();
    public List<supplierDAO> supplieritems = new ArrayList<>();
    private Button btnaddProduk;
    private Button btnaddPengadaan;
    private Spinner mySpinner;
    private AdapterPengadaanAdd adapter;
    private int idsupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengadaan_add);
        getSupplier();
        myRc =findViewById(R.id.myRc);
        mySpinner = findViewById(R.id.supplierSpinAdaan);
        btnaddProduk = findViewById(R.id.addProdukPengadaan);
        btnaddPengadaan = findViewById(R.id.addPengadaan);

        myRc.setHasFixedSize(true);
        myRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new AdapterPengadaanAdd(this, detailPengadaanList, produkDAOList);
        myRc.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                supplierDAO user = (supplierDAO) parent.getSelectedItem();
                getProdukbySupplier(user.getId_supplier());
                detailPengadaanList.clear();
                adapter = new AdapterPengadaanAdd(getApplicationContext(), detailPengadaanList, produkDAOList);
                myRc.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnaddProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailPengadaanDAO detailPengadaanDAO = new detailPengadaanDAO();
//                detailPengadaanList = adapter.getArrayList();
                detailPengadaanList.add(detailPengadaanDAO);
                adapter = new AdapterPengadaanAdd(getApplicationContext(), detailPengadaanList, produkDAOList);
                myRc.setAdapter(adapter);
            }
        });

        btnaddPengadaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                Call<cudDataMaster> pengadaanCall = apiService.addPengadaan("Pending");
                pengadaanCall.enqueue(new Callback<cudDataMaster>(){
                    @Override
                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                        if(response.body()!=null) {
                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();

                            ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                            List<detailPengadaanDAO> detailPengadaanDAOList = adapter.getArrayList();
                            for (int i = 0; i < detailPengadaanDAOList.size(); i++) {
                                Call<cudDataMaster> detailPengadaanCall = apiService.addDetailPengadaan(1,detailPengadaanDAOList.get(i).getJml_pengadaan_produk());
                                detailPengadaanCall.enqueue(new Callback<cudDataMaster>(){
                                    @Override
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                        if(response.body()!=null) {
                                            response.body().getMessage();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<cudDataMaster> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<cudDataMaster> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error1",Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

    }

    private void getSupplier(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readSupplier> layananCall = apiService.getSupplier();
        layananCall.enqueue(new Callback<readSupplier>(){

            @Override
            public void onResponse(Call<readSupplier> call, Response<readSupplier> response) {
                if(response.body()!=null) {
                    supplieritems = response.body().getMessage();
                    ArrayAdapter<supplierDAO> adapter = new ArrayAdapter<supplierDAO>(pengadaanAdd.this,
                            android.R.layout.simple_spinner_item, supplieritems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    mySpinner.setAdapter(adapter);

                    getProdukbySupplier(supplieritems.get(0).getId_supplier());
                }
            }

            @Override
            public void onFailure(Call<readSupplier> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProdukbySupplier(int id){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readProduk> produkCall = apiService.getProdukbySupplier(id);
        produkCall.enqueue(new Callback<readProduk>(){

            @Override
            public void onResponse(Call<readProduk> call, Response<readProduk> response) {
                if(response.body()!=null) {
                    produkDAOList = response.body().getMessage();

//                    Toast.makeText(context,mListProduk.get(0).getNama_produk(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<readProduk> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

