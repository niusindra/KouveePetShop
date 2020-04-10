package com.kel1.kouveepetshop.DAO;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class supplierDAO {
    @SerializedName("id_supplier")
    private  int id_supplier;

    @SerializedName("nama_supplier")
    private  String nama_supplier;

    @SerializedName("alamat_supplier")
    private  String alamat_supplier;

    @SerializedName("telp_supplier")
    private  String telp_supplier;

    @SerializedName("sup_created_at")
    private  String sup_created_at;

    @SerializedName("sup_edited_at")
    private  String sup_edited_at;

    @SerializedName("sup_deleted_at")
    private  String sup_deleted_at;

    public int getId_supplier() {
        return id_supplier;
    }
    public supplierDAO(int id_supplier, String name,String alamat_supplier,String telp_supplier, String sup_created_at,String sup_edited_at, String sup_deleted_at) {
        this.id_supplier = id_supplier;
        this.nama_supplier = name;
        this.alamat_supplier = alamat_supplier;
        this.telp_supplier = telp_supplier;
        this.sup_created_at = sup_created_at;
        this.sup_edited_at = sup_edited_at;
        this.sup_deleted_at = sup_deleted_at;
    }
    public String getNama_supplier() {
        return nama_supplier;
    }

    public String getAlamat_supplier() {
        return alamat_supplier;
    }

    public String getTelp_supplier() {
        return telp_supplier;
    }

    public String getSup_created_at() {
        return sup_created_at;
    }

    public String getSup_deleted_at() {
        return sup_deleted_at;
    }

    public String getSup_edited_at() {
        return sup_edited_at;
    }

    @NonNull
    @Override
    public String toString() {
        return nama_supplier;
    }
}