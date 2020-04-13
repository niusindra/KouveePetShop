package com.kel1.kouveepetshop.View.Produk;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readSupplier;
import com.kel1.kouveepetshop.View.ErrorCatch;
import com.kel1.kouveepetshop.View.Layanan.RecycleAdapterLayanan;
import com.kel1.kouveepetshop.View.Layanan.layananEdit;
import com.squareup.picasso.Picasso;

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
    public EditText nama;
    public TextView idsup;
    public EditText beli;
    public EditText jual;
    public EditText stok;
    public EditText minstok;
    public Spinner mSpinner;
    public ImageView mImageView;
    public Button uploadBtn;
    public Button editBtn;
    public Button delBtn;
    public Button enableNama;
    public Button enableHB;
    public Button enableHJ;
    public Button enableStok;
    public Button enableMinStok;

    public List<supplierDAO> mListSupplier;

    private Uri filePath;

    private Bitmap bitmap;

    private static final int STORAGE_PERMISSION_CODE = 123;

    public String produk[];
    public int number[];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produk_edit);

        Intent intent = getIntent();
        produk = intent.getStringArrayExtra(RecycleAdapterProduk.EXTRA_TEXT);
        number = intent.getIntArrayExtra(RecycleAdapterProduk.EXTRA_NUMBER);
        setAtribut();
        setText(produk[0],produk[1],String.valueOf(number[1]),String.valueOf(number[2]),String.valueOf(number[3]),String.valueOf(number[4]),String.valueOf(number[5]));
        getSupplier();

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                supplierDAO user = (supplierDAO) parent.getSelectedItem();
                displayUserData(user);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
            public void displayUserData(supplierDAO user) {
                int id = user.getId_supplier();
                String userData = String.valueOf(id);
                idsup.setText(userData);
            }
        });
        enableNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nama.isEnabled()){
                    enableNama.setBackgroundResource(R.drawable.lock);
                    nama.setEnabled(false);
                }
                else {
                    enableNama.setBackgroundResource(R.drawable.unlocked);
                    nama.setEnabled(true);
                }
            }
        });
        enableHB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(beli.isEnabled()){
                    enableHB.setBackgroundResource(R.drawable.lock);
                    beli.setEnabled(false);
                }
                else {
                    enableHB.setBackgroundResource(R.drawable.unlocked);
                    beli.setEnabled(true);
                }
            }
        });
        enableHJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jual.isEnabled()){
                    enableHJ.setBackgroundResource(R.drawable.lock);
                    jual.setEnabled(false);
                }
                else {
                    enableHJ.setBackgroundResource(R.drawable.unlocked);
                    jual.setEnabled(true);
                }
            }
        });
        enableStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stok.isEnabled()){
                    enableStok.setBackgroundResource(R.drawable.lock);
                    stok.setEnabled(false);
                }
                else {
                    enableStok.setBackgroundResource(R.drawable.unlocked);
                    stok.setEnabled(true);
                }
            }
        });
        enableMinStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(minstok.isEnabled()){
                    enableMinStok.setBackgroundResource(R.drawable.lock);
                    minstok.setEnabled(false);
                }
                else {
                    enableMinStok.setBackgroundResource(R.drawable.unlocked);
                    minstok.setEnabled(true);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(produkEdit.this, produkShow.class);
                startActivity(intent);
            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestStoragePermission();
                File file = FileUtils.getFile(getApplicationContext(), filePath);
                uploadMultipart(file);
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(produkEdit.this);

                builder.setMessage("Anda yakin untuk menghapus data")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                                Call<cudDataMaster> supplierCall = apiService.deleteProduk(number[0]);
                                supplierCall.enqueue(new Callback<cudDataMaster>(){
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                                        Toast.makeText(produkEdit.this,"Berhasil dihapus",Toast.LENGTH_SHORT).show();
                                        startIntent();
                                    }
                                    public void onFailure(Call<cudDataMaster> call, Throwable t){
                                        Intent intent=new Intent(getApplicationContext(), ErrorCatch.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void setAtribut(){
        enableNama = findViewById(R.id.enableproNama1);
        enableHB = findViewById(R.id.enableproHB1);
        enableHJ = findViewById(R.id.enableproHJ1);
        enableStok = findViewById(R.id.enableproStok1);
        enableMinStok = findViewById(R.id.enableproMinStok1);
        idsup = findViewById(R.id.idSup1);
        nama = findViewById(R.id.namaProdukTxt1);
        beli = findViewById(R.id.beliProdukTxt1);
        jual = findViewById(R.id.jualProdukTxt1);
        stok = findViewById(R.id.stokProduk1);
        minstok = findViewById(R.id.minstokProduk1);
        uploadBtn = findViewById(R.id.addFotoBtn1);
        editBtn = findViewById(R.id.editProdukBtn1);
        delBtn = findViewById(R.id.delProdukBtn1);
        mSpinner = findViewById(R.id.supplierSpin1);
        mImageView = findViewById(R.id.showImageUload1);
        back = findViewById(R.id.backBtnProduk1);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), produkMain.class);
        startActivity(intent);
    }
    private void getSupplier(){
        mListSupplier=new ArrayList<>();
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readSupplier> layananCall = apiService.getSupplier();
        layananCall.enqueue(new Callback<readSupplier>(){

            @Override
            public void onResponse(Call<readSupplier> call, Response<readSupplier> response) {
                if(response.body()!=null) {
                    List<supplierDAO> supplieritems = response.body().getMessage();
                    ArrayAdapter<supplierDAO> adapter = new ArrayAdapter<supplierDAO>(produkEdit.this,
                            android.R.layout.simple_spinner_item, supplieritems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
                jual.getText().toString().isEmpty() || stok.getText().toString().isEmpty() || minstok.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
        }else{
            MultipartBody.Part photoPart;
            RequestBody photoBody;
            if(file != null){
                photoBody = RequestBody.create(MediaType.parse("image/*"), file);
                photoPart = MultipartBody.Part.createFormData("foto_produk", file.getName(), photoBody);
            }else{
                photoPart = null;
            }

            RequestBody Rnama = RequestBody.create(MediaType.parse("text/plain"), this.nama.getText().toString());
            int Rid = Integer.parseInt(this.idsup.getText().toString()) ;
            int Rbeli = Integer.parseInt(this.beli.getText().toString()) ;
            int Rjual = Integer.parseInt(this.jual.getText().toString());
            int Rstok = Integer.parseInt(this.stok.getText().toString());
            int Rminstok = Integer.parseInt(this.minstok.getText().toString());

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<cudDataMaster> customerCall = apiService.editProduk(number[0],Rid,Rnama,photoPart,Rbeli,Rjual,Rstok,Rminstok);
            customerCall.enqueue(new Callback<cudDataMaster>(){
                public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                    Toast.makeText(produkEdit.this,"Berhasil tambah",Toast.LENGTH_SHORT).show();
                    startIntent();
                }
                public void onFailure(Call<cudDataMaster> call, Throwable t){
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


    public void setText(String nama, String foto, String idsup, String beli, String jual, String stok, String minstok){
        this.nama.setText(nama);
        this.idsup.setText(idsup);
        this.beli.setText(beli);
        this.jual.setText(jual);
        this.stok.setText(stok);
        this.minstok.setText(minstok);
        Picasso.get().load("http:/api.kouvee.site/upload/foto_produk/"+foto).into(this.mImageView);
    }
}

