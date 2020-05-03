package com.kel1.kouveepetshop.View.HargaLayanan;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.hargalayananDAO;
import com.kel1.kouveepetshop.DAO.hewanDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readHargaLayanan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hargaLayananShow  extends AppCompatActivity {

    public ImageView back;
    public Switch aSwitch;
    private List<hargalayananDAO> mListCustomer;
    private RecyclerView recyclerView;
    private RecycleAdapterHargaLayanan recycleAdapter;
    private RecycleAdapterHargaLayananLog recycleAdapterHargaLayananLog;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.hargalayanan_show);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hargaLayananShow.this, hargaLayananMain.class);
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

    private void filter(String text) {
        List<hargalayananDAO> filteredList = new ArrayList<>();
        for (hargalayananDAO item : mListCustomer) {
            if (item.getNama_layananHL().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterHargaLayananLog.filterList(filteredList);
    }
    private void setRecycleAdapter(){
        recyclerView=findViewById(R.id.RecycleHargaLayanan);
        recycleAdapter=new RecycleAdapterHargaLayanan(this,mListCustomer);
        recycleAdapterHargaLayananLog=new RecycleAdapterHargaLayananLog(this,mListCustomer);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(){

        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readHargaLayanan> hargaLayananCall = apiService.getHargaLayanan();
        hargaLayananCall.enqueue(new Callback<readHargaLayanan>(){

            @Override
            public void onResponse(Call<readHargaLayanan> call, Response<readHargaLayanan> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapter);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readHargaLayanan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setRecycleViewLog(){

        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readHargaLayanan> hargaLayananCall = apiService.getHargaLayananLog();
        hargaLayananCall.enqueue(new Callback<readHargaLayanan>(){

            @Override
            public void onResponse(Call<readHargaLayanan> call, Response<readHargaLayanan> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapterHargaLayananLog);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readHargaLayanan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), hargaLayananMain.class);
        startActivity(intent);
    }
    public void setAtribut() {
        back = findViewById(R.id.btnBackHLshow);
        aSwitch = findViewById(R.id.switchLogHL);
        searchCustomer = findViewById(R.id.searchHarga);
    }
}
