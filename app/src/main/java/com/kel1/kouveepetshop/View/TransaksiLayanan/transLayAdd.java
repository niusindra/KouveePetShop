package com.kel1.kouveepetshop.View.TransaksiLayanan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.detailLayananDAO;
import com.kel1.kouveepetshop.DAO.detailProdukDAO;
import com.kel1.kouveepetshop.DAO.hargalayananDAO;
import com.kel1.kouveepetshop.DAO.hewanDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readHargaLayanan;
import com.kel1.kouveepetshop.Respon.readHewan;
import com.kel1.kouveepetshop.Respon.readProduk;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.CustomerService.CS_Dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class transLayAdd extends AppCompatActivity {
    private List<detailLayananDAO> detailTransProList = new ArrayList<>();
    private List<hargalayananDAO> layananDAOList = new ArrayList<>();
    private List<hewanDAO> hewanDAOList = new ArrayList<>();
    private AdapterTransLayAdd adapter;

    private AppCompatAutoCompleteTextView cari;
    private TextView namaCust, telpCust, almtCust, namaHwn, jenisHwn, tvcariHewan;
    private RecyclerView myRc;
    private Button btnAddTransPro, btnaddProduk;
    private int total;
    private int id_hewan=0;
    private String intentExtra;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translay_add);
        setAtribut();
        getLayanan();
        getHewan();
        intentExtra = getIntent().getStringExtra("cekMember");
        if(intentExtra.equalsIgnoreCase("non member")){
            tvcariHewan.setVisibility(View.GONE);
            cari.setVisibility(View.GONE);
            telpCust.setVisibility(View.GONE);
            almtCust.setVisibility(View.GONE);
            jenisHwn.setVisibility(View.GONE);

            namaCust.setText("Nama : GUEST");
            namaHwn.setText("Nama : GUEST");
        }
        session = new SessionManager(getApplicationContext());
        final HashMap<String, String> userDetails = session.getUserDetails();

        cari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                hewanDAO hewan = (hewanDAO) adapterView.getItemAtPosition(i);
                id_hewan=hewan.getId_hewan();
                namaCust.setText("Nama : "+hewan.getNama_customer());
                telpCust.setText("Telepon : "+hewan.getTelp_customer());
                almtCust.setText("Alamat : "+hewan.getAlamat_customer());
                namaHwn.setText("Nama : "+hewan.getNama_hewan());
                jenisHwn.setText("Jenis : "+hewan.getJenis());
            }
        });
        myRc.setHasFixedSize(true);
        myRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new AdapterTransLayAdd(this, detailTransProList, layananDAOList);
        myRc.setAdapter(adapter);
        btnaddProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailLayananDAO detailLayananDAO = new detailLayananDAO();
                detailTransProList.add(detailLayananDAO);
                adapter = new AdapterTransLayAdd(getApplicationContext(), detailTransProList, layananDAOList);
                myRc.setAdapter(adapter);
                btnaddProduk.setVisibility(View.GONE);
            }
        });
        btnAddTransPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int cekSatuan=0, cekJumlah=0;
//                AlertDialog.Builder builder = new AlertDialog.Builder(transProAdd.this);
//                total=0;
//                detailTransProList=adapter.getArrayList();
//                for (int i = 0; i < detailTransProList.size(); i++) {
//                    total+=detailTransProList.get(i).getSubtotal_pengadaan();
//                    if(detailTransProList.get(i).getSatuan()==null)
//                        cekSatuan=1;
//                    if(detailTransProList.get(i).getJml_pengadaan_produk()==0)
//                        cekJumlah=1;

