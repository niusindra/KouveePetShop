package com.kel1.kouveepetshop.View.Hewan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readCustomer;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.ErrorCatch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hewanAdd extends AppCompatActivity {
    public List<customerDAO> mListCostumer;
    public EditText nama;
    public Button add;
    public EditText tanggal;
    final Calendar myCalendar = Calendar.getInstance();
    public Spinner mSpinner;
    public int idcus;
    public SessionManager session;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hewan_add);
        setAtribut();
        session = new SessionManager(getApplicationContext());
        getCustomer();
        mSpinner.setSelection(1);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customerDAO user = (customerDAO) parent.getSelectedItem();
                idcus=user.getId_customer();
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
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(hewanAdd.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }
    public void upload()
    {
        if(this.nama.getText().toString().isEmpty() || this.tanggal.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Data harus terisi semua!", Toast.LENGTH_SHORT).show();
        }else{
            final HashMap<String, String> userDetails = session.getUserDetails();
            int Cid = this.idcus;
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<cudDataMaster> layananCall = apiService.addHewan(Cid,this.nama.getText().toString(),
                    this.tanggal.getText().toString(),userDetails.get(SessionManager.KEY_NAME));
            layananCall.enqueue(new Callback<cudDataMaster>(){
                public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response){
                    Toast.makeText(hewanAdd.this,"Berhasil Ditambah",Toast.LENGTH_SHORT).show();
                }
                public void onFailure(Call<cudDataMaster> call, Throwable t){
                    Toast.makeText(hewanAdd.this,"Masalah koneksi",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tanggal.setText(sdf.format(myCalendar.getTime()));
    }
    public void setAtribut(){
        add = findViewById(R.id.addHBtn);
        nama = findViewById(R.id.namaHTxt);
        tanggal = findViewById(R.id.dateHewanTxt);
        mSpinner = findViewById(R.id.customerSpin);
    }
    private void getCustomer(){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readCustomer> customerCall = apiService.getCustomer();
        customerCall.enqueue(new Callback<readCustomer>(){

            @Override
            public void onResponse(Call<readCustomer> call, Response<readCustomer> response) {
                if(response.body()!=null) {
                    mListCostumer = response.body().getMessage();
                    ArrayAdapter<customerDAO> adapter = new ArrayAdapter<customerDAO>(hewanAdd.this,
                            android.R.layout.simple_spinner_item, mListCostumer);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<readCustomer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

