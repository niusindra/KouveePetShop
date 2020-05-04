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

    @SerializedName("nama_hewan")
    @Expose
    private String nama_hewan;

    @SerializedName("ukuran")
    @Expose
    private String ukuran;

    @SerializedName("jenis")
    @Expose
    private String jenis;

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

    public String getNama_Hewan() {
        return nama_hewan;
    }

    public String getUkuran_hewan() {
        return ukuran;
    }

    public String getJenis_hewan() {
        return jenis;
    }
    public String getNama_layanan() {
        return nama_layanan;
    }

    public String getToken() {
        return token;
    }
}
