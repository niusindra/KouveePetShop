package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class cudDataMaster {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("nama_produk")
    @Expose
    private String nama_produk;

    @SerializedName("nama_layanan")
    @Expose
    private String nama_layanan;

    @SerializedName("token")
    @Expose
    private String token;

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public String getNama_layanan() {
        return nama_layanan;
    }

    public String getToken() {
        return token;
    }
}
