package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.hewanDAO;

import java.util.List;

public class readHewan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<hewanDAO> message;

    public  String getError() {return error;}

    public List<hewanDAO> getMessage() {return message;}

}
