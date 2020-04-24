package com.kel1.kouveepetshop.View.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.DAO.supplierDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readDetailPengadaan;
import com.kel1.kouveepetshop.Respon.readProduk;
import com.kel1.kouveepetshop.Respon.readSupplier;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pengadaanEdit extends AppCompatActivity {
    private RecyclerView myRc;
    private List<detailPengadaanDAO> detailPengadaanList = new ArrayList<>();
    private List<produkDAO> produkDAOList = new ArrayList<>();
    private List<supplierDAO> supplieritems = new ArrayList<>();
    private Button btnaddProduk;
    private Button btnEditPengadaan;
    private Spinner mySpinner;
    private AdapterPengadaanAdd adapter;
    private String[] pengadaan;
    private int[] number;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengadaan_edit);

        Intent intent = getIntent();
        pengadaan = intent.getStringArrayExtra(RecycleAdapterPengadaanShow.EXTRA_TEXT);
        number = intent.getIntArrayExtra(RecycleAdapterPengadaanShow.EXTRA_NUMBER);

        getSupplier();

        myRc =findViewById(R.id.myRc1);
        mySpinner = findViewById(R.id.supplierSpinAdaan1);
        btnaddProduk = findViewById(R.id.addProdukPengadaan1);
        btnEditPengadaan = findViewById(R.id.editPengadaan);
        myRc.setHasFixedSize(true);
        myRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


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

        btnEditPengadaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(pengadaanEdit.this);
                total=0;
                detailPengadaanList=adapter.getArrayList();
                for (int i = 0; i < detailPengadaanList.size(); i++) {
                    total+=detailPengadaanList.get(i).getSubtotal_pengadaan();
                }
                builder.setMessage("Anda yakin mengedit pengadaan dengan total: Rp "+total)
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
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
                                                Call<cudDataMaster> detailPengadaanCall = apiService.addDetailPengadaan(detailPengadaanDAOList.get(i).getId_produk(),detailPengadaanDAOList.get(i).getJml_pengadaan_produk());
                                                detailPengadaanCall.enqueue(new Callback<cudDataMaster>(){
                                                    @Override
                                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                                        if(response.body()!=null) {
                                                            response.body().getMessage();
                                                            startActivity(new Intent(getApplicationContext(),pengadaanShow.class));
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
                                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
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

    }

    private void getSupplier(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readSupplier> layananCall = apiService.getSupplier();
        layananCall.enqueue(new Callback<readSupplier>(){

            @Override
            public void onResponse(Call<readSupplier> call, Response<readSupplier> response) {
                if(response.body()!=null) {
                    supplieritems = response.body().getMessage();
                    ArrayAdapter<supplierDAO> adapter = new ArrayAdapter<supplierDAO>(pengadaanEdit.this,
                            android.R.layout.simple_spinner_item, supplieritems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    mySpinner.setAdapter(adapter);
                    for (int i = 0; i < supplieritems.size(); i++) {
                        if(supplieritems.get(i).getNama_supplier().equalsIgnoreCase(pengadaan[0])){
                            mySpinner.setSelection(i);
                            getProdukbySupplier(supplieritems.get(i).getId_supplier());
                            getDetailPengadaan(number[0]);
                        }
                    }
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
                }
            }
            @Override
            public void onFailure(Call<readProduk> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDetailPengadaan(int idsupplier){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readDetailPengadaan> produkCall = apiService.getDetailPengadaan(idsupplier);
        produkCall.enqueue(new Callback<readDetailPengadaan>(){

            @Override
            public void onResponse(Call<readDetailPengadaan> call, Response<readDetailPengadaan> response) {
                if(response.body()!=null) {
                    detailPengadaanList = response.body().getMessage();
                    adapter = new AdapterPengadaanAdd(getApplicationContext(), detailPengadaanList, produkDAOList);
                    myRc.setAdapter(adapter);

                }
            }
            @Override
            public void onFailure(Call<readDetailPengadaan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

