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
import android.widget.TextView;
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

public class detailShow extends AppCompatActivity {
    private RecyclerView myRc;
    private List<detailPengadaanDAO> detailPengadaanList = new ArrayList<>();
    private List<detailPengadaanDAO> tempDetail = new ArrayList<>();
    private List<produkDAO> produkDAOList = new ArrayList<>();
    private List<supplierDAO> supplieritems = new ArrayList<>();
    private Button btnaddProduk;
    private Button btnEditPengadaan, btnHapusPengadaan;
    private TextView supplier;
    private ImageView back;
    private Spinner mySpinner;
    private detailShowAdapter adapter;
    private String[] pengadaan;
    private int[] number;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_show);

        Intent intent = getIntent();
        pengadaan = intent.getStringArrayExtra(RecycleAdapterPengadaanShow.EXTRA_TEXT);
        number = intent.getIntArrayExtra(RecycleAdapterPengadaanShow.EXTRA_NUMBER);

        getDetailPengadaan(pengadaan[0]);

        supplier = findViewById(R.id.supplierShowTV);
        myRc =findViewById(R.id.myRcDetail);
        back = findViewById(R.id.backdetailShow);
        supplier.setText(pengadaan[0]);
        myRc.setHasFixedSize(true);
        myRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),pengadaanShow.class));
            }
        });



    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getDetailPengadaan(String idpengadaan){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readDetailPengadaan> produkCall = apiService.getDetailPengadaan(idpengadaan);
        produkCall.enqueue(new Callback<readDetailPengadaan>(){

            @Override
            public void onResponse(Call<readDetailPengadaan> call, Response<readDetailPengadaan> response) {
                if(response.body()!=null) {
                    detailPengadaanList = response.body().getMessage();
                    tempDetail = response.body().getMessage();
                    adapter = new detailShowAdapter(detailShow.this, detailPengadaanList, produkDAOList);
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

