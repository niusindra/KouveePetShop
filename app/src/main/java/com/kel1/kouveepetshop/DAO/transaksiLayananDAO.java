package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class transaksiLayananDAO {
    @SerializedName("id_trans_layanan")
    private  String id_trans_layanan;

    @SerializedName("id_cs")
    private  int id_cs;

    @SerializedName("nama_cs")
    private  String nama_cs;

    @SerializedName("id_kasir")
    private  int id_kasir;

    @SerializedName("nama_kasir")
    private  String nama_kasir;

    @SerializedName("id_hewan")
    private  int id_hewan;

    @SerializedName("nama_hewan")
    private  String nama_hewan;

    @SerializedName("jenis")
    private  String jenis;

    @SerializedName("id_customer")
    private  int id_customer;

    @SerializedName("nama_customer")
    private  String nama_customer;

    @SerializedName("alamat_customer")
    private  String alamat_customer;

    @SerializedName("telp_customer")
    private  String telp_customer;

    @SerializedName("tanggal_trans_layanan")
    private  String tanggal_trans_layanan;

    @SerializedName("diskon_layanan")
    private  int diskon_layanan;

    @SerializedName("total_layanan")
    private  int total_layanan;

    @SerializedName("status_layanan")
    private  String status_layanan;

    @SerializedName("translay_created_at")
    private  String translay_created_at;

    @SerializedName("translay_edited_at")
    private  String translay_edited_at;

    @SerializedName("translay_deleted_at")
    private  String translay_deleted_at;

    @SerializedName("translay_created_by")
    private  String translay_created_by;

    @SerializedName("translay_edited_by")
    private  String translay_edited_by;

    @SerializedName("translay_deleted_by")
    private  String translay_deleted_by;

    public String getId_trans_layanan() {
        return id_trans_layanan;
    }

    public int getId_cs() {
        return id_cs;
    }

    public String getNama_cs() {
        return nama_cs;
    }

    public int getId_kasir() {
        return id_kasir;
    }

    public String getNama_kasir() {
        return nama_kasir;
    }

    public int getId_hewan() {
        return id_hewan;
    }

    public String getNama_hewan() {
        return nama_hewan;
    }

    public int getId_customer() {
        return id_customer;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public String getTanggal_trans_layanan() {
        return tanggal_trans_layanan;
    }

    public int getDiskon_layanan() {
        return diskon_layanan;
    }

    public int getTotal_layanan() {
        return total_layanan;
    }

    public String getStatus_layanan() {
        return status_layanan;
    }

    public String getTranslay_created_at() {
        return translay_created_at;
    }

    public String getTranslay_edited_at() {
        return translay_edited_at;
    }

    public String getTranslay_deleted_at() {
        return translay_deleted_at;
    }

    public String getTranslay_created_by() {
        return translay_created_by;
    }

    public String getTranslay_edited_by() {
        return translay_edited_by;
    }

    public String getTranslay_deleted_by() {
        return translay_deleted_by;
    }

    public String getJenis() {
        return jenis;
    }

    public String getAlamat_customer() {
        return alamat_customer;
    }

    public String getTelp_customer() {
        return telp_customer;
    }
}