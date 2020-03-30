package com.kel1.kouveepetshop.Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;

public class Respon {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<customerDAO> customerDAOList;

    @SerializedName("message")
    @Expose
    private List<produkDAO> message;

    public  String getError() {return error;}

    public List<produkDAO> getMessage() {return message;}

}
