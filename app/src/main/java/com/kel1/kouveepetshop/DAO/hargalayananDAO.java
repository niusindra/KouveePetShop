package com.kel1.kouveepetshop.DAO;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class hargalayananDAO {
    @SerializedName("id_harga_layanan")
    private  int id_harga_layanan;

    @SerializedName("id_layanan")
    private  int id_layanan;

    @SerializedName("nama_layanan")
    private  String nama_layanan;

    @SerializedName("id_jenis")
    private  int id_jenis;

    @SerializedName("jenis")
    private  String jenis;

    @SerializedName("id_ukuran")
    private  int id_ukuran;

    @SerializedName("ukuran")
    private  String ukuran;

    @SerializedName("harga_layanan")
    private  int harga_layanan;

    @SerializedName("harga_created_at")
    private  String harga_created_at;

    @SerializedName("harga_edited_at")
    private  String harga_edited_at;

    @SerializedName("harga_deleted_at")
    private  String harga_deleted_at;

    public int getId_harga_layanan() {
        return id_harga_layanan;
    }
    public int getId_layanan() {
        return id_layanan;
    }
    public int getId_jenis() {
        return id_jenis;
    }
    public int getId_ukuran() {
        return id_ukuran;
    }

    public String getNama_layananHL() {
        return nama_layanan;
    }
    public String getJenisHL() {
        return jenis;
    }
    public String getUkuranHL() {
        return ukuran;
    }
    public int getHarga_Layanan() {
        return harga_layanan;
    }

    public String getHarga_created_at() {
        return harga_created_at;
    }

    public String getHarga_deleted_at() {
        return harga_deleted_at;
    }

    public String getHarga_edited_at() {
        return harga_edited_at;
    }

    @NonNull
    @Override
    public String toString() {
        return nama_layanan;
    }
}
