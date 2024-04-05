package com.example.khachhang.DTO;

public class HoaDon  {
    public String tenKH;
    public String tenSP;
    public int giaSP;
    public int soLuongSP;
    public int tongTienSP;

    public String getTenKH() {
        return tenKH;
    }
    //123
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public HoaDon(String tenKH, String tenSP, int giaSP, int soLuongSP, int tongTienSP) {
        this.tenKH = tenKH;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
        this.tongTienSP = tongTienSP;
    }


    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public int getTongTienSP() {
        return tongTienSP;
    }

    public void setTongTienSP(int tongTienSP) {
        this.tongTienSP = tongTienSP;
    }

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

}
