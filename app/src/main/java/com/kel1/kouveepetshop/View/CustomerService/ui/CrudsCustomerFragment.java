package com.kel1.kouveepetshop.View.CustomerService.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readCustomer;
import com.kel1.kouveepetshop.View.Customer.RecycleAdapter;
import com.kel1.kouveepetshop.View.Customer.RecycleAdapterLog;
import com.kel1.kouveepetshop.View.Customer.customerAdd;
import com.kel1.kouveepetshop.View.Customer.customerMain;
import com.kel1.kouveepetshop.View.Customer.customerShow;
import com.kel1.kouveepetshop.View.ErrorCatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudsCustomerFragment extends Fragment {
    private Switch aSwitch;
    private List<customerDAO> mListCustomer=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private RecycleAdapterLog recycleAdapterLog;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchCustomer;
    private FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_cruds_customer, container, false);

        setAtribut(root);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(root.getContext(), customerAdd.class));
            }
        });

        mListCustomer=new ArrayList<>();
        setRecycleAdapter(root);
        searchCustomer = root.findViewById(R.id.CS_searchCustomer);

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
        setRecycleView(root);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    setRecycleViewLog(root);
                }else{
                    setRecycleView(root);
                }
            }
        });
        if(aSwitch.isChecked()){
            setRecycleViewLog(root);
        }

        return root;
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
    private void setRecycleAdapter(View root){
        recyclerView=root.findViewById(R.id.CS_RC_Customer);
        recycleAdapter=new RecycleAdapter(root.getContext(),mListCustomer);
        recycleAdapterLog=new RecycleAdapterLog(root.getContext(),mListCustomer);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(final View root){

        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readCustomer> customerCall = apiService.getCustomer();
        customerCall.enqueue(new Callback<readCustomer>(){

            @Override
            public void onResponse(Call<readCustomer> call, Response<readCustomer> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapter);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readCustomer> call, Throwable t) {
                Intent intent=new Intent(root.getContext(), ErrorCatch.class);
                startActivity(intent);
            }
        });
    }

    private void setRecycleViewLog(final View root){

        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readCustomer> customerCall = apiService.getCustomerLog();
        customerCall.enqueue(new Callback<readCustomer>(){

            @Override
            public void onResponse(Call<readCustomer> call, Response<readCustomer> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapterLog);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readCustomer> call, Throwable t) {
                Intent intent=new Intent(root.getContext(), ErrorCatch.class);
                startActivity(intent);
            }
        });
    }

    public void setAtribut(View root) {
        fab = root.findViewById(R.id.CS_fab_customer);
        aSwitch = root.findViewById(R.id.CS_switchLogCust);
    }
}
