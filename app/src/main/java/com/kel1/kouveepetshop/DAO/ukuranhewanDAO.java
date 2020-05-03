package com.kel1.kouveepetshop.DAO;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ukuranhewanDAO {
    @SerializedName("id_ukuran")
    private  int id_ukuran;

    @SerializedName("ukuran")
    private  String ukuran;

    @SerializedName("ukrn_created_at")
    private String ukrn_created_at;

    @SerializedName("ukrn_edited_at")
    private  String ukrn_edited_at;

    @SerializedName("ukrn_deleted_at")
    private  String ukrn_deleted_at;

    public int getId_ukuran() {
        return id_ukuran;
    }

    public String getUkuran() {
        return ukuran;
    }

    public String getUkrn_created_at() {
        return ukrn_created_at;
    }

    public String getUkrn_deleted_at() {
        return ukrn_deleted_at;
    }

    public String getUkrn_edited_at() {
        return ukrn_edited_at;
    }

    @NonNull
    @Override
    public String toString() {
        return ukuran;
    }
}