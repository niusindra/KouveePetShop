package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class produkDAO {

    @SerializedName("id_produk")
    private  int id_produk;

    @SerializedName("id_supplier")
    private  int id_supplier;

    @SerializedName("nama_supplier")
    private  String nama_supplier;

    @SerializedName("nama_produk")
    private  String nama_produk;

    @SerializedName("foto_produk")
    private  String foto_produk;

    @SerializedName("harga_beli_produk")
    private  int harga_beli_produk;

    @SerializedName("harga_jual_produk")
    private  int harga_jual_produk;

    @SerializedName("stok")
    private  int stok;

    @SerializedName("min_stok")
    private  int min_stok;

    @SerializedName("produk_created_at")
    private  String produk_created_at;

    @SerializedName("produk_edited_at")
    private  String produk_edited_at;

    @SerializedName("produk_deleted_at")
    private  String produk_deleted_at;

    public int getId_produk() {
        return id_produk;
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public String getFoto_produk() {
        return foto_produk;
    }

    public int getHarga_beli_produk() {
        return harga_beli_produk;
    }

    public int getHarga_jual_produk() {
        return harga_jual_produk;
    }

    public int getStok() {
        return stok;
    }

    public int getMin_stok() {
        return min_stok;
    }

    public String getProduk_created_at() {
        return produk_created_at;
    }

    public String getProduk_deleted_at() {
        return produk_deleted_at;
    }

    public String getProduk_edited_at() {
        return produk_edited_at;
    }
}