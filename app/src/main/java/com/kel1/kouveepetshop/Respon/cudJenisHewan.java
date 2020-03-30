package com.kel1.kouveepetshop.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kel1.kouveepetshop.DAO.jenishewanDAO;

public class cudJenisHewan {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private jenishewanDAO message;

    public jenishewanDAO getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(jenishewanDAO message) {
        this.message = message;
    }
}
