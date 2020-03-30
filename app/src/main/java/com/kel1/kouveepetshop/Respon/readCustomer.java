package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.kel1.kouveepetshop.DAO.customerDAO;

public class readCustomer {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private List<customerDAO> message;

    public  String getError() {return error;}

    public List<customerDAO> getMessage() {return message;}

    public void setMessage(List<customerDAO> message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }
}
