package com.kel1.kouveepetshop.View.Customer;

import android.app.DatePickerDialog;
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


public class customerAdd extends AppCompatActivity {
    //aaa
    public ImageView back;
    public EditText tanggal;
    public EditText nama;
    public EditText alamat;
    public EditText telp;
    final Calendar myCalendar = Calendar.getInstance();
    public Button addBtn;
    public SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_add);
        setAtribut();
        session = new SessionManager(getApplicationContext());
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().isEmpty() || alamat.getText().toString().isEmpty() || tanggal.getText().toString().isEmpty() || telp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
                }else{
                    final HashMap<String, String> userDetails = session.getUserDetails();
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<cudDataMaster> customerCall = apiService.addCustomer(nama.getText().toString(),
                            alamat.getText().toString(),tanggal.getText().toString(),
                            telp.getText().toString(),userDetails.get(SessionManager.KEY_NAME));
                    customerCall.enqueue(new Callback<cudDataMaster>(){
                        public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                            Toast.makeText(customerAdd.this,"Berhasil tambah",Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                        public void onFailure(Call<cudDataMaster> call, Throwable t){
                            Toast.makeText(customerAdd.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customerAdd.this, customerMain.class);
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
                new DatePickerDialog(customerAdd.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    public void setAtribut (){
        back = findViewById(R.id.backBtn);
        tanggal = findViewById(R.id.dateTxt);
        addBtn = findViewById(R.id.addBtn);
        nama = findViewById(R.id.namaTxt);
        alamat = findViewById(R.id.alamatTxt);
        telp = findViewById(R.id.telpTxt);
    }
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tanggal.setText(sdf.format(myCalendar.getTime()));
    }
    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(),customerShow.class);
        startActivity(intent);
    }
}
