package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class detailLayananDAO {
    @SerializedName("id_detail_layanan")
    private  int id_detail_layanan;

    @SerializedName("id_trans_layanan")
    private  String id_trans_layanan;

    @SerializedName("id_harga_layanan")
    private  int id_harga_layanan;

    @SerializedName("nama_layanan")
    private  String nama_layanan;

    @SerializedName("harga_layanan")
    private  int harga_layanan;

    @SerializedName("jumlah_beli_layanan")
    private  int jumlah_beli_layanan;

    @SerializedName("subtotal_layanan")
    private  int subtotal_layanan;

    public int getId_detail_layanan() {
        return id_detail_layanan;
    }

    public void setId_detail_layanan(int id_detail_layanan) {
        this.id_detail_layanan = id_detail_layanan;
    }

    public String getId_trans_layanan() {
        return id_trans_layanan;
    }

    public void setId_trans_layanan(String id_trans_layanan) {
        this.id_trans_layanan = id_trans_layanan;
    }

    public int getId_harga_layanan() {
        return id_harga_layanan;
    }

    public void setId_harga_layanan(int id_harga_layanan) {
        this.id_harga_layanan = id_harga_layanan;
    }

    public String getNama_layanan() {
        return nama_layanan;
    }

    public void setNama_layanan(String nama_layanan) {
        this.nama_layanan = nama_layanan;
    }

    public int getHarga_layanan() {
        return harga_layanan;
    }

    public void setHarga_layanan(int harga_layanan) {
        this.harga_layanan = harga_layanan;
    }

    public int getJumlah_beli_layanan() {
        return jumlah_beli_layanan;
    }

    public void setJumlah_beli_layanan(int jumlah_beli_layanan) {
        this.jumlah_beli_layanan = jumlah_beli_layanan;
    }

    public int getSubtotal_layanan() {
        return subtotal_layanan;
    }

    public void setSubtotal_layanan(int subtotal_layanan) {
        this.subtotal_layanan = subtotal_layanan;
    }
}