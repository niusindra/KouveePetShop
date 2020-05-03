package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.DAO.hargalayananDAO;

import java.util.List;

public class readHargaLayanan {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<hargalayananDAO> message;

    public  String getError() {return error;}

    public List<hargalayananDAO> getMessage() {
        return message;
    }
}
