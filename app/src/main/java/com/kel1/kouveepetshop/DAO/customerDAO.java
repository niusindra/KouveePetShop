package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class customerDAO {
    @SerializedName("id_customer")
    private  int id_customer;

    @SerializedName("nama_customer")
    private  String nama_customer;

    @SerializedName("alamat_customer")
    private  String alamat_customer;

    @SerializedName("tgllahir_customer")
    private  String tgllahir_customer;

    @SerializedName("telp_customer")
    private  String telp_customer;

    @SerializedName("cust_created_at")
    private  String cust_created_at;

    @SerializedName("cust_edited_at")
    private  String cust_edited_at;

    @SerializedName("cust_deleted_at")
    private  String cust_deleted_at;

    @SerializedName("cust_created_by")
    private  String cust_created_by;

    @SerializedName("cust_edited_by")
    private  String cust_edited_by;

    @SerializedName("cust_deleted_by")
    private  String cust_deleted_by;


    public int getId_customer() { return id_customer; }

    public String getNama_customer() {
        return nama_customer;
    }

    public String getAlamat_customer() {
        return alamat_customer;
    }

    public String getTelp_customer() {
        return telp_customer;
    }

    public String getTgllahir_customer() {
        return tgllahir_customer;
    }

    public String getCust_created_at() {
        return cust_created_at;
    }

    public String getCust_created_by() {
        return cust_created_by;
    }

    public String getCust_deleted_at() {
        return cust_deleted_at;
    }

    public String getCust_deleted_by() {
        return cust_deleted_by;
    }

    public String getCust_edited_at() {
        return cust_edited_at;
    }

    public String getCust_edited_by() {
        return cust_edited_by;
    }
}
