package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.transaksiLayananDAO;
import com.kel1.kouveepetshop.DAO.transaksiProdukDAO;

import java.util.List;

public class readTransPro {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<transaksiProdukDAO> message;

    public  String getError() {return error;}

    public List<transaksiProdukDAO> getMessage() {return message;}

    public void setMessage(List<transaksiProdukDAO> message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }
}
