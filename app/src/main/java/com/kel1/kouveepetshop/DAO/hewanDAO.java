package com.kel1.kouveepetshop.DAO;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class hewanDAO {
    @SerializedName("id_hewan")
    private  int id_hewan;

    @SerializedName("id_customer")
    private  int id_customer;

    @SerializedName("nama_customer")
    private  String nama_customer;

    @SerializedName("telp_customer")
    private  String telp_customer;

    @SerializedName("alamat_customer")
    private  String alamat_customer;

    @SerializedName("nama_hewan")
    private  String nama_hewan;

    @SerializedName("tgl_lahir_hewan")
    private  String tgl_lahir_hewan;

    @SerializedName("hwn_created_at")
    private  String hwn_created_at;

    @SerializedName("hwn_edited_at")
    private  String hwn_edited_at;

    @SerializedName("hwn_deleted_at")
    private  String hwn_deleted_at;

    @SerializedName("hwn_created_by")
    private  String hwn_created_by;

    @SerializedName("hwn_edited_by")
    private  String hwn_edited_by;

    @SerializedName("hwn_deleted_by")
    private  String hwn_deleted_by;

    public int getId_customer() {
        return id_customer;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public String getTelp_customer() {
        return telp_customer;
    }

    public String getAlamat_customer() {
        return alamat_customer;
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

    public String getHwn_created_at() {
        return hwn_created_at;
    }

    public String getHwn_created_by() {
        return hwn_created_by;
    }

    public String getHwn_deleted_at() {
        return hwn_deleted_at;
    }

    public String getHwn_deleted_by() {
        return hwn_deleted_by;
    }

    public String getHwn_edited_at() {
        return hwn_edited_at;
    }

    public String getHwn_edited_by() {
        return hwn_edited_by;
    }

    @NonNull
    @Override
    public String toString() {
        return nama_hewan;
    }

}