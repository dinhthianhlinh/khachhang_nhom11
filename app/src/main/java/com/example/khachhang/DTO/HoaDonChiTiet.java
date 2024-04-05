package com.example.khachhang.DTO;

public class HoaDonChiTiet {
    public String tenKH;
    public String tenSP;
    public int giaSP;
    public int soLuongSP;
    public int tongTienSP;
    public String phone;
    public String adress;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String tenKH, String tenSP, int giaSP, int soLuongSP, int tongTienSP, String phone, String adress) {
        this.tenKH = tenKH;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
        this.tongTienSP = tongTienSP;
        this.phone = phone;
        this.adress = adress;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
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

    public int getTongTienSP() {
        return tongTienSP;
    }

    public void setTongTienSP(int tongTienSP) {
        this.tongTienSP = tongTienSP;
    }

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
}
