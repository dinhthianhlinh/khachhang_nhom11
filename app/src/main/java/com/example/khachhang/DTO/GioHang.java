package com.example.khachhang.DTO;

import java.io.Serializable;

public class GioHang implements Serializable {
//    String anhSP;
    public String tenSP;
    public int giaSP;
    public int soLuongSP;

    public GioHang() {
    }
    //123
    public GioHang(String tenSP, int giaSP, int soLuongSP) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
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

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }
    public int tinhTongTien() {
        return giaSP * soLuongSP;
    }
}
