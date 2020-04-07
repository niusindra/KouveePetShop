package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class jenishewanDAO {
    @SerializedName("id_jenis")
    private  int id_jenis;

    @SerializedName("jenis")
    private  String jenis;

    public int getId_jenis() {
        return id_jenis;
    }

    public String getJenis() {
        return jenis;
    }
}