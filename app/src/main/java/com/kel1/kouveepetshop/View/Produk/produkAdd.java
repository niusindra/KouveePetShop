package com.kel1.kouveepetshop.View.Produk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.supplierDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readSupplier;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class produkAdd extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    public ImageView back;
    public EditText nama;
    public EditText beli;
    public EditText jual;
    public EditText stok;
    public EditText minstok;
    public Spinner mSpinner;
    public ImageView mImageView;
    public Button uploadBtn;
    public Button addBtn;

    private List<supplierDAO> mListSupplier=new ArrayList<>();

    private Uri mImageUri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produk_add);
        setAtribut();
        getSupplier();

        ArrayAdapter<supplierDAO> adapter = new ArrayAdapter<supplierDAO>(this,
                android.R.layout.simple_spinner_item, mListSupplier);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                supplierDAO user = (supplierDAO) parent.getSelectedItem();
                displayUserData(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
    }

    public void setAtribut(){
        nama = findViewById(R.id.namaProdukTxt);
        beli = findViewById(R.id.beliProdukTxt);
        jual = findViewById(R.id.jualProdukTxt);
        stok = findViewById(R.id.stokProduk);
        minstok = findViewById(R.id.minstokProduk);
        uploadBtn = findViewById(R.id.addFotoBtn);
        mSpinner = findViewById(R.id.supplierSpin);
    }

    public void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private void getSupplier(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readSupplier> layananCall = apiService.getSupplier();
        layananCall.enqueue(new Callback<readSupplier>(){

            @Override
            public void onResponse(Call<readSupplier> call, Response<readSupplier> response) {
                if(response.body()!=null) {
                    mListSupplier.addAll(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<readSupplier> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSelectedUser(View v) {
        supplierDAO user = (supplierDAO) mSpinner.getSelectedItem();
        displayUserData(user);
    }

    private void displayUserData(supplierDAO user) {
        String name = user.getNama_supplier();

        String userData = "Name: " + name;

        Toast.makeText(this, userData, Toast.LENGTH_LONG).show();
    }
}

