package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.produkDAO;

import java.util.List;

public class readProduk {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<produkDAO> message;

    public  String getError() {return error;}

    public List<produkDAO> getMessage() {return message;}

}
