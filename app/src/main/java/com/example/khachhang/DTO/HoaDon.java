package com.example.khachhang.DTO;

public class HoaDon {
    public String tenSP;
    public int giaSP;
    public String hangSP;
    public String moTaSP;

    public HoaDon() {
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

    public HoaDon(String tenSP, int giaSP, String hangSP, String moTaSP) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hangSP = hangSP;
        this.moTaSP = moTaSP;
    }
}
