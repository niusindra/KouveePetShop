package com.kel1.kouveepetshop.View.TransaksiProduk;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.kel1.kouveepetshop.DAO.detailProdukDAO;
import com.kel1.kouveepetshop.DAO.hewanDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.DAO.transaksiProdukDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readDetailPengadaan;
import com.kel1.kouveepetshop.Respon.readDetailTransPro;
import com.kel1.kouveepetshop.Respon.readHewan;
import com.kel1.kouveepetshop.Respon.readProduk;
import com.kel1.kouveepetshop.Respon.readTransPro;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.CustomerService.CS_Dashboard;
import com.kel1.kouveepetshop.View.Pengadaan.AdapterPengadaanEdit;
import com.kel1.kouveepetshop.View.Pengadaan.pengadaanEdit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class transProEdit extends AppCompatActivity {
    private List<transaksiProdukDAO> TransProList = new ArrayList<>();
    private List<detailProdukDAO> detailTransProList = new ArrayList<>();
    private List<produkDAO> produkDAOList = new ArrayList<>();
    private List<hewanDAO> hewanDAOList = new ArrayList<>();

    private AdapterTransProAdd adapterEdit;
    private AdapterTransProShow adapterShow;

    private AppCompatAutoCompleteTextView cari;
    private TextView namaCust, telpCust, almtCust, namaHwn, jenisHwn, tvcariHewan, tvEditHewan, tvEditProduk, tvID,
                        createdAt, createdBy, editedAt, editedBy;
    private RecyclerView myRc;
    private Button btnHapusTransaksi;
    private int total;
    private int id_hewan;
    private SessionManager session;
    private String[] transpro;
    private int[] number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transpro_edit);
        setAtribut();

        session = new SessionManager(getApplicationContext());
        final HashMap<String, String> userDetails = session.getUserDetails();

        Intent intent = getIntent();
        transpro = intent.getStringArrayExtra(RecycleAdapterTransProShow.EXTRA_TEXT);
        number = intent.getIntArrayExtra(RecycleAdapterTransProShow.EXTRA_NUMBER);
        getTransPro(transpro[0]);
        getDetailTransPro(transpro[0]);

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
                    Call<cudDataMaster> TransProCall = apiService.editTransPro(transpro[0],id_hewan,"Belum Lunas",userDetails.get(SessionManager.KEY_NAME));
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

        tvEditProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvEditProduk.getText().toString().equalsIgnoreCase("edit")){
                    tvEditProduk.setText("Selesai");
                    adapterEdit = new AdapterTransProAdd(transProEdit.this, detailTransProList, produkDAOList);
                    myRc.setAdapter(adapterEdit);
                    adapterEdit.notifyDataSetChanged();
                }else{
                    tvEditProduk.setText("Edit");
                    adapterShow = new AdapterTransProShow(transProEdit.this, detailTransProList, produkDAOList);
                    myRc.setAdapter(adapterShow);
                    adapterShow.notifyDataSetChanged();
                    ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                    List<detailProdukDAO> detailProdukDAOList = adapterShow.getArrayList();
                    for (int i = 0; i < detailProdukDAOList.size(); i++) {
                        if(detailProdukDAOList.get(i).getId_detail_produk()==0){
                            Call<cudDataMaster> detailTransProCall = apiService.addDetailTransPro(transpro[0],detailProdukDAOList.get(i).getId_produk(),detailProdukDAOList.get(i).getJumlah_beli_produk());
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
                            Call<cudDataMaster> detailTransProCall = apiService.editDetailTransPro(detailProdukDAOList.get(i).getId_detail_produk(),detailProdukDAOList.get(i).getId_produk(),detailProdukDAOList.get(i).getJumlah_beli_produk());
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

        btnHapusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(transProEdit.this);
                builder.setMessage("Anda yakin menghapus data transaksi ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                Call<cudDataMaster> pengadaanCall = apiService.deleteTransPro(transpro[0],userDetails.get(SessionManager.KEY_NAME));
                                pengadaanCall.enqueue(new Callback<cudDataMaster>(){
                                    @Override
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                        if(response.body()!=null) {
                                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();

                                            ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                            List<detailProdukDAO> detailTransProDAOList = adapterShow.getArrayList();
                                            for (int i = 0; i < detailTransProDAOList.size(); i++) {
                                                Call<cudDataMaster> detailPengadaanCall = apiService.deleteDetailPengadaan(detailTransProDAOList.get(i).getId_detail_produk());
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
                                            Intent i = new Intent(transProEdit.this, CS_Dashboard.class);
                                            i.putExtra("loadFragment",R.id.transaksi_produk);
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
        Intent i = new Intent(transProEdit.this, CS_Dashboard.class);
        i.putExtra("loadFragment",R.id.transaksi_produk);
        startActivity(i);
    }

    public void setAtribut(){
        cari = findViewById(R.id.cariHewan);
        namaCust = findViewById(R.id.namaCustomer);
        telpCust = findViewById(R.id.telpCustomer);
        almtCust = findViewById(R.id.almtCustomer);
        namaHwn = findViewById(R.id.namaHewan);
        jenisHwn = findViewById(R.id.jenisHewan);
        btnHapusTransaksi = findViewById(R.id.btnDelTransPro);
        myRc = findViewById(R.id.RC_transpro);
        tvcariHewan = findViewById(R.id.tvCariHewan);
        tvEditHewan = findViewById(R.id.editHewan);
        tvEditProduk = findViewById(R.id.editItem);
        tvID = findViewById(R.id.tvID);
        createdAt = findViewById(R.id.createdAt);
        createdBy = findViewById(R.id.createdBy);
        editedAt = findViewById(R.id.editedAt);
        editedBy = findViewById(R.id.editedBy);
    }

    public void setRecycleTampil(){

    }

    public void setRecycleEdit(){

    }

    public void getProduk(){
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readProduk> layananCall = apiService.getProduk();
        layananCall.enqueue(new Callback<readProduk>(){

            @Override
            public void onResponse(Call<readProduk> call, Response<readProduk> response) {
                if(response.body()!=null) {
                    produkDAOList.addAll(response.body().getMessage());
                    adapterShow = new AdapterTransProShow(transProEdit.this, detailTransProList, produkDAOList);
                    myRc.setAdapter(adapterShow);
                    adapterShow.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readProduk> call, Throwable t) {
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
                            (transProEdit.this, R.layout.autocomplete_adapter, R.id.item, hewanDAOList);
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

    public void getDetailTransPro(String idTransPro){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readDetailTransPro> produkCall = apiService.getDetailProduk(idTransPro);
        produkCall.enqueue(new Callback<readDetailTransPro>(){

            @Override
            public void onResponse(Call<readDetailTransPro> call, Response<readDetailTransPro> response) {
                if(response.body()!=null) {
                    detailTransProList = response.body().getMessage();
                    getProduk();
                }
            }
            @Override
            public void onFailure(Call<readDetailTransPro> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getTransPro(String id){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readTransPro> produkCall = apiService.getTransPro(id);
        produkCall.enqueue(new Callback<readTransPro>(){

            @Override
            public void onResponse(Call<readTransPro> call, Response<readTransPro> response) {
                if(response.body()!=null) {
                    TransProList=response.body().getMessage();

                    tvID.setText(transpro[0]);
                    if(TransProList.get(0).getId_hewan()==0){
                        namaHwn.setText("GUEST");
                        namaCust.setText("GUEST");
                        jenisHwn.setVisibility(View.GONE);
                        almtCust.setVisibility(View.GONE);
                        telpCust.setVisibility(View.GONE);
                    }else{
                        namaHwn.setText("Nama : "+TransProList.get(0).getNama_hewan());
                        jenisHwn.setText("Jenis : "+TransProList.get(0).getJenis());
                        namaCust.setText("Nama : "+TransProList.get(0).getNama_customer());
                        almtCust.setText("Alamat : "+TransProList.get(0).getAlamat_customer());
                        telpCust.setText("Telp : "+TransProList.get(0).getTelp_customer());
                    }
                    if(TransProList.get(0).getTransproduk_created_at()!=null){
                        createdAt.setVisibility(View.VISIBLE);
                        createdAt.setText("Dibuat pada : "+TransProList.get(0).getTransproduk_created_at());
                    }
                    if(TransProList.get(0).getTransproduk_created_by()!=null){
                        createdBy.setVisibility(View.VISIBLE);
                        createdBy.setText("Dibuat oleh : "+TransProList.get(0).getTransproduk_created_by());
                    }
                    if(TransProList.get(0).getTransproduk_edited_at()!=null){
                        editedAt.setVisibility(View.VISIBLE);
                        editedAt.setText("Diedit pada : "+TransProList.get(0).getTransproduk_edited_at());
                    }
                    if(TransProList.get(0).getTransproduk_edited_by()!=null){
                        editedBy.setVisibility(View.VISIBLE);
                        editedBy.setText("Diedit oleh : "+TransProList.get(0).getTransproduk_edited_by());
                    }
                }
            }
            @Override
            public void onFailure(Call<readTransPro> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

