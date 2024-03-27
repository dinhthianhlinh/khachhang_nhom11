package com.example.khachhang.DTO;

import com.google.firebase.Timestamp;

public class SanPham {
    public String tenSP;
    public int giaSP;
    public String hangSP;
    public String moTaSP;
    public Timestamp timestamp;

    public SanPham() {
    }

    public SanPham(String tenSP, String hangSP, String moTaSP) {
        this.tenSP = tenSP;
//        this.giaSP = giaSP;
        this.hangSP = hangSP;
        this.moTaSP = moTaSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public String getHangSP() {
        return hangSP;
    }

    public void setHangSP(String hangSP) {
        this.hangSP = hangSP;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

