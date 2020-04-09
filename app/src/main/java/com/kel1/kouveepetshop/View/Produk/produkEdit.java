package com.kel1.kouveepetshop.View.Produk;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.supplierDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudCustomer;
import com.kel1.kouveepetshop.Respon.readSupplier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class produkEdit extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    public ImageView back;
    public TextView namaSupplier;
    public EditText nama;
    public EditText beli;
    public EditText jual;
    public EditText stok;
    public EditText minstok;
    public Spinner mSpinner;
    public ImageView mImageView;
    public Button uploadBtn;
    public Button addBtn;

    private List<supplierDAO> mListSupplier;

    private Uri filePath;

    private Bitmap bitmap;

    private static final int STORAGE_PERMISSION_CODE = 123;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produk_add);

        setAtribut();
        getSupplier();

        ArrayAdapter<supplierDAO> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mListSupplier);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                supplierDAO user = (supplierDAO) parent.getItemAtPosition(position);
                displayUserData(user);
                namaSupplier.setText(user.getNama_supplier());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestStoragePermission();
                File file = FileUtils.getFile(getApplicationContext(), filePath);
                uploadMultipart(file);
            }
        });
    }

    public void setAtribut(){
        namaSupplier = findViewById(R.id.namaSupplierTxt);
        nama = findViewById(R.id.namaProdukTxt);
        beli = findViewById(R.id.beliProdukTxt);
        jual = findViewById(R.id.jualProdukTxt);
        stok = findViewById(R.id.stokProduk);
        minstok = findViewById(R.id.minstokProduk);
        uploadBtn = findViewById(R.id.addFotoBtn);
        addBtn = findViewById(R.id.addProdukBtn);
        mSpinner = findViewById(R.id.supplierSpin);
        mImageView = findViewById(R.id.showImageUload);
    }

    private void getSupplier(){
        mListSupplier=new ArrayList<>();
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

    private void displayUserData(supplierDAO user) {
        String name = user.getNama_supplier();

        String userData = "Name: " + name;

        Toast.makeText(this, userData, Toast.LENGTH_LONG).show();
    }

    public void uploadMultipart(File file) {
        if(nama.getText().toString().isEmpty() || nama.getText().toString().isEmpty() || beli.getText().toString().isEmpty() ||
                jual.getText().toString().isEmpty() || stok.getText().toString().isEmpty() || minstok.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
        }else{
            RequestBody photoBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part photoPart = MultipartBody.Part.createFormData("foto_produk", file.getName(), photoBody);
            RequestBody Rnama = RequestBody.create(MediaType.parse("text/plain"), this.nama.getText().toString());
            int Rbeli = Integer.parseInt(this.beli.getText().toString()) ;
            int Rjual = Integer.parseInt(this.jual.getText().toString());
            int Rstok = Integer.parseInt(this.stok.getText().toString());
            int Rminstok = Integer.parseInt(this.minstok.getText().toString());

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<cudCustomer> customerCall = apiService.addProduk(1,Rnama,photoPart,Rbeli,Rjual,Rstok,Rminstok);
            customerCall.enqueue(new Callback<cudCustomer>(){
                public void onResponse(Call<cudCustomer> call, Response<cudCustomer> response){
                    Toast.makeText(produkEdit.this,"Berhasil tambah",Toast.LENGTH_SHORT).show();
                    startIntent();
                }
                public void onFailure(Call<cudCustomer> call, Throwable t){
                    Toast.makeText(produkEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(),produkShow.class);
        startActivity(intent);
    }
}

