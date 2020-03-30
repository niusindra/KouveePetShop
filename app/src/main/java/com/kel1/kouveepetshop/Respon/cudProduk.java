package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.produkDAO;

public class cudProduk {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private produkDAO message;

    public produkDAO getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(produkDAO message) {
        this.message = message;
    }
}
