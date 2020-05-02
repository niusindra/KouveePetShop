package com.kel1.kouveepetshop.View.Hewan;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.DAO.hewanDAO;
import com.kel1.kouveepetshop.DAO.supplierDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readCustomer;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.ErrorCatch;
import com.kel1.kouveepetshop.View.Layanan.layananShow;
import com.kel1.kouveepetshop.View.Produk.RecycleAdapterProduk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hewanEdit extends AppCompatActivity {
    public List<customerDAO> mListCustomer;
    final Calendar myCalendar = Calendar.getInstance();
    public ImageView back;
    public int idcus;
    public Spinner mSpinner;
    public EditText mNamaHewan;
    public EditText mTanggalLahir;
    public Button editBtn;
    public Button delBtn;
    public Button enableNama;
    public Button enableTanggal;
    public String hewan[];
    public int number[];
    public SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hewan_edit);
        session = new SessionManager(getApplicationContext());
        setAtribut();
        getCustomer();
        Intent intent = getIntent();
        hewan = intent.getStringArrayExtra(RecycleAdapterHewan.EXTRA_TEXT);
        number = intent.getIntArrayExtra(RecycleAdapterHewan.EXTRA_NUMBER);
        mSpinner.setSelection(1);
        setText(hewan[0],hewan[1]);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customerDAO user = (customerDAO) parent.getSelectedItem();
                idcus = user.getId_customer();
                Toast.makeText(getApplicationContext(),String.valueOf(idcus),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        mTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(hewanEdit.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        enableNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNamaHewan.isEnabled()){
                    enableNama.setBackgroundResource(R.drawable.lock);
                    mNamaHewan.setEnabled(false);
                }
                else {
                    enableNama.setBackgroundResource(R.drawable.unlocked);
                    mNamaHewan.setEnabled(true);
                }
            }
        });
        enableTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTanggalLahir.isEnabled()){
                    enableTanggal.setBackgroundResource(R.drawable.lock);
                    mTanggalLahir.setEnabled(false);
                }
                else {
                    enableTanggal.setBackgroundResource(R.drawable.unlocked);
                    mTanggalLahir.setEnabled(true);
                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final HashMap<String, String> userDetails = session.getUserDetails();
                AlertDialog.Builder builder = new AlertDialog.Builder(hewanEdit.this);
                builder.setMessage("Anda yakin untuk menghapus data")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                                Call<cudDataMaster> supplierCall = apiService.deleteHewan(number[0],userDetails.get(SessionManager.KEY_NAME));
                                supplierCall.enqueue(new Callback<cudDataMaster>(){
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                                        Toast.makeText(hewanEdit.this,"Berhasil dihapus",Toast.LENGTH_SHORT).show();
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hewanEdit.this, hewanShow.class);
                startActivity(intent);
            }
        });

    }
    private void getCustomer(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readCustomer> layananCall = apiService.getCustomer();
        layananCall.enqueue(new Callback<readCustomer>(){

            @Override
            public void onResponse(Call<readCustomer> call, Response<readCustomer> response) {
                if(response.body()!=null) {
                    mListCustomer = response.body().getMessage();
                    ArrayAdapter<customerDAO> adapter = new ArrayAdapter<customerDAO>(hewanEdit.this,
                            android.R.layout.simple_spinner_item, mListCustomer);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(adapter);

                    for (int i = 0; i < mListCustomer.size(); i++) {
                        if(mListCustomer.get(i).getId_customer()==number[1]){
                            mSpinner.setSelection(i);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<readCustomer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setAtribut (){
        back = findViewById(R.id.backBtnHewanEdit);
        editBtn = findViewById(R.id.editHBtn1);
        mNamaHewan = findViewById(R.id.namaHewanEdit);
        mTanggalLahir = findViewById(R.id.bornDateHewan);
        delBtn = findViewById(R.id.delHBtn1);
        mSpinner = findViewById(R.id.spinnerCustomerHEdit);
        enableNama = findViewById(R.id.enableNamaH);
        enableTanggal = findViewById(R.id.enableTglH);
    }
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mTanggalLahir.setText(sdf.format(myCalendar.getTime()));
    }
    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(), hewanShow.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), hewanShow.class);
        startActivity(intent);
    }
    public void setText(String nama, String tanggal){
        this.mNamaHewan.setText(nama);
        this.mTanggalLahir.setText(tanggal);
    }
    public void upload()
    {
        if(this.mNamaHewan.getText().toString().isEmpty() || this.mTanggalLahir.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
        }else{
            final HashMap<String, String> userDetails = session.getUserDetails();
            int Cid = this.idcus;
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<cudDataMaster> customerCall = apiService.editHewan(number[0],Cid,mNamaHewan.getText().toString(),
                    mTanggalLahir.getText().toString(),userDetails.get(SessionManager.KEY_NAME));
            customerCall.enqueue(new Callback<cudDataMaster>(){
                public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                    Toast.makeText(hewanEdit.this,mNamaHewan.getText().toString(),Toast.LENGTH_SHORT).show();
                    startIntent();
                }
                public void onFailure(Call<cudDataMaster> call, Throwable t){
                    Toast.makeText(hewanEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

