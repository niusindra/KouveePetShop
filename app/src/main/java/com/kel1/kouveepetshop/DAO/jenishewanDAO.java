package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class jenishewanDAO {
    @SerializedName("id_customer")
    private  String id_customer;

    @SerializedName("nama_customer")
    private  String nama_customer;

    @SerializedName("alamat_customer")
    private  String alamat_customer;

    @SerializedName("tgllahir_customer")
    private  String tgllahir_customer;

    @SerializedName("telp_customer")
    private  String telp_customer;

    public String getId_customer() {
        return id_customer;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public String getAlamat_customer() {
        return alamat_customer;
    }

    public String getTelp_customer() {
        return telp_customer;
    }

    public String getTgllahir_customer() {
        return tgllahir_customer;
    }
}