package com.example.khachhang.DTO;

import java.io.Serializable;
import java.util.List;

public class GioHang implements Serializable {
    //    String anhSP;
    public String tenSP;
    public int giaSP;
    public int soLuongSP;
    public String idHoaDon;
    public int tinhTongTien;
    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public GioHang() {
    }
    //123
    public GioHang(String tenSP, int giaSP, int soLuongSP,int tinhTongTien) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
        this.tinhTongTien = tinhTongTien;
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


    public int getTinhTongTien() {
        return giaSP * soLuongSP;
    }

    public void setTinhTongTien(int tinhTongTien) {
        this.tinhTongTien = tinhTongTien;
    }

    public static int tinhTongTien(List<GioHang> gioHangs) {
        int tongTien = 0;
        for (GioHang item : gioHangs) {
            tongTien += item.getTinhTongTien();
        }
        return tongTien;
    }
}