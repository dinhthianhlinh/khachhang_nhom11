package com.example.khachhang.DTO;

public class User {
    private String ten;
    private String id;
    private String phone;
    private String adress;

    public User(String ten, String id, String phone, String adress) {
        this.ten = ten;
        this.id = id;
        this.phone = phone;
        this.adress = adress;
    }
    //123
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public User() {
    }

}
