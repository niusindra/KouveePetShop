package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class PengadaanDAO {
    @SerializedName("id_pengadaan")
    private  int id_pengadaan;

    @SerializedName("tgl_pengadaan")
    private  String tgl_pengadaan;

    @SerializedName("total_pengadaan")
    private  String total_pengadaan;

    @SerializedName("status_pengadaan")
    private  String status_pengadaan;

    @SerializedName("adaan_created_at")
    private  String adaan_created_at;

    @SerializedName("adaan_edited_at")
    private  String adaan_edited_at;

    @SerializedName("adaan_deleted_at")
    private  String adaan_deleted_at;
}