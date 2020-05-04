package com.kel1.kouveepetshop.View.Admin;

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
    private Button btnEditPengadaan, btnHapusPengadaan;
    private ImageView back;
    private Spinner mySpinner;
    private AdapterPengadaanEdit adapter;
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
        back = findViewById(R.id.backBtnAdaan1);
        mySpinner = findViewById(R.id.supplierSpinAdaan1);
        btnaddProduk = findViewById(R.id.addProdukPengadaan1);
        btnEditPengadaan = findViewById(R.id.editPengadaan);
        btnHapusPengadaan = findViewById(R.id.delPengadaan);
        myRc.setHasFixedSize(true);
        myRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),pengadaanShow.class));
            }
        });
        mySpinner.setEnabled(false);

        btnaddProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailPengadaanDAO detailPengadaanDAO = new detailPengadaanDAO();
//                detailPengadaanList = adapter.getArrayList();
                detailPengadaanDAO.setId_detail_pengadaan(0);
                detailPengadaanDAO.setId_pengadaan(pengadaan[0]);
                detailPengadaanList.add(detailPengadaanDAO);
                adapter = new AdapterPengadaanEdit(pengadaanEdit.this, detailPengadaanList, produkDAOList);
                myRc.setAdapter(adapter);
            }
        });

        btnEditPengadaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cekSatuan=0, cekJumlah=0;
                AlertDialog.Builder builder = new AlertDialog.Builder(pengadaanEdit.this);
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
                    Toast.makeText(pengadaanEdit.this,"Tambahkan produk terlebih dahulu!",Toast.LENGTH_LONG).show();
                if(cekSatuan==1)
                    Toast.makeText(pengadaanEdit.this,"Data harus terisi semua!",Toast.LENGTH_LONG).show();
                if(cekJumlah==1)
                    Toast.makeText(pengadaanEdit.this,"Jumlah produk tidak boleh 0!",Toast.LENGTH_LONG).show();
                if(cekSatuan==0 && cekJumlah==0){
                    builder.setMessage("Anda yakin mengedit pengadaan dengan total: Rp "+total)
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                    Call<cudDataMaster> pengadaanCall = apiService.editPengadaan(pengadaan[0],"Pending");
                                    pengadaanCall.enqueue(new Callback<cudDataMaster>(){
                                        @Override
                                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                            if(response.body()!=null) {

                                                ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                                List<detailPengadaanDAO> detailPengadaanDAOList = adapter.getArrayList();
                                                for (int i = 0; i < detailPengadaanDAOList.size(); i++) {
                                                    if(detailPengadaanDAOList.get(i).getId_detail_pengadaan()==0){
                                                        Call<cudDataMaster> detailPengadaanCall = apiService.addDetailPengadaan(pengadaan[0],detailPengadaanDAOList.get(i).getId_produk(),detailPengadaanDAOList.get(i).getSatuan(),detailPengadaanDAOList.get(i).getJml_pengadaan_produk());
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
                                                                Toast.makeText(getApplicationContext(),"Error1",Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }else{
                                                        Call<cudDataMaster> detailPengadaanCall = apiService.editDetailPengadaan(detailPengadaanDAOList.get(i).getId_detail_pengadaan(),detailPengadaanDAOList.get(i).getId_produk(),detailPengadaanDAOList.get(i).getSatuan(),detailPengadaanDAOList.get(i).getJml_pengadaan_produk());
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
                                                                Toast.makeText(getApplicationContext(),"Error2",Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }

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

        btnHapusPengadaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(pengadaanEdit.this);
                builder.setMessage("Anda yakin menghapus data pengadaan ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                Call<cudDataMaster> pengadaanCall = apiService.deletePengadaan(pengadaan[0]);
                                pengadaanCall.enqueue(new Callback<cudDataMaster>(){
                                    @Override
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                        if(response.body()!=null) {
                                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();

                                            ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                            List<detailPengadaanDAO> detailPengadaanDAOList = adapter.getArrayList();
                                            for (int i = 0; i < detailPengadaanDAOList.size(); i++) {
                                                Call<cudDataMaster> detailPengadaanCall = apiService.deleteDetailPengadaan(detailPengadaanDAOList.get(i).getId_detail_pengadaan());
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

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), pengadaanShow.class);
        startActivity(intent);
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
                            R.layout.spinner_item, supplieritems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    mySpinner.setAdapter(adapter);
                    for (int i = 0; i < supplieritems.size(); i++) {
                        if(supplieritems.get(i).getNama_supplier().equalsIgnoreCase(pengadaan[1])){
                            mySpinner.setSelection(i);
                            getProdukbySupplierInit(supplieritems.get(i).getId_supplier());
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

    private void getProdukbySupplierInit(int id){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readProduk> produkCall = apiService.getProdukbySupplier(id);
        produkCall.enqueue(new Callback<readProduk>(){

            @Override
            public void onResponse(Call<readProduk> call, Response<readProduk> response) {
                if(response.body()!=null) {
                    produkDAOList = response.body().getMessage();
                    getDetailPengadaan(pengadaan[0]);
                }
            }
            @Override
            public void onFailure(Call<readProduk> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDetailPengadaan(String idpengadaan){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readDetailPengadaan> produkCall = apiService.getDetailPengadaan(idpengadaan);
        produkCall.enqueue(new Callback<readDetailPengadaan>(){

            @Override
            public void onResponse(Call<readDetailPengadaan> call, Response<readDetailPengadaan> response) {
                if(response.body()!=null) {
                    detailPengadaanList = response.body().getMessage();
                    adapter = new AdapterPengadaanEdit(pengadaanEdit.this, detailPengadaanList, produkDAOList);
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

