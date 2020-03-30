package com.kel1.kouveepetshop.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.AESUtils;
import com.kel1.kouveepetshop.DAO.adminDAO;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.EmailDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register_admin extends AppCompatActivity {
    public Button Daftar;
    public TextView Login;
    public EditText mNama,mNpm,mUsername,mPassword,mConfirm,mEmail,mTelp;
    public Spinner mProdi,mFakultas;
    public adminDAO adminDAO;
    private String encrypted = "";
    private String sourceStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_admin);

        setAtribut();
        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPassword.getText().toString().equals(mConfirm.getText().toString())){
                    adminDAO.setEmail(mEmail.getText().toString());
                    adminDAO.setUsername(mUsername.getText().toString());
                    sourceStr = mPassword.getText().toString();
                    try {
                        encrypted = AESUtils.encrypt(sourceStr);
                        Log.d("TEST", "encrypted:" + encrypted);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    adminDAO.setPassword(encrypted);
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<adminDAO> apiServiceUser = apiService.createUser(adminDAO);
                    apiServiceUser.enqueue(new Callback<adminDAO>() {
                        @Override
                        public void onResponse(Call<adminDAO> call, Response<adminDAO> response) {
                            openDialog();
                            Intent intent = new Intent(register_admin.this, login_admin.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<adminDAO> call, Throwable t) {
                            mConfirm.setText("");
                            mPassword.setText("");
                            Toast.makeText(getApplicationContext(), "koneksi gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    mConfirm.setText("");
                    mPassword.setText("");
                    Toast.makeText(getApplicationContext(),"Password dan Confirm Password tidak cocok",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register_admin.this, login_admin.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return false;
    }

    public void setAtribut (){
        Login = findViewById(R.id.loginBtn);
        Daftar = findViewById(R.id.registerBtn);
        mUsername = findViewById(R.id.usernameTxt);
        mEmail=findViewById(R.id.emailTxt);
        mPassword = findViewById(R.id.passwordTxt);
        adminDAO = new adminDAO();
    }
    public void openDialog() {
        EmailDialog emailDialog = new EmailDialog();
        emailDialog.show(getSupportFragmentManager(),"Email Dialog");
    }
}
