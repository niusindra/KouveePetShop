package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class hewanDAO {
    @SerializedName("id_hewan")
    private  int id_hewan;

    @SerializedName("id_customer")
    private  int id_customer;

    @SerializedName("nama_hewan")
    private  String nama_hewan;

    @SerializedName("tgl_lahir_hewan")
    private  String tgl_lahir_hewan;

    public int getId_customer() {
        return id_customer;
    }

    public int getId_hewan() {
        return id_hewan;
    }

    public String getNama_hewan() {
        return nama_hewan;
    }

    public String getTgl_lahir_hewan() {
        return tgl_lahir_hewan;
    }
}