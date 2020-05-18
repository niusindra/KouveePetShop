package com.kel1.kouveepetshop.View.CustomerService.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.transaksiLayananDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readTransLay;
import com.kel1.kouveepetshop.View.ErrorCatch;
import com.kel1.kouveepetshop.View.TransaksiLayanan.RecycleAdapterTransLayShow;
import com.kel1.kouveepetshop.View.TransaksiLayanan.RecycleAdapterTransLayShowLog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabsSelesai extends Fragment {
    private List<transaksiLayananDAO> mListTransLay =new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapterTransLayShow recycleAdapter;
    private RecycleAdapterTransLayShowLog recycleAdapterLog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.tabs_selesai_fragment, container, false);

        mListTransLay =new ArrayList<>();
        setRecycleAdapter(root);
        setRecycleView(root);

        return root;
    }

    public void filter(String text) {

        List<transaksiLayananDAO> filteredList ,temp;
        filteredList = new ArrayList<>();
        temp = recycleAdapter.getResult();
        for (transaksiLayananDAO item : temp) {
            if (item.getId_trans_layanan().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterLog.filterList(filteredList);
    }
    public void setListPengadaan(String text) {
        List<transaksiLayananDAO> filteredList = new ArrayList<>();
        for (transaksiLayananDAO item : mListTransLay) {
            if (item.getId_trans_layanan().equalsIgnoreCase(text)) {
                filteredList.add(item);
            }
        }

        recycleAdapter.setResult(filteredList);
        recycleAdapterLog.setResult(filteredList);
    }
    public void setRecycleAdapter(View root){
        recyclerView=root.findViewById(R.id.CS_RC_translay);
        recycleAdapter=new RecycleAdapterTransLayShow(root.getContext(), mListTransLay);
        recycleAdapterLog=new RecycleAdapterTransLayShowLog(root.getContext(), mListTransLay);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void setRecycleView(final View root){

        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readTransLay> TransLayCall = apiService.getTransLaySelesai();
        TransLayCall.enqueue(new Callback<readTransLay>(){

            @Override
            public void onResponse(Call<readTransLay> call, Response<readTransLay> response) {
                if(response.body()!=null) {
                    mListTransLay.clear();
                    mListTransLay.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapter);
                    recycleAdapter.notifyDataSetChanged();
                    if(mListTransLay.size()==0)
                        Toast.makeText(root.getContext(),"Belum ada transaksi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<readTransLay> call, Throwable t) {
                Intent intent=new Intent(root.getContext(), ErrorCatch.class);
                startActivity(intent);
            }
        });
    }

    public void setRecycleViewLog(final View root){

        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readTransLay> pengadaanCallCall = apiService.getTransLayLog();
        pengadaanCallCall.enqueue(new Callback<readTransLay>(){

            @Override
            public void onResponse(Call<readTransLay> call, Response<readTransLay> response) {
                if(response.body()!=null) {
                    mListTransLay.clear();
                    mListTransLay.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapterLog);
                    recycleAdapter.notifyDataSetChanged();
                    if(mListTransLay.size()==0)
                        Toast.makeText(root.getContext(),"Belum ada transaksi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<readTransLay> call, Throwable t) {
                Intent intent=new Intent(root.getContext(), ErrorCatch.class);
                startActivity(intent);
            }
        });
    }
}
