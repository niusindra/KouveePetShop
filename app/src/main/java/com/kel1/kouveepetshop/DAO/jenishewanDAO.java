package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class jenishewanDAO {
    @SerializedName("id_jenis")
    private  int id_jenis;

    @SerializedName("jenis")
    private  String jenis;

    @SerializedName("jns_created_at")
    private  String jns_created_at;

    @SerializedName("jns_edited_at")
    private  String jns_edited_at;

    @SerializedName("jns_deleted_at")
    private  String jns_deleted_at;

    public int getId_jenis() {
        return id_jenis;
    }

    public String getJenis() {
        return jenis;
    }

    public String getJns_created_at() {
        return jns_created_at;
    }

    public String getJns_deleted_at() {
        return jns_deleted_at;
    }

    public String getJns_edited_at() {
        return jns_edited_at;
    }
}