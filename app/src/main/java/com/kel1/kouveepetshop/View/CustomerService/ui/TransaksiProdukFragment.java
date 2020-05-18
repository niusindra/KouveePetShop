package com.kel1.kouveepetshop.View.CustomerService.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.transaksiProdukDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readTransPro;
import com.kel1.kouveepetshop.View.Pengadaan.pengadaanShow;
import com.kel1.kouveepetshop.View.TransaksiProduk.RecycleAdapterTransProShowLog;
import com.kel1.kouveepetshop.View.TransaksiProduk.RecycleAdapterTransProShow;
import com.kel1.kouveepetshop.View.ErrorCatch;
import com.kel1.kouveepetshop.View.TransaksiProduk.transProAdd;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiProdukFragment extends Fragment {
    FloatingActionButton fabtranspro;
    private Switch aSwitch;
    private List<transaksiProdukDAO> mListTransPro =new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapterTransProShow recycleAdapter;
    private RecycleAdapterTransProShowLog recycleAdapterLog;
    private EditText searchTransPro;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_transaksi_produk, container, false);

        setAtribut(root);

        mListTransPro =new ArrayList<>();
        setRecycleAdapter(root);
        setRecycleView(root);

        fabtranspro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setMessage("Siliahkan pilih jenis member:")
                        .setCancelable(false)
                        .setPositiveButton("MEMBER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(root.getContext(), transProAdd.class);
                                intent.putExtra("cekMember","member");
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("NON MEMBER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(root.getContext(), transProAdd.class);
                                intent.putExtra("cekMember","non member");
                                startActivity(intent);
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        searchTransPro.addTextChangedListener(new TextWatcher() {
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
        }else{
            setRecycleView(root);
        }

        return root;
    }
    private void filter(String text) {

        List<transaksiProdukDAO> filteredList ,temp;
        filteredList = new ArrayList<>();
        temp = recycleAdapter.getResult();
        for (transaksiProdukDAO item : temp) {
            if (item.getId_trans_produk().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterLog.filterList(filteredList);
    }
    private void setListPengadaan(String text) {
        List<transaksiProdukDAO> filteredList = new ArrayList<>();
        for (transaksiProdukDAO item : mListTransPro) {
            if (item.getId_trans_produk().equalsIgnoreCase(text)) {
                filteredList.add(item);
            }
        }

        recycleAdapter.setResult(filteredList);
        recycleAdapterLog.setResult(filteredList);
    }
    private void setRecycleAdapter(View root){
        recyclerView=root.findViewById(R.id.CS_RC_transpro);
        recycleAdapter=new RecycleAdapterTransProShow(root.getContext(), mListTransPro);
        recycleAdapterLog=new RecycleAdapterTransProShowLog(root.getContext(), mListTransPro);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(final View root){

        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readTransPro> pengadaanCallCall = apiService.getTransPro();
        pengadaanCallCall.enqueue(new Callback<readTransPro>(){

            @Override
            public void onResponse(Call<readTransPro> call, Response<readTransPro> response) {
                if(response.body()!=null) {
                    mListTransPro.clear();
                    mListTransPro.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapter);
                    recycleAdapter.notifyDataSetChanged();
                    if(mListTransPro.size()==0)
                        Toast.makeText(root.getContext(),"Belum ada transaksi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<readTransPro> call, Throwable t) {
                Intent intent=new Intent(root.getContext(), ErrorCatch.class);
                startActivity(intent);
            }
        });
    }

    private void setRecycleViewLog(final View root){

        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readTransPro> pengadaanCallCall = apiService.getTransProLog();
        pengadaanCallCall.enqueue(new Callback<readTransPro>(){

            @Override
            public void onResponse(Call<readTransPro> call, Response<readTransPro> response) {
                if(response.body()!=null) {
                    mListTransPro.clear();
                    mListTransPro.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapterLog);
                    recycleAdapter.notifyDataSetChanged();
                    if(mListTransPro.size()==0)
                        Toast.makeText(root.getContext(),"Belum ada transaksi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<readTransPro> call, Throwable t) {
                Intent intent=new Intent(root.getContext(), ErrorCatch.class);
                startActivity(intent);
            }
        });
    }

    public void setAtribut(View root) {
        fabtranspro = root.findViewById(R.id.CS_fab_transpro);
        aSwitch = root.findViewById(R.id.CS_switchLog_transpro);
        searchTransPro = root.findViewById(R.id.CS_search_transpro);
    }
}
