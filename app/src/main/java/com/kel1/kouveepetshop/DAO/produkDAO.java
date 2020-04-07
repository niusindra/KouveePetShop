package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class produkDAO {
    @SerializedName("id_produk")
    private  int id_produk;

    @SerializedName("id_supplier")
    private  int id_supplier;

    @SerializedName("nama_produk")
    private  String nama_produk;

    @SerializedName("foto_produk")
    private  String foto_produk;

    @SerializedName("harga_beli_produk")
    private  String harga_beli_produk;

    @SerializedName("harga_jual_produk")
    private  String harga_jual_produk;

    @SerializedName("stok")
    private  String stok;

    @SerializedName("min_stok")
    private  String min_stok;

    public int getId_produk() {
        return id_produk;
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public String getFoto_produk() {
        return foto_produk;
    }

    public String getHarga_beli_produk() {
        return harga_beli_produk;
    }

    public String getHarga_jual_produk() {
        return harga_jual_produk;
    }

    public String getStok() {
        return stok;
    }

    public String getMin_stok() {
        return min_stok;
    }
}