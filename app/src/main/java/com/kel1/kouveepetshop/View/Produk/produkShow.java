package com.kel1.kouveepetshop.View.Produk;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readProduk;
import com.kel1.kouveepetshop.View.ErrorCatch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class produkShow extends AppCompatActivity {

    public ImageView back;
    public Switch aSwitch;
    private List<produkDAO> mListCustomer;
    private RecyclerView recyclerView;
    private RecycleAdapterProduk recycleAdapter;
    private RecycleAdapterProdukLog recycleAdapterProdukLog;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.produk_show);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(produkShow.this, produkMain.class);
                startActivity(intent);
            }
        });
        mListCustomer=new ArrayList<>();
        setRecycleAdapter();
        searchCustomer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        setRecycleView();
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    setRecycleViewLog();
                }else{
                    setRecycleView();
                }
            }
        });
        if(aSwitch.isChecked()){
            setRecycleViewLog();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),produkMain.class);
        startActivity(intent);
    }
    private void filter(String text) {
        List<produkDAO> filteredList = new ArrayList<>();
        for (produkDAO item : mListCustomer) {
            if (item.getNama_produk().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterProdukLog.filterList(filteredList);
    }
    private void setRecycleAdapter(){
        recyclerView=findViewById(R.id.RC_Produk);
        recycleAdapter=new RecycleAdapterProduk(this,mListCustomer);
        recycleAdapterProdukLog =new RecycleAdapterProdukLog(this,mListCustomer);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(){
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readProduk> layananCall = apiService.getProduk();
        layananCall.enqueue(new Callback<readProduk>(){

            @Override
            public void onResponse(Call<readProduk> call, Response<readProduk> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapter);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readProduk> call, Throwable t) {
                Intent intent=new Intent(getApplicationContext(), ErrorCatch.class);
                startActivity(intent);
            }
        });
    }
    private void setRecycleViewLog(){
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readProduk> layananCall = apiService.getProdukLog();
        layananCall.enqueue(new Callback<readProduk>(){

            @Override
            public void onResponse(Call<readProduk> call, Response<readProduk> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapterProdukLog);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readProduk> call, Throwable t) {
                Intent intent=new Intent(getApplicationContext(), ErrorCatch.class);
                startActivity(intent);
            }
        });
    }
    public void setAtribut() {
        back = findViewById(R.id.btnBackProduk);
        aSwitch = findViewById(R.id.switchLogPro);
        searchCustomer = findViewById(R.id.searchProduk);
    }
}
