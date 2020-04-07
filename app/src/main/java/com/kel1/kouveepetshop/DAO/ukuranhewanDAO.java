package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class ukuranhewanDAO {
    @SerializedName("id_ukuran")
    private  int id_ukuran;

    @SerializedName("ukuran")
    private  String ukuran;

    public int getId_ukuran() {
        return id_ukuran;
    }

    public String getUkuran() {
        return ukuran;
    }
}