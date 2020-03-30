package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.hewanDAO;

public class cudHewan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private hewanDAO message;

    public hewanDAO getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(hewanDAO message) {
        this.message = message;
    }
}
