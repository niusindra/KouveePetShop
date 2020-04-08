package com.kel1.kouveepetshop.View.Customer;

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
import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readCustomer;
import com.kel1.kouveepetshop.View.Admin.dashboard;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class customerShow extends AppCompatActivity {

    private ImageView back;
    private Switch aSwitch;
    private List<customerDAO> mListCustomer=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private RecycleAdapterLog recycleAdapterLog;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        setContentView(R.layout.customer_show);
        setAtribut();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customerShow.this, dashboard.class);
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
        List<customerDAO> filteredList = new ArrayList<>();
        for (customerDAO item : mListCustomer) {
            if (item.getNama_customer().toLowerCase().contains(text.toLowerCase()) ||
                    item.getAlamat_customer().toLowerCase().contains(text.toLowerCase()) ||
                    item.getTelp_customer().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterLog.filterList(filteredList);
    }
    private void setRecycleAdapter(){
        recyclerView=findViewById(R.id.RC_Customer);
        recycleAdapter=new RecycleAdapter(this,mListCustomer);
        recycleAdapterLog=new RecycleAdapterLog(this,mListCustomer);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(){
        recyclerView.setAdapter(recycleAdapter);
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readCustomer> customerCall = apiService.getCustomer();
        customerCall.enqueue(new Callback<readCustomer>(){

            @Override
            public void onResponse(Call<readCustomer> call, Response<readCustomer> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readCustomer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecycleViewLog(){
        recyclerView.setAdapter(recycleAdapterLog);
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readCustomer> customerCall = apiService.getCustomerLog();
        customerCall.enqueue(new Callback<readCustomer>(){

            @Override
            public void onResponse(Call<readCustomer> call, Response<readCustomer> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readCustomer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setAtribut() {
        back = findViewById(R.id.btnBack);
        aSwitch = findViewById(R.id.switchLogCust);
    }
}
