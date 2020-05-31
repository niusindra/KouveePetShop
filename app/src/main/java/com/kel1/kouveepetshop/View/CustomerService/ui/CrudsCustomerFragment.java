package com.kel1.kouveepetshop.View.CustomerService.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
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

public class CrudsCustomerFragment extends Fragment{
    private List<customerDAO> mListCustomer=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private RecycleAdapterLog recycleAdapterLog;
    private RecyclerView.LayoutManager layoutManager;
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
        setRecycleView(root);

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = (SearchView) item.getActionView();
//        sv.setOnQueryTextListener(this);
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void setAtribut(View root) {
        fab = root.findViewById(R.id.CS_fab_customer);
    }
}
