package com.kel1.kouveepetshop.View.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.kel1.kouveepetshop.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.Respon.readCustomer;
import com.kel1.kouveepetshop.DAO.customerDAO;

public class customerShow extends AppCompatActivity {

    private List<customerDAO> mListStudent=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_show);
        mListStudent=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
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
                mListStudent.addAll(response.body().getMessage());
                recycleAdapter.notifyDataSetChanged();
                Toast.makeText(customerShow.this,"Welcome",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<readCustomer> call, Throwable t) {

            }
        });
    }
}
