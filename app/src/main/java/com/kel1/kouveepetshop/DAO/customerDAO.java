package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class customerDAO{
    @SerializedName("ID_CUSTOMER")
    private  int id_customer;

    @SerializedName("NAMA_CUSTOMER")
    private  String nama_customer;

    @SerializedName("ALAMAT_CUSTOMER")
    private  String alamat_customer;

    @SerializedName("TGLLAHIR_CUSTOMER")
    private  String tgllahir_customer;

    @SerializedName("TELP_CUSTOMER")
    private  String telp_customer;

//    public customerDAO(){}
//
//    public customerDAO(int id_customer, String nama_customer, String alamat_customer, String tgllahir_customer, String telp_customer)
//    {
//        this.id_customer=id_customer;
//        this.nama_customer=nama_customer;
//        this.alamat_customer=alamat_customer;
//        this.tgllahir_customer=tgllahir_customer;
//        this.telp_customer=telp_customer;
//    }

    public int getId_customer() { return id_customer; }

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