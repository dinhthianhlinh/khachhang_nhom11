package com.example.khachhang.DTO;

public class User {
    private String Email;
    private String Pass;
    private String Ten;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        this.Pass = pass;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        this.Ten = ten;
    }

    public User() {
    }

    public User(String email, String pass, String ten) {
        Email = email;
        Pass = pass;
        Ten = ten;
    }
}
