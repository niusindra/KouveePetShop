package com.kel1.kouveepetshop.View.Customer;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class customerEdit extends AppCompatActivity {
    public ImageView back;
    final Calendar myCalendar = Calendar.getInstance();
    public EditText tanggal;
    public EditText nama;
    public EditText alamat;
    public EditText telp;
    public Button editBtn;
    public Button delBtn;
    public Button enableNama;
    public Button enableAlamat;
    public Button enableDate;
    public Button enableTelp;
    public SessionManager session;
    public String customer[];
    public int number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_edit);
        setAtribut();
        Intent intent = getIntent();
        customer = intent.getStringArrayExtra(RecycleAdapter.EXTRA_TEXT);
        number = intent.getIntExtra(RecycleAdapter.EXTRA_NUMBER,0);
        setText(customer[0],customer[1],customer[2],customer[3]);

        session = new SessionManager(getApplicationContext());
        final HashMap<String, String> userDetails = session.getUserDetails();

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
        enableAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alamat.isEnabled())
                {
                    enableAlamat.setBackgroundResource(R.drawable.lock);
                    alamat.setEnabled(false);
                }
                else {
                    enableAlamat.setBackgroundResource(R.drawable.unlocked);
                    alamat.setEnabled(true);
                }
            }
        });
        enableDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tanggal.isEnabled())
                {
                    enableDate.setBackgroundResource(R.drawable.lock);
                    tanggal.setEnabled(false);
                }
                else {
                    enableDate.setBackgroundResource(R.drawable.unlocked);
                    tanggal.setEnabled(true);
                }
            }
        });
        enableTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(telp.isEnabled())
                {
                    enableTelp.setBackgroundResource(R.drawable.lock);
                    telp.setEnabled(false);
                }
                else {
                    enableTelp.setBackgroundResource(R.drawable.unlocked);
                    telp.setEnabled(true);
                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().isEmpty() || alamat.getText().toString().isEmpty() || tanggal.getText().toString().isEmpty() || telp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
                }else{

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<cudDataMaster> customerCall = apiService.editCustomer(number,nama.getText().toString(),
                            alamat.getText().toString(),tanggal.getText().toString(),
                            telp.getText().toString(),userDetails.get(SessionManager.KEY_NAME));
                    customerCall.enqueue(new Callback<cudDataMaster>(){
                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                            Toast.makeText(customerEdit.this,"Berhasil edit",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                        public void onFailure(Call<cudDataMaster> call, Throwable t){
                            Toast.makeText(customerEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                    });
                }
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerEdit.this);

                builder.setMessage("Anda yakin untuk menghapus data")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                                Call<cudDataMaster> customerCall = apiService.deleteCustomer(number,userDetails.get(SessionManager.KEY_NAME));
                                customerCall.enqueue(new Callback<cudDataMaster>(){
                                    public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                                        Toast.makeText(customerEdit.this,"Berhasil dihapus",Toast.LENGTH_SHORT).show();
                                        startIntent();
                                    }
                                    public void onFailure(Call<cudDataMaster> call, Throwable t){
                                        Toast.makeText(customerEdit.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(customerEdit.this, customerShow.class);
                startActivity(intent);
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

        tanggal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(customerEdit.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tanggal.setText(sdf.format(myCalendar.getTime()));
    }
    public void setAtribut (){
        back = findViewById(R.id.backBtn1);
        tanggal = findViewById(R.id.dateTxt1);
        editBtn = findViewById(R.id.editBtn1);
        nama = findViewById(R.id.namaTxt1);
        alamat = findViewById(R.id.alamatTxt1);
        telp = findViewById(R.id.telpTxt1);
        delBtn = findViewById(R.id.delBtn1);
        enableNama = findViewById(R.id.enableNama1);
        enableAlamat = findViewById(R.id.enableAlamat1);
        enableDate = findViewById(R.id.enableDate1);
        enableTelp = findViewById(R.id.enableTelp1);
    }

    public void setText(String nama, String alamat, String tgllahir, String telp){
        this.nama.setText(nama);
        this.alamat.setText(alamat);
        this.tanggal.setText(tgllahir);
        this.telp.setText(telp);
    }

    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(),customerShow.class);
        startActivity(intent);
    }
}
