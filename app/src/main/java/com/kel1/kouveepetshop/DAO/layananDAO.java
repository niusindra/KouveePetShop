package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class layananDAO {
    @SerializedName("id_layanan")
    private  String id_layanan;

    @SerializedName("nama_layanan")
    private  String nama_layanan;

    public String getId_layanan() {
        return id_layanan;
    }

    public String getNama_layanan() {
        return nama_layanan;
    }
}