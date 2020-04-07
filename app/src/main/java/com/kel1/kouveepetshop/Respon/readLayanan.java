package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.layananDAO;

import java.util.List;

public class readLayanan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<layananDAO> message;

    public  String getError() {return error;}

    public List<layananDAO> getMessage() {return message;}

    public void setMessage(List<layananDAO> message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }

}
