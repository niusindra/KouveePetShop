package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.supplierDAO;

public class cudSupplier {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private supplierDAO message;

    public supplierDAO getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(supplierDAO message) {
        this.message = message;
    }
}
