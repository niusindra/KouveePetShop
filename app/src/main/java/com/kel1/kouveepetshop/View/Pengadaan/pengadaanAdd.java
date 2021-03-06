package com.kel1.kouveepetshop.View.Pengadaan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.kel1.kouveepetshop.Respon.readProduk;
import com.kel1.kouveepetshop.Respon.readSupplier;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pengadaanAdd extends AppCompatActivity {
    private RecyclerView myRc;
    private List<detailPengadaanDAO> detailPengadaanList = new ArrayList<>();
    public List<produkDAO> produkDAOList = new ArrayList<>();
    public List<supplierDAO> supplieritems = new ArrayList<>();
    private Button btnaddProduk;
    private ImageView back;
    private Button btnaddPengadaan;
    private Spinner mySpinner;
    private AdapterPengadaanAdd adapter;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengadaan_add);
        getSupplier();
        myRc =findViewById(R.id.myRc);
        mySpinner = findViewById(R.id.supplierSpinAdaan);
        btnaddProduk = findViewById(R.id.addProdukPengadaan);
        btnaddPengadaan = findViewById(R.id.addPengadaan);
        back = findViewById(R.id.backBtnAdaanAdd);
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
                detailPengadaanList.add(detailPengadaanDAO);
                adapter = new AdapterPengadaanAdd(getApplicationContext(), detailPengadaanList, produkDAOList);
                myRc.setAdapter(adapter);
            }
        });

        btnaddPengadaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cekSatuan=0, cekJumlah=0;
                AlertDialog.Builder builder = new AlertDialog.Builder(pengadaanAdd.this);
                total=0;
                detailPengadaanList=adapter.getArrayList();
                for (int i = 0; i < detailPengadaanList.size(); i++) {
                    total+=detailPengadaanList.get(i).getSubtotal_pengadaan();
                    if(detailPengadaanList.get(i).getSatuan()==null)
                        cekSatuan=1;
                    if(detailPengadaanList.get(i).getJml_pengadaan_produk()==0)
                        cekJumlah=1;
                }
                if(detailPengadaanList.size()==0)
                    Toast.makeText(pengadaanAdd.this,"Tambahkan produk terlebih dahulu!",Toast.LENGTH_LONG).show();
                if(cekSatuan==1)
                    Toast.makeText(pengadaanAdd.this,"Data harus terisi semua!",Toast.LENGTH_LONG).show();
                if(cekJumlah==1)
                    Toast.makeText(pengadaanAdd.this,"Jumlah produk tidak boleh 0!",Toast.LENGTH_LONG).show();
                if(cekSatuan==0 && cekJumlah==0){
                    builder.setMessage("Anda yakin membuat pengadaan dengan total: Rp "+total)
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
                                                    Call<cudDataMaster> detailPengadaanCall = apiService.addDetailPengadaan(null,detailPengadaanDAOList.get(i).getId_produk(),detailPengadaanDAOList.get(i).getSatuan(),detailPengadaanDAOList.get(i).getJml_pengadaan_produk());
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


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),pengadaanMain.class));
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
                            R.layout.spinner_item, supplieritems);
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), pengadaanMain.class);
        startActivity(intent);
    }
}

