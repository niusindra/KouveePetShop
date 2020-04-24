package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.DAO.pengadaanDAO;

import java.util.List;

public class readPengadaan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<pengadaanDAO> message;

    public  String getError() {return error;}

    public List<pengadaanDAO> getMessage() {return message;}

    public void setMessage(List<pengadaanDAO> message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }
}
