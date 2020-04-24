package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;

import java.util.List;

public class readDetailPengadaan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<detailPengadaanDAO> message;

    public  String getError() {return error;}

    public List<detailPengadaanDAO> getMessage() {return message;}

    public void setMessage(List<detailPengadaanDAO> message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }
}
