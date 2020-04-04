package com.kel1.kouveepetshop.DAO;

import com.google.gson.annotations.SerializedName;

public class pegawaiDAO {
    @SerializedName("id_pegawai")
    private  int id_pegawai;

    @SerializedName("nama_pegawai")
    private  String nama_pegawai;

    @SerializedName("alamat_pegawai")
    private  String alamat_pegawai;

    @SerializedName("tgllahir_pegawai")
    private  String tgllahir_pegawai;

    @SerializedName("telp_pegawai")
    private  String telp_pegawai;

    @SerializedName("role_pegawai")
    private  String role_pegawai;

    @SerializedName("username")
    private  String username;

    @SerializedName("password")
    private  String password;

    public int getId_pegawai() {
        return id_pegawai;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public String getAlamat_pegawai() {
        return alamat_pegawai;
    }

    public String getTgllahir_pegawai() {
        return tgllahir_pegawai;
    }

    public String getTelp_pegawai() {
        return telp_pegawai;
    }

    public String getRole_pegawai() {
        return role_pegawai;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}