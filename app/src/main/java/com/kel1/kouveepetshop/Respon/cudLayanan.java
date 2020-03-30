package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.layananDAO;

public class cudLayanan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private layananDAO message;

    public layananDAO getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(layananDAO message) {
        this.message = message;
    }
}
