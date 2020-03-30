package com.kel1.kouveepetshop.View.Customer;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class customerShow extends AppCompatActivity {

    private List<customerDAO> mListStudent=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_show);
        mListStudent=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.RC_Customer);
        recycleAdapter=new RecycleAdapter(this,mListStudent);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycleAdapter);
        setRecycleView();
    }

    private void setRecycleView(){
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readCustomer> customer = apiService.getCustomer();
        customer.enqueue(new Callback<readCustomer>(){

            @Override
            public void onResponse(Call<readCustomer> call, Response<readCustomer> response) {
                if(response.body()!=null) {
                    mListStudent.addAll(response.body().getMessage());
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readCustomer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
