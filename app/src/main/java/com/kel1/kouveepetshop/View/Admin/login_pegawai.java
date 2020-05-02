package com.kel1.kouveepetshop.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.EmailDialog;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.verifyPegawai;
import com.kel1.kouveepetshop.SessionManager;
import com.kel1.kouveepetshop.View.CustomerService.dashboardCS;
import com.kel1.kouveepetshop.View.ErrorCatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_pegawai extends AppCompatActivity {

    public Button Login;
    public TextView signUp;
    public EditText username,password;
    public SessionManager session;
    private String newToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pegawai);
        setAtribut ();
        session = new SessionManager(getApplicationContext());

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("Username tidak boleh kosong!");
                    return;
                }else if (TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Password tidak boleh kosong!");
                    return;
                }else {
                    FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(login_pegawai.this, new OnSuccessListener<InstanceIdResult>() {
                        @Override
                        public void onSuccess(InstanceIdResult instanceIdResult) {

                            newToken = instanceIdResult.getToken();
                            Log.e("newToken",newToken);
                        }
                    });

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<verifyPegawai> userDAOCall = apiService.verifyPegawai(username.getText().toString(),password.getText().toString());
                    userDAOCall.enqueue(new Callback<verifyPegawai>() {
                        @Override
                        public void onResponse(Call<verifyPegawai> call, Response<verifyPegawai> response) {
                            if (response.body() != null) {
                                if(response.body().getError_login()!=null)
                                    Toast.makeText(getApplicationContext(),response.body().getError_login(),Toast.LENGTH_SHORT).show();
                                else {
                                    if(response.body().getRole_pegawai().equalsIgnoreCase("admin")) {
                                        session.createLoginSession(response.body().getNama_pegawai(),response.body().getId_pegawai());
                                        Intent i = new Intent(getApplicationContext(), landingPage.class);
                                        startActivity(i);
                                        Toast.makeText(getApplicationContext(),"Login admin sukses",Toast.LENGTH_SHORT).show();

                                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                                        Call<cudDataMaster> deviceCall = apiService.addDevice(response.body().getRole_pegawai(),newToken);
                                        deviceCall.enqueue(new Callback<cudDataMaster>() {
                                            @Override
                                            public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                                Log.e("Sukses",response.body().getToken());
                                            }

                                            @Override
                                            public void onFailure(Call<cudDataMaster> call, Throwable t) {
                                                Log.e("Gagal","Gagal Tambah Device");
                                            }
                                        });

                                        finish();
                                    }else if(response.body().getRole_pegawai().equalsIgnoreCase("customer service")){
                                        session.createLoginSession(response.body().getNama_pegawai(),response.body().getId_pegawai());
                                        Intent i = new Intent(getApplicationContext(), dashboardCS.class);
                                        startActivity(i);
                                        Toast.makeText(getApplicationContext(),"Login CS sukses ",Toast.LENGTH_SHORT).show();

                                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                                        Call<cudDataMaster> deviceCall = apiService.addDevice(response.body().getRole_pegawai(),newToken);
                                        deviceCall.enqueue(new Callback<cudDataMaster>() {
                                            @Override
                                            public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                                Log.e("Sukses",response.body().getToken());
                                            }

                                            @Override
                                            public void onFailure(Call<cudDataMaster> call, Throwable t) {
                                                Log.e("Gagal","Gagal Tambah Device");
                                            }
                                        });

                                        finish();
                                    }else if(response.body().getRole_pegawai().equalsIgnoreCase("kasir")){
                                        Toast.makeText(getApplicationContext(),"Kasir tidak memiliki hak akses",Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<verifyPegawai> call, Throwable t) {
                            Intent intent=new Intent(getApplicationContext(), ErrorCatch.class);
                            startActivity(intent);
                        }
                    });

                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public void setAtribut (){
        Login = findViewById(R.id.loginBtn);
        username = findViewById(R.id.usernameTxt);
        password = findViewById(R.id.passwordTxt);
    }

    public void openDialog() {
        EmailDialog emailDialog = new EmailDialog();
        emailDialog.show(getSupportFragmentManager(),"Email Dialog");
    }

}
