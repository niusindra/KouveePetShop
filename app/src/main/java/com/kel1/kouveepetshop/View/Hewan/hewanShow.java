package com.kel1.kouveepetshop.View.Hewan;

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
import com.kel1.kouveepetshop.DAO.hewanDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readHewan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hewanShow  extends AppCompatActivity {

    public ImageView back;
    public Switch aSwitch;
    private List<hewanDAO> mListCustomer;
    private RecyclerView recyclerView;
    private RecycleAdapterHewan recycleAdapter;
    private RecycleAdapterHewanLog recycleAdapterHewanLog;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.hewan_show);
        setAtribut();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hewanShow.this, hewanMain.class);
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
        List<hewanDAO> filteredList = new ArrayList<>();
        for (hewanDAO item : mListCustomer) {
            if (item.getNama_hewan().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterHewanLog.filterList(filteredList);
    }
    private void setRecycleAdapter(){
        recyclerView=findViewById(R.id.RC_Hewan);
        recycleAdapter=new RecycleAdapterHewan(this,mListCustomer);
        recycleAdapterHewanLog=new RecycleAdapterHewanLog(this,mListCustomer);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRecycleView(){

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
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setRecycleViewLog(){

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
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), hewanMain.class);
        startActivity(intent);
    }
    public void setAtribut() {
        back = findViewById(R.id.btnBackHewanShow);
        aSwitch = findViewById(R.id.switchLogHewan);
        searchCustomer = findViewById(R.id.searchHewan);
    }
}
