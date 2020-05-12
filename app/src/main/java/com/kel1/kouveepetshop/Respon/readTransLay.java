package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.pengadaanDAO;
import com.kel1.kouveepetshop.DAO.transaksiLayananDAO;

import java.util.List;

public class readTransLay {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<transaksiLayananDAO> message;

    public  String getError() {return error;}

    public List<transaksiLayananDAO> getMessage() {return message;}

    public void setMessage(List<transaksiLayananDAO> message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }
}
