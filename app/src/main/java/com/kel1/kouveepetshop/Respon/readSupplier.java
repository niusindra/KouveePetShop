package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.supplierDAO;

import java.util.List;

public class readSupplier {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<supplierDAO> message;

    public  String getError() {return error;}

    public List<supplierDAO> getMessage() {return message;}

}
