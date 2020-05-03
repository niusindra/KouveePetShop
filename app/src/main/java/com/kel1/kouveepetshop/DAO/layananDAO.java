package com.kel1.kouveepetshop.DAO;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class layananDAO {
    @SerializedName("id_layanan")
    private  int id_layanan;

    @SerializedName("nama_layanan")
    private  String nama_layanan;

    @SerializedName("lay_created_at")
    private  String lay_created_at;

    @SerializedName("lay_edited_at")
    private  String lay_edited_at;

    @SerializedName("lay_deleted_at")
    private  String lay_deleted_at;

    public int getId_layanan() {
        return id_layanan;
    }

    public String getNama_layanan() {
        return nama_layanan;
    }

    public String getLay_created_at() {
        return lay_created_at;
    }

    public String getLay_deleted_at() {
        return lay_deleted_at;
    }

    public String getLay_edited_at() {
        return lay_edited_at;
    }
    @NonNull
    @Override
    public String toString() {
        return nama_layanan;
    }
}
