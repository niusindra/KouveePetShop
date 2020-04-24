package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class pengadaanDAO {
    @SerializedName("id_pengadaan")
    private  int id_pengadaan;

    @SerializedName("tgl_pengadaan")
    private  String tgl_pengadaan;

    @SerializedName("total_pengadaan")
    private  int total_pengadaan;

    @SerializedName("status_pengadaan")
    private  String status_pengadaan;

    @SerializedName("id_supplier")
    private  int id_supplier;

    @SerializedName("nama_supplier")
    private  String nama_supplier;

    @SerializedName("adaan_created_at")
    private  String adaan_created_at;

    @SerializedName("adaan_edited_at")
    private  String adaan_edited_at;

    @SerializedName("adaan_deleted_at")
    private  String adaan_deleted_at;

    public String getNama_supplier() {
        return nama_supplier;
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public int getId_pengadaan() {
        return id_pengadaan;
    }

    public int getTotal_pengadaan() {
        return total_pengadaan;
    }

    public String getAdaan_created_at() {
        return adaan_created_at;
    }

    public String getAdaan_deleted_at() {
        return adaan_deleted_at;
    }

    public String getAdaan_edited_at() {
        return adaan_edited_at;
    }

    public String getStatus_pengadaan() {
        return status_pengadaan;
    }

    public String getTgl_pengadaan() {
        return tgl_pengadaan;
    }
}