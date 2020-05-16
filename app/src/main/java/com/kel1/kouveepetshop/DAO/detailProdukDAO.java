package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class detailProdukDAO {
    @SerializedName("id_detail_produk")
    private  int id_detail_produk;

    @SerializedName("id_trans_produk")
    private  String id_trans_produk;

    @SerializedName("id_produk")
    private  int id_produk;

    @SerializedName("nama_produk")
    private  String nama_produk;

    @SerializedName("jumlah_beli_produk")
    private  int jumlah_beli_produk;

    @SerializedName("subtotal_produk")
    private  int subtotal_produk;

    public int getId_detail_produk() {
        return id_detail_produk;
    }

    public void setId_detail_produk(int id_detail_produk) {
        this.id_detail_produk = id_detail_produk;
    }

    public String getId_trans_produk() {
        return id_trans_produk;
    }

    public void setId_trans_produk(String id_trans_produk) {
        this.id_trans_produk = id_trans_produk;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public int getJumlah_beli_produk() {
        return jumlah_beli_produk;
    }

    public void setJumlah_beli_produk(int jumlah_beli_produk) {
        this.jumlah_beli_produk = jumlah_beli_produk;
    }

    public int getSubtotal_produk() {
        return subtotal_produk;
    }

    public void setSubtotal_produk(int subtotal_produk) {
        this.subtotal_produk = subtotal_produk;
    }
}