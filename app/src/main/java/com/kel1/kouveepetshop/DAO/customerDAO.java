package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class customerDAO{
    @SerializedName("ID_CUSTOMER")
    private  String id_customer;

    @SerializedName("NAMA_CUSTOMER")
    private  String nama_customer;

    @SerializedName("ALAMAT_CUSTOMER")
    private  String alamat_customer;

    @SerializedName("TGLLAHIR_CUSTOMER")
    private  String tgllahir_customer;

    @SerializedName("TELP_CUSTOMER")
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