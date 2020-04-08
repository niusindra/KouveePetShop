package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class verifyPegawai {

    @SerializedName("id_pegawai")
    @Expose
    private String id_pegawai;

    @SerializedName("nama_pegawai")
    @Expose
    private String nama_pegawai;

    @SerializedName("role_pegawai")
    @Expose
    private String role_pegawai;

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public String getRole_pegawai() {
        return role_pegawai;
    }
}
