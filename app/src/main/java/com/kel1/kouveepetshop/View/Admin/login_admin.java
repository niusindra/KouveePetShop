package com.kel1.kouveepetshop.View.Admin;

import androidx.appcompat.app.AppCompatActivity;

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

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.adminDAO;
import com.kel1.kouveepetshop.EmailDialog;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.AESUtils;
import com.kel1.kouveepetshop.View.Produk.produkMain;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_admin extends AppCompatActivity {

    public Button Login;
    public TextView signUp;
    public EditText username,password;
    public adminDAO mUser;
    private String decrypted = "";
    private String encrypted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin);
        setAtribut ();


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
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<adminDAO> userDAOCall = apiService.loginUser(username.getText().toString());
                    userDAOCall.enqueue(new Callback<adminDAO>() {
                        @Override
                        public void onResponse(Call<adminDAO> call, Response<adminDAO> response) {
                            if (response.body() != null) {
                                mUser = response.body();
                                encrypted = mUser.getPassword();
                                try {
                                    decrypted = AESUtils.decrypt(encrypted);
                                    Log.d("TEST", "decrypted:" + decrypted);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (mUser.getStatus().equals("Waiting Confirmation") && decrypted.equals(password.getText().toString())) {
                                    openDialog();
                                    username.setText("");
                                    password.setText("");
                                } else if (decrypted.equals(password.getText().toString())) {
                                    Intent intent = new Intent(login_admin.this, produkMain.class);
                                    intent.putExtra("id", mUser.getId());
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Username / Password Salah", Toast.LENGTH_SHORT).show();
                                    password.setText("");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<adminDAO> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_admin.this, register_admin.class);
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
        signUp = findViewById(R.id.signUpBtn);
        username = findViewById(R.id.usernameTxt);
        password = findViewById(R.id.passwordTxt);
    }

    public void openDialog() {
        EmailDialog emailDialog = new EmailDialog();
        emailDialog.show(getSupportFragmentManager(),"Email Dialog");
    }

}
