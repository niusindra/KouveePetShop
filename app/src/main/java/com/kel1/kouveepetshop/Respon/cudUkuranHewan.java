package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.ukuranhewanDAO;

public class cudUkuranHewan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private ukuranhewanDAO message;

    public ukuranhewanDAO getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(ukuranhewanDAO message) {
        this.message = message;
    }
}
