package com.kel1.kouveepetshop.View.Produk;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
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
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readSupplier;
import com.kel1.kouveepetshop.View.ErrorCatch;

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

public class produkAdd extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    public ImageView back;
    public EditText nama;
    public int idsup;
    public EditText beli;
    public EditText jual;
    public EditText stok;
    public EditText minstok;
    public Spinner mSpinner;
    public ImageView mImageView;
    public Button uploadBtn;
    public Button addBtn;

    public List<supplierDAO> mListSupplier;

    private Uri filePath;

    private Bitmap bitmap;

    private static final int STORAGE_PERMISSION_CODE = 123;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produk_add);
        setAtribut();
        getSupplier();
        mSpinner.setSelection(1);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                supplierDAO user = (supplierDAO) parent.getSelectedItem();
                idsup=user.getId_supplier();
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(produkAdd.this, produkMain.class);
                startActivity(intent);
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
        addBtn = findViewById(R.id.addProdukBtn);
        mSpinner = findViewById(R.id.supplierSpin);
        mImageView = findViewById(R.id.showImageUload);
        back = findViewById(R.id.backBtnProduk);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), produkMain.class);
        startActivity(intent);
    }
    private void getSupplier(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readSupplier> layananCall = apiService.getSupplier();
        layananCall.enqueue(new Callback<readSupplier>(){

            @Override
            public void onResponse(Call<readSupplier> call, Response<readSupplier> response) {
                if(response.body()!=null) {
                    mListSupplier = response.body().getMessage();
                    ArrayAdapter<supplierDAO> adapter = new ArrayAdapter<supplierDAO>(produkAdd.this,
                            R.layout.spinner_item, mListSupplier);
                    adapter.setDropDownViewResource(R.layout.spinner_item);

                    mSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<readSupplier> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void uploadMultipart(File file) {
        if(nama.getText().toString().isEmpty() || nama.getText().toString().isEmpty() || beli.getText().toString().isEmpty() ||
                jual.getText().toString().isEmpty() || stok.getText().toString().isEmpty() || minstok.getText().toString().isEmpty() ||
                file == null){
            Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
        }else{
            RequestBody photoBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part photoPart = MultipartBody.Part.createFormData("foto_produk", file.getName(), photoBody);
            RequestBody Rnama = RequestBody.create(MediaType.parse("text/plain"), this.nama.getText().toString());
            int Rid = this.idsup ;
            int Rbeli = Integer.parseInt(this.beli.getText().toString()) ;
            int Rjual = Integer.parseInt(this.jual.getText().toString());
            int Rstok = Integer.parseInt(this.stok.getText().toString());
            int Rminstok = Integer.parseInt(this.minstok.getText().toString());

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            final Call<cudDataMaster> customerCall = apiService.addProduk(Rid,Rnama,photoPart,Rbeli,Rjual,Rstok,Rminstok);
            customerCall.enqueue(new Callback<cudDataMaster>(){
                public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                    try {
                        if(response.body()!=null){
                            if(response.body().getNama_produk()!=null)
                                Toast.makeText(produkAdd.this,response.body().getNama_produk(),Toast.LENGTH_LONG).show();
                            else{
                                Toast.makeText(produkAdd.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                                startIntent();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                public void onFailure(Call<cudDataMaster> call, Throwable t){
                    customerCall.request();
                    Intent intent=new Intent(getApplicationContext(), ErrorCatch.class);
                    startActivity(intent);
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

