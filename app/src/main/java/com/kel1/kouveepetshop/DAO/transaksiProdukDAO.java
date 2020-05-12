package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class transaksiProdukDAO {
    @SerializedName("id_trans_produk")
    private  String id_trans_produk;

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

    @SerializedName("id_customer")
    private  int id_customer;

    @SerializedName("nama_customer")
    private  String nama_customer;

    @SerializedName("tanggal_trans_produk")
    private  String tanggal_trans_produk;

    @SerializedName("diskon_produk")
    private  int diskon_produk;

    @SerializedName("total_produk")
    private  int total_produk;

    @SerializedName("status_penjualan_produk")
    private  String status_penjualan_produk;

    @SerializedName("transproduk_created_at")
    private  String transproduk_created_at;

    @SerializedName("transproduk_edited_at")
    private  String transproduk_edited_at;

    @SerializedName("transproduk_deleted_at")
    private  String transproduk_deleted_at;

    @SerializedName("transproduk_created_by")
    private  String transproduk_created_by;

    @SerializedName("transproduk_edited_by")
    private  String transproduk_edited_by;

    @SerializedName("transproduk_deleted_by")
    private  String transproduk_deleted_by;

    public String getId_trans_produk() {
        return id_trans_produk;
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

    public String getTanggal_trans_produk() {
        return tanggal_trans_produk;
    }

    public int getDiskon_produk() {
        return diskon_produk;
    }

    public int getTotal_produk() {
        return total_produk;
    }

    public String getStatus_penjualan_produk() {
        return status_penjualan_produk;
    }

    public String getTransproduk_created_at() {
        return transproduk_created_at;
    }

    public String getTransproduk_edited_at() {
        return transproduk_edited_at;
    }

    public String getTransproduk_deleted_at() {
        return transproduk_deleted_at;
    }

    public String getTransproduk_created_by() {
        return transproduk_created_by;
    }

    public String getTransproduk_edited_by() {
        return transproduk_edited_by;
    }

    public String getTransproduk_deleted_by() {
        return transproduk_deleted_by;
    }
}