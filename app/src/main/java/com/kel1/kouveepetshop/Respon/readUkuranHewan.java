package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.ukuranhewanDAO;

import java.util.List;

public class readUkuranHewan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<ukuranhewanDAO> message;

    public  String getError() {return error;}

    public List<ukuranhewanDAO> getMessage() {return message;}

}