//                    Log.e("posisi",String.valueOf(i));
//                    Log.e("jml",String.valueOf(detailTransProList.get(i).getJumlah_beli_produk()));
//                    Log.e("id_produk",String.valueOf(detailTransProList.get(i).getId_produk()));
//                    Log.e("harga",String.valueOf(detailTransProList.get(i).getSubtotal_produk()));
//                    Log.e("=======","========================");
//                }
//                if(detailTransProList.size()==0)
//                    Toast.makeText(transProAdd.this,"Tambahkan produk terlebih dahulu!",Toast.LENGTH_LONG).show();
//                if(cekSatuan==1)
//                    Toast.makeText(transProAdd.this,"Data harus terisi semua!",Toast.LENGTH_LONG).show();
//                if(cekJumlah==1)
//                    Toast.makeText(transProAdd.this,"Jumlah produk tidak boleh 0!",Toast.LENGTH_LONG).show();
//                if(cekSatuan==0 && cekJumlah==0){
//                    builder.setMessage("Anda yakin membuat pengadaan dengan total: Rp "+total)
//                            .setCancelable(false)
//                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {

                                    ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                    Call<cudDataMaster> TransLayCall = apiService.addTransLay(Integer.parseInt(userDetails.get(SessionManager.KEY_ID)),id_hewan,"Belum Selesai",userDetails.get(SessionManager.KEY_NAME));
                                    TransLayCall.enqueue(new Callback<cudDataMaster>(){
                                        @Override
                                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                            if(response.body()!=null) {
//                                                Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                                ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                                List<detailLayananDAO> detailLayananDAOList = adapter.getArrayList();
                                                for (int i = 0; i < detailLayananDAOList.size(); i++) {
                                                    Call<cudDataMaster> detailTransLayCall = apiService.addDetailTransLay(null,detailLayananDAOList.get(i).getId_harga_layanan(),detailLayananDAOList.get(i).getJumlah_beli_layanan());
                                                    detailTransLayCall.enqueue(new Callback<cudDataMaster>(){
                                                        @Override
                                                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                                            if(response.body()!=null) {
                                                                Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                                                                response.body().getMessage();
                                                                Intent i = new Intent(transLayAdd.this, CS_Dashboard.class);
                                                                i.putExtra("loadFragment",R.id.transaksi_layanan);
                                                                startActivity(i);
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<cudDataMaster> call, Throwable t) {
                                                            Toast.makeText(getApplicationContext(),"Error22",Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<cudDataMaster> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(),"Error11",Toast.LENGTH_SHORT).show();
                                        }
                                    });

//                                }
//                            })
//                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    dialogInterface.cancel();
//                                }
//                            });
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
//                }
//
//
            }
        });
    }
    public void setAtribut(){
        cari = findViewById(R.id.cariHewan);
        namaCust = findViewById(R.id.namaCustomer);
        telpCust = findViewById(R.id.telpCustomer);
        almtCust = findViewById(R.id.almtCustomer);
        namaHwn = findViewById(R.id.namaHewan);
        jenisHwn = findViewById(R.id.jenisHewan);
        btnaddProduk = findViewById(R.id.btnTambahProduk);
        btnAddTransPro = findViewById(R.id.btnAddTransPro);
        myRc = findViewById(R.id.RC_transpro);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(transLayAdd.this, CS_Dashboard.class);
        i.putExtra("loadFragment",R.id.transaksi_layanan);
        startActivity(i);
    }

    public void getLayanan(){
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readHargaLayanan> layananCall = apiService.getHargaLayanan();
        layananCall.enqueue(new Callback<readHargaLayanan>(){

            @Override
            public void onResponse(Call<readHargaLayanan> call, Response<readHargaLayanan> response) {
                if(response.body()!=null) {
                    layananDAOList.addAll(response.body().getMessage());
                    adapter = new AdapterTransLayAdd(getApplicationContext(), detailTransProList, layananDAOList);
                    myRc.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<readHargaLayanan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Masalah Koneksi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getHewan(){
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readHewan> layananCall = apiService.getHewan();
        layananCall.enqueue(new Callback<readHewan>(){

            @Override
            public void onResponse(Call<readHewan> call, Response<readHewan> response) {
                if(response.body()!=null) {
                    hewanDAOList.addAll(response.body().getMessage());
                    ArrayAdapter<hewanDAO> adapter = new ArrayAdapter<hewanDAO>
                            (getApplicationContext(), R.layout.autocomplete_adapter,R.id.item, hewanDAOList);
                    cari.setThreshold(0); //will start working from first character
                    cari.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<readHewan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Masalah Koneksi",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

