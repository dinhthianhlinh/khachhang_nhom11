package com.example.khachhang.DTO;

import java.io.Serializable;

public class GioHang implements Serializable {
//    String anhSP;
    String tenSP,giaSP;
    String soluongSP;

    public GioHang() {
    }

    public GioHang( String tenSP, String giaSP, String soluongSP) {
//        this.anhSP = anhSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soluongSP = soluongSP;
    }

//    public String getAnhSP() {
//        return anhSP;
//    }
//
//    public void setAnhSP(String anhSP) {
//        this.anhSP = anhSP;
//    }

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


}
