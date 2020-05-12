package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.detailLayananDAO;
import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;

import java.util.List;

public class readDetailLayanan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<detailLayananDAO> message;

    public  String getError() {return error;}

    public List<detailLayananDAO> getMessage() {return message;}

    public void setMessage(List<detailLayananDAO> message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }
}
