package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class supplierDAO {
    @SerializedName("id_supplier")
    private  int id_supplier;

    @SerializedName("nama_supplier")
    private  String nama_supplier;

    @SerializedName("alamat_supplier")
    private  String alamat_supplier;

    @SerializedName("telp_supplier")
    private  String telp_supplier;

    public int getId_supplier() {
        return id_supplier;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public String getAlamat_supplier() {
        return alamat_supplier;
    }

    public String getTelp_supplier() {
        return telp_supplier;
    }
}