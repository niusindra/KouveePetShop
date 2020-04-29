package com.kel1.kouveepetshop.View.JenisHewan;

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
import com.kel1.kouveepetshop.DAO.jenishewanDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readJenisHewan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class jenisHewanShow extends AppCompatActivity {

    public ImageView back;
    public Switch aSwitch;
    private List<jenishewanDAO> mListCustomer;
    private RecyclerView recyclerView;
    private RecycleAdapterJenisHewan recycleAdapter;
    private RecycleAdapterJenisHewanLog recycleAdapterJenisLog;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.jenishewan_show);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jenisHewanShow.this, jenisHewanMain.class);
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
        List<jenishewanDAO> filteredList = new ArrayList<>();
        for (jenishewanDAO item : mListCustomer) {
            if (item.getJenis().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterJenisLog.filterList(filteredList);
    }
    private void setRecycleAdapter(){
        recyclerView=findViewById(R.id.RC_JenisHewan);
        recycleAdapter=new RecycleAdapterJenisHewan(this,mListCustomer);
        recycleAdapterJenisLog=new RecycleAdapterJenisHewanLog(this,mListCustomer);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(){

        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readJenisHewan> layananCall = apiService.getJenisHewan();
        layananCall.enqueue(new Callback<readJenisHewan>(){
            @Override
            public void onResponse(Call<readJenisHewan> call, Response<readJenisHewan> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapter);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readJenisHewan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setRecycleViewLog(){

        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readJenisHewan> ukuranHewanCall = apiService.getJenisHewanLog();
        ukuranHewanCall.enqueue(new Callback<readJenisHewan>(){

            @Override
            public void onResponse(Call<readJenisHewan> call, Response<readJenisHewan> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapterJenisLog);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readJenisHewan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), jenisHewanMain.class);
        startActivity(intent);
    }
    public void setAtribut() {
        back = findViewById(R.id.btnBackJHShow);
        aSwitch = findViewById(R.id.switchLogJH);
        searchCustomer = findViewById(R.id.searchJenis);
    }
}
