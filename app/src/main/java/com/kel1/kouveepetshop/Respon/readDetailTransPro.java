package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.detailLayananDAO;
import com.kel1.kouveepetshop.DAO.detailProdukDAO;

import java.util.List;

public class readDetailTransPro {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<detailProdukDAO> message;

    public  String getError() {return error;}

    public List<detailProdukDAO> getMessage() {return message;}

    public void setMessage(List<detailProdukDAO> message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }
}
