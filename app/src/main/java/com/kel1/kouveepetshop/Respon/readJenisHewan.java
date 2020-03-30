package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.jenishewanDAO;

import java.util.List;

public class readJenisHewan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<jenishewanDAO> message;

    public  String getError() {return error;}

    public List<jenishewanDAO> getMessage() {return message;}

}
