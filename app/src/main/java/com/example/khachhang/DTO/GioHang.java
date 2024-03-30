package com.example.khachhang.DTO;

import java.io.Serializable;

public class GioHang implements Serializable {
    String anhSP,tenSP,giaSP;
    String soluongSP;
    String idSP,idNguoiDung;

    public GioHang() {
    }

    public GioHang(String anhSP, String tenSP, String giaSP, String soluongSP, String idSP, String idNguoiDung) {
        this.anhSP = anhSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soluongSP = soluongSP;
        this.idSP = idSP;
        this.idNguoiDung = idNguoiDung;
    }

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(String giaSP) {
        this.giaSP = giaSP;
    }

    public String getSoluongSP() {
        return soluongSP;
    }

    public void setSoluongSP(String soluongSP) {
        this.soluongSP = soluongSP;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }


}
