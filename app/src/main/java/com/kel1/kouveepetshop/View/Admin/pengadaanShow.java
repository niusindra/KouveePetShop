package com.kel1.kouveepetshop.View.Admin;

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
import com.kel1.kouveepetshop.DAO.pengadaanDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readCustomer;
import com.kel1.kouveepetshop.Respon.readPengadaan;
import com.kel1.kouveepetshop.View.Customer.customerMain;
import com.kel1.kouveepetshop.View.ErrorCatch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pengadaanShow extends AppCompatActivity {

    private ImageView back;
    private Switch aSwitch;
    private List<pengadaanDAO> mListPengadaan =new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapterPengadaanShow recycleAdapter;
    private RecycleAdapterLogPengadaanShowLog recycleAdapterLog;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        setContentView(R.layout.pengadaan_show);
        setAtribut();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pengadaanShow.this, dashboard.class);
                startActivity(intent);
            }
        });

        mListPengadaan =new ArrayList<>();
        setRecycleAdapter();
        searchCustomer = findViewById(R.id.searchAdaan);

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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), customerMain.class);
        startActivity(intent);
    }
    private void filter(String text) {
        List<pengadaanDAO> filteredList = new ArrayList<>();
        for (pengadaanDAO item : mListPengadaan) {
            if (item.getNama_supplier().toLowerCase().contains(text.toLowerCase()) ||
                    item.getStatus_pengadaan().toLowerCase().contains(text.toLowerCase()) ||
                    item.getTgl_pengadaan().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterLog.filterList(filteredList);
    }
    private void setRecycleAdapter(){
        recyclerView=findViewById(R.id.RC_Adaan);
        recycleAdapter=new RecycleAdapterPengadaanShow(this, mListPengadaan);
        recycleAdapterLog=new RecycleAdapterLogPengadaanShowLog(this, mListPengadaan);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(){

        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readPengadaan> pengadaanCallCall = apiService.getPengadaan();
        pengadaanCallCall.enqueue(new Callback<readPengadaan>(){

            @Override
            public void onResponse(Call<readPengadaan> call, Response<readPengadaan> response) {
                if(response.body()!=null) {
                    mListPengadaan.clear();
                    mListPengadaan.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapter);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readPengadaan> call, Throwable t) {
                Intent intent=new Intent(getApplicationContext(), ErrorCatch.class);
                startActivity(intent);
            }
        });
    }

    private void setRecycleViewLog(){

        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readPengadaan> pengadaanCallCall = apiService.getPengadaan();
        pengadaanCallCall.enqueue(new Callback<readPengadaan>(){

            @Override
            public void onResponse(Call<readPengadaan> call, Response<readPengadaan> response) {
                if(response.body()!=null) {
                    mListPengadaan.clear();
                    mListPengadaan.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapterLog);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readPengadaan> call, Throwable t) {
                Intent intent=new Intent(getApplicationContext(), ErrorCatch.class);
                startActivity(intent);
        }
        });
    }

    public void setAtribut() {
        back = findViewById(R.id.btnBackAdaan);
        aSwitch = findViewById(R.id.switchLogAdaan);
    }
}
