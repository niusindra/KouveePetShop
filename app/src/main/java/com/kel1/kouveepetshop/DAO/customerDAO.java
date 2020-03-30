package com.kel1.kouveepetshop.DAO;

public class customerDAO {
    String id, nama, kategori, penyelenggara, tempat, waktu, deskripsi, harga, no_rek, nama_rek, bank_rek, username, password, email, status;

    public customerDAO(String nama, String password, String id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public customerDAO(String id, String nama, String kategori, String penyelenggara, String tempat, String waktu, String deskripsi, String harga, String no_rek, String nama_rek, String bank_rek, String username, String password, String email, String status) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.penyelenggara = penyelenggara;
        this.tempat = tempat;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.no_rek = no_rek;
        this.nama_rek = nama_rek;
        this.bank_rek = bank_rek;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
    }

    public customerDAO(String id) {
        this.id = id;
    }

    public customerDAO(String nama, String kategori, String penyelenggara, String tempat, String waktu, String deskripsi, String harga, String no_rek, String nama_rek, String bank_rek, String username, String password, String email) {
        this.nama = nama;
        this.kategori = kategori;
        this.penyelenggara = penyelenggara;
        this.tempat = tempat;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.no_rek = no_rek;
        this.nama_rek = nama_rek;
        this.bank_rek = bank_rek;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public customerDAO(String id, String nama, String kategori, String penyelenggara, String tempat, String waktu, String deskripsi, String harga, String no_rek, String nama_rek, String bank_rek, String username, String password, String email) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.penyelenggara = penyelenggara;
        this.tempat = tempat;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.no_rek = no_rek;
        this.nama_rek = nama_rek;
        this.bank_rek = bank_rek;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(String penyelenggara) {
        this.penyelenggara = penyelenggara;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNo_rek() {
        return no_rek;
    }

    public void setNo_rek(String no_rek) {
        this.no_rek = no_rek;
    }

    public String getNama_rek() {
        return nama_rek;
    }

    public void setNama_rek(String nama_rek) {
        this.nama_rek = nama_rek;
    }
}