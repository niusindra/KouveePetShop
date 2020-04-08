package com.kel1.kouveepetshop.View.Supplier;

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
import com.kel1.kouveepetshop.DAO.supplierDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readSupplier;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class supplierShow extends AppCompatActivity {

    public ImageView back;
    public Switch aSwitch;
    private List<supplierDAO> mListCustomer=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapterSupplier recycleAdapterSupplier;
    private RecycleAdapterSupplierLog recycleAdapterSupplierLog;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.supplier_show);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(supplierShow.this, supplierMain.class);
                startActivity(intent);
            }
        });
        mListCustomer=new ArrayList<>();
        setRecycleAdapter();
        searchCustomer = findViewById(R.id.searchCustomer);
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
        List<supplierDAO> filteredList = new ArrayList<>();
        for (supplierDAO item : mListCustomer) {
            if (item.getNama_supplier().toLowerCase().contains(text.toLowerCase()) ||
                    item.getAlamat_supplier().toLowerCase().contains(text.toLowerCase()) ||
                    item.getTelp_supplier().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapterSupplier.filterList(filteredList);
    }
    private void setRecycleAdapter(){
        recyclerView=findViewById(R.id.RC_Customer);
        recycleAdapterSupplier =new RecycleAdapterSupplier(this,mListCustomer);
        recycleAdapterSupplierLog =new RecycleAdapterSupplierLog(this,mListCustomer);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(){
        recyclerView.setAdapter(recycleAdapterSupplier);
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readSupplier> customerCall = apiService.getSupplier();
        customerCall.enqueue(new Callback<readSupplier>(){

            @Override
            public void onResponse(Call<readSupplier> call, Response<readSupplier> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recycleAdapterSupplier.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readSupplier> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setRecycleViewLog(){
        recyclerView.setAdapter(recycleAdapterSupplierLog);
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readSupplier> customerCall = apiService.getSupplierLog();
        customerCall.enqueue(new Callback<readSupplier>(){

            @Override
            public void onResponse(Call<readSupplier> call, Response<readSupplier> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recycleAdapterSupplier.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readSupplier> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setAtribut() {
        back = findViewById(R.id.btnBack);
        aSwitch = findViewById(R.id.switchLogSup);
    }
}
