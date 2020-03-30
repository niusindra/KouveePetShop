package com.kel1.kouveepetshop.DAO;

public class adminDAO {
    String id, email,telp,username,password,status;

    public adminDAO(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public adminDAO(){}
    public adminDAO(String id, String username, String password, String email, String status) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public adminDAO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

