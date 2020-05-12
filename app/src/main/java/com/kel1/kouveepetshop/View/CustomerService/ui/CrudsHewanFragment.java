package com.kel1.kouveepetshop.View.CustomerService.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.hewanDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readHewan;
import com.kel1.kouveepetshop.View.Hewan.RecycleAdapterHewan;
import com.kel1.kouveepetshop.View.Hewan.RecycleAdapterHewanLog;
import com.kel1.kouveepetshop.View.Hewan.hewanAdd;
import com.kel1.kouveepetshop.View.Hewan.hewanMain;
import com.kel1.kouveepetshop.View.Hewan.hewanShow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudsHewanFragment extends Fragment {
    public ImageView back;
    public Switch aSwitch;
    private List<hewanDAO> mListCustomer;
    private RecyclerView recyclerView;
    private RecycleAdapterHewan recycleAdapter;
    private RecycleAdapterHewanLog recycleAdapterHewanLog;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchCustomer;
    private FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_cruds_hewan, container, false);

        setAtribut(root);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(root.getContext(), hewanAdd.class));
            }
        });
        mListCustomer=new ArrayList<>();
        setRecycleAdapter(root);
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
        List<hewanDAO> filteredList = new ArrayList<>();
        for (hewanDAO item : mListCustomer) {
            if (item.getNama_hewan().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterHewanLog.filterList(filteredList);
    }
    private void setRecycleAdapter(View root){
        recyclerView=root.findViewById(R.id.CS_RC_Hewan);
        recycleAdapter=new RecycleAdapterHewan(root.getContext(),mListCustomer);
        recycleAdapterHewanLog=new RecycleAdapterHewanLog(root.getContext(),mListCustomer);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(final View root){

        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readHewan> hewanCall = apiService.getHewan();
        hewanCall.enqueue(new Callback<readHewan>(){

            @Override
            public void onResponse(Call<readHewan> call, Response<readHewan> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapter);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readHewan> call, Throwable t) {
                Toast.makeText(root.getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setRecycleViewLog(final View root){

        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<readHewan> hewanCall = apiService.getHewanLog();
        hewanCall.enqueue(new Callback<readHewan>(){

            @Override
            public void onResponse(Call<readHewan> call, Response<readHewan> response) {
                if(response.body()!=null) {
                    mListCustomer.clear();
                    mListCustomer.addAll(response.body().getMessage());
                    recyclerView.setAdapter(recycleAdapterHewanLog);
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<readHewan> call, Throwable t) {
                Toast.makeText(root.getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent=new Intent(getApplicationContext(), hewanMain.class);
//        startActivity(intent);
//    }
    public void setAtribut(View root) {
        aSwitch = root.findViewById(R.id.CS_switchLogHewan);
        searchCustomer = root.findViewById(R.id.CS_searchHewan);
        fab = root.findViewById(R.id.CS_fab_hewan);
    }
}
