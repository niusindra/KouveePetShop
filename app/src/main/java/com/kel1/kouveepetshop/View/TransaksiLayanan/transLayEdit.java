package com.kel1.kouveepetshop.View.TransaksiLayanan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.kel1.kouveepetshop.DAO.transaksiLayananDAO;
import com.kel1.kouveepetshop.DAO.transaksiProdukDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readDetailTransLay;
import com.kel1.kouveepetshop.Respon.readDetailTransPro;
import com.kel1.kouveepetshop.Respon.readHargaLayanan;
import com.kel1.kouveepetshop.Respon.readHewan;
import com.kel1.kouveepetshop.Respon.readProduk;
import com.kel1.kouveepetshop.Respon.readTransLay;
import com.kel1.kouveepetshop.Respon.readTransPro;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.CustomerService.CS_Dashboard;
import com.kel1.kouveepetshop.View.TransaksiProduk.AdapterTransProAdd;
import com.kel1.kouveepetshop.View.TransaksiProduk.RecycleAdapterTransProShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class transLayEdit extends AppCompatActivity {
    private List<transaksiLayananDAO> TransLayList = new ArrayList<>();
    private List<detailLayananDAO> detailTransLayList = new ArrayList<>();
    private List<hargalayananDAO> hargalayananDAOList = new ArrayList<>();
    private List<hewanDAO> hewanDAOList = new ArrayList<>();

    private AdapterTransLayAdd adapterEdit;
    private AdapterTransLayShow adapterShow;

    private AppCompatAutoCompleteTextView cari;
    private TextView namaCust, telpCust, almtCust, namaHwn, jenisHwn, tvcariHewan, tvEditHewan, tvEditLayanan, tvID,
                        createdAt, createdBy, editedAt, editedBy;
    private RecyclerView myRc;
    private Button btnHapusTransaksi, btnSelesai;
    private int total;
    private int id_hewan;
    private SessionManager session;
    private String[] translay;
    private int[] number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translay_edit);
        setAtribut();

        session = new SessionManager(getApplicationContext());
        final HashMap<String, String> userDetails = session.getUserDetails();

        Intent intent = getIntent();
        translay = intent.getStringArrayExtra(RecycleAdapterTransProShow.EXTRA_TEXT);
        number = intent.getIntArrayExtra(RecycleAdapterTransProShow.EXTRA_NUMBER);
        getTransLay(translay[0]);
        getDetailTransLay(translay[0]);

        getHewan();

        myRc.setHasFixedSize(true);
        myRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        tvEditHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvEditHewan.getText().toString().equalsIgnoreCase("edit")){
                    tvcariHewan.setVisibility(View.VISIBLE);
                    cari.setVisibility(View.VISIBLE);
                    tvEditHewan.setText("Selesai");
                }else{
                    ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                    Call<cudDataMaster> TransProCall = apiService.editTransPro(translay[0],id_hewan,"Belum Lunas",userDetails.get(SessionManager.KEY_NAME));
                    TransProCall.enqueue(new Callback<cudDataMaster>(){
                        @Override
                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                            if(response.body()!=null) {
                                Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<cudDataMaster> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Error1",Toast.LENGTH_SHORT).show();
                        }
                    });
                    tvcariHewan.setVisibility(View.GONE);
                    cari.setVisibility(View.GONE);
                    tvEditHewan.setText("Edit");
                }
            }
        });

        tvEditLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvEditLayanan.getText().toString().equalsIgnoreCase("edit")){
                    tvEditLayanan.setText("Selesai");
                    adapterEdit = new AdapterTransLayAdd(transLayEdit.this, detailTransLayList, hargalayananDAOList);
                    myRc.setAdapter(adapterEdit);
                    adapterEdit.notifyDataSetChanged();
                }else{
                    tvEditLayanan.setText("Edit");
                    adapterShow = new AdapterTransLayShow(transLayEdit.this, detailTransLayList, hargalayananDAOList);
                    myRc.setAdapter(adapterShow);
                    adapterShow.notifyDataSetChanged();
                    ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                    List<detailLayananDAO> detailLayananDAOList = adapterShow.getArrayList();
                    for (int i = 0; i < detailLayananDAOList.size(); i++) {
                        if(detailLayananDAOList.get(i).getId_detail_layanan()==0){
                            Call<cudDataMaster> detailTransProCall = apiService.addDetailTransPro(translay[0],detailLayananDAOList.get(i).getId_harga_layanan(),detailLayananDAOList.get(i).getJumlah_beli_layanan());
                            detailTransProCall.enqueue(new Callback<cudDataMaster>(){
                                @Override
                                public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                    if(response.body()!=null) {
                                        response.body().getMessage();
                                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<cudDataMaster> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Call<cudDataMaster> detailTransProCall = apiService.editDetailTransPro(detailLayananDAOList.get(i).getId_detail_layanan(),detailLayananDAOList.get(i).getId_harga_layanan(),detailLayananDAOList.get(i).getJumlah_beli_layanan());
                            detailTransProCall.enqueue(new Callback<cudDataMaster>(){
                                @Override
                                public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                    if(response.body()!=null) {
                                        response.body().getMessage();
                                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
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
            }
        });

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

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(transLayEdit.this);
                builder.setMessage("Anda yakin layanan sudah selesai?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                Call<cudDataMaster> pengadaanCall = apiService.konfirmasiSelesai(translay[0]);
                                pengadaanCall.enqueue(new Callback<cudDataMaster>(){
                                    @Override
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                        if(response.body()!=null) {
                                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(transLayEdit.this, CS_Dashboard.class);
                                            i.putExtra("loadFragment",R.id.transaksi_layanan);
                                            startActivity(i);
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

        btnHapusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(transLayEdit.this);
                builder.setMessage("Anda yakin menghapus data transaksi ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                Call<cudDataMaster> pengadaanCall = apiService.deleteTransLay(translay[0],userDetails.get(SessionManager.KEY_NAME));
                                pengadaanCall.enqueue(new Callback<cudDataMaster>(){
                                    @Override
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                        if(response.body()!=null) {
                                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();

                                            ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                            List<detailLayananDAO> detailLayananDAOList = adapterShow.getArrayList();
                                            for (int i = 0; i < detailLayananDAOList.size(); i++) {
                                                Call<cudDataMaster> detailPengadaanCall = apiService.deleteDetailPengadaan(detailLayananDAOList.get(i).getId_detail_layanan());
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
                                            Intent i = new Intent(transLayEdit.this, CS_Dashboard.class);
                                            i.putExtra("loadFragment",R.id.transaksi_layanan);
                                            startActivity(i);

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
        Intent i = new Intent(transLayEdit.this, CS_Dashboard.class);
        i.putExtra("loadFragment",R.id.transaksi_layanan);
        startActivity(i);
    }

    public void setAtribut(){
        cari = findViewById(R.id.cariHewan);
        namaCust = findViewById(R.id.namaCustomer);
        telpCust = findViewById(R.id.telpCustomer);
        almtCust = findViewById(R.id.almtCustomer);
        namaHwn = findViewById(R.id.namaHewan);
        jenisHwn = findViewById(R.id.jenisHewan);
        btnHapusTransaksi = findViewById(R.id.btnDelTransLay);
        btnSelesai = findViewById(R.id.btnSelesaiLay);
        myRc = findViewById(R.id.RC_translay);
        tvcariHewan = findViewById(R.id.tvCariHewan);
        tvEditHewan = findViewById(R.id.editHewan);
        tvEditLayanan = findViewById(R.id.editItem);
        tvID = findViewById(R.id.tvID);
        createdAt = findViewById(R.id.createdAt);
        createdBy = findViewById(R.id.createdBy);
        editedAt = findViewById(R.id.editedAt);
        editedBy = findViewById(R.id.editedBy);
    }

    public void getLayanan(){
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readHargaLayanan> layananCall = apiService.getHargaLayanan();
        layananCall.enqueue(new Callback<readHargaLayanan>(){

            @Override
            public void onResponse(Call<readHargaLayanan> call, Response<readHargaLayanan> response) {
                if(response.body()!=null) {
                    hargalayananDAOList.addAll(response.body().getMessage());
                    adapterShow = new AdapterTransLayShow(transLayEdit.this, detailTransLayList, hargalayananDAOList);
                    myRc.setAdapter(adapterShow);
                    adapterShow.notifyDataSetChanged();
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
                            (transLayEdit.this, R.layout.autocomplete_adapter, R.id.item, hewanDAOList);
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

    public void getDetailTransLay(String idTransLay){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readDetailTransLay> layananCall = apiService.getDetailLayanan(idTransLay);
        layananCall.enqueue(new Callback<readDetailTransLay>(){

            @Override
            public void onResponse(Call<readDetailTransLay> call, Response<readDetailTransLay> response) {
                if(response.body()!=null) {
                    detailTransLayList = response.body().getMessage();
                    getLayanan();
                }
            }
            @Override
            public void onFailure(Call<readDetailTransLay> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getTransLay(String id){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readTransLay> layananCall = apiService.getTransLay(id);
        layananCall.enqueue(new Callback<readTransLay>(){

            @Override
            public void onResponse(Call<readTransLay> call, Response<readTransLay> response) {
                if(response.body()!=null) {
                    TransLayList=response.body().getMessage();

                    tvID.setText(translay[0]);
                    if(TransLayList.get(0).getStatus_layanan().equalsIgnoreCase("belum selesai"))
                        btnSelesai.setVisibility(View.VISIBLE);
                    if(TransLayList.get(0).getId_hewan()==0){
                        namaHwn.setText("GUEST");
                        namaCust.setText("GUEST");
                        jenisHwn.setVisibility(View.GONE);
                        almtCust.setVisibility(View.GONE);
                        telpCust.setVisibility(View.GONE);
                    }else{
                        namaHwn.setText("Nama : "+TransLayList.get(0).getNama_hewan());
                        jenisHwn.setText("Jenis : "+TransLayList.get(0).getJenis());
                        namaCust.setText("Nama : "+TransLayList.get(0).getNama_customer());
                        almtCust.setText("Alamat : "+TransLayList.get(0).getAlamat_customer());
                        telpCust.setText("Telp : "+TransLayList.get(0).getTelp_customer());
                    }
                    if(TransLayList.get(0).getTranslay_created_at()!=null){
                        createdAt.setVisibility(View.VISIBLE);
                        createdAt.setText("Dibuat pada : "+TransLayList.get(0).getTranslay_created_at());
                    }
                    if(TransLayList.get(0).getTranslay_created_by()!=null){
                        createdBy.setVisibility(View.VISIBLE);
                        createdBy.setText("Dibuat oleh : "+TransLayList.get(0).getTranslay_created_by());
                    }
                    if(TransLayList.get(0).getTranslay_edited_at()!=null){
                        editedAt.setVisibility(View.VISIBLE);
                        editedAt.setText("Diedit pada : "+TransLayList.get(0).getTranslay_edited_at());
                    }
                    if(TransLayList.get(0).getTranslay_edited_by()!=null){
                        editedBy.setVisibility(View.VISIBLE);
                        editedBy.setText("Diedit oleh : "+TransLayList.get(0).getTranslay_edited_by());
                    }
                }
            }
            @Override
            public void onFailure(Call<readTransLay> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

