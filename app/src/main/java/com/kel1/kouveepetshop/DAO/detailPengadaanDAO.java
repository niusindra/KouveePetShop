package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class detailPengadaanDAO {
    @SerializedName("id_detail_pengadaan")
    private  int id_detail_pengadaan;

    @SerializedName("id_pengadaan")
    private  int id_pengadaan;

    @SerializedName("id_produk")
    private  int id_produk;

    @SerializedName("nama_produk")
    private  String nama_produk;

    @SerializedName("satuan")
    private  String satuan;

    @SerializedName("jml_pengadaan_produk")
    private  int jml_pengadaan_produk;

    @SerializedName("subtotal_pengadaan")
    private  int subtotal_pengadaan;

    public int getId_produk() {
        return id_produk;
    }

    public int getId_detail_pengadaan() {
        return id_detail_pengadaan;
    }

    public int getId_pengadaan() {
        return id_pengadaan;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public String getSatuan() {
        return satuan;
    }

    public int getJml_pengadaan_produk() {
        return jml_pengadaan_produk;
    }

    public int getSubtotal_pengadaan() {
        return subtotal_pengadaan;
    }

    public void setId_detail_pengadaan(int id_detail_pengadaan) {
        this.id_detail_pengadaan = id_detail_pengadaan;
    }

    public void setId_pengadaan(int id_pengadaan) {
        this.id_pengadaan = id_pengadaan;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public void setJml_pengadaan_produk(int jml_pengadaan_produk) {
        this.jml_pengadaan_produk = jml_pengadaan_produk;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public void setSubtotal_pengadaan(int subtotal_pengadaan) {
        this.subtotal_pengadaan = subtotal_pengadaan;
    }
}