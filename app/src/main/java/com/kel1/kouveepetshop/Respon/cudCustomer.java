package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.customerDAO;

import java.util.List;

public class cudCustomer {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private customerDAO message;

    public customerDAO getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(customerDAO message) {
        this.message = message;
    }
}
