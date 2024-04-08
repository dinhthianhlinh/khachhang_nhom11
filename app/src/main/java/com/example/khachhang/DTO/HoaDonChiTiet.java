package com.example.khachhang.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

public class HoaDonChiTiet implements Parcelable {
    public String tenKH;
    public String email;
    public int tongTienSP;
    public String phone;
    public String adress;
    public Timestamp timestamp;
    public String idDonHang;
    public String trangThai;

    public HoaDonChiTiet(String tenKH, String email, int tongTienSP, String phone, String adress, Timestamp timestamp, String idDonHang, String trangThai) {
        this.tenKH = tenKH;
        this.email = email;
        this.tongTienSP = tongTienSP;
        this.phone = phone;
        this.adress = adress;
        this.timestamp = timestamp;
        this.idDonHang = idDonHang;
        this.trangThai = trangThai;
    }

    protected HoaDonChiTiet(Parcel in) {
        tenKH = in.readString();
        email = in.readString();
        tongTienSP = in.readInt();
        phone = in.readString();
        adress = in.readString();
        timestamp = in.readParcelable(Timestamp.class.getClassLoader());
        idDonHang = in.readString();
        trangThai = in.readString();
    }

    public static final Creator<HoaDonChiTiet> CREATOR = new Creator<HoaDonChiTiet>() {
        @Override
        public HoaDonChiTiet createFromParcel(Parcel in) {
            return new HoaDonChiTiet(in);
        }

        @Override
        public HoaDonChiTiet[] newArray(int size) {
            return new HoaDonChiTiet[size];
        }
    };

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }



    //123
    public HoaDonChiTiet() {
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(String idDonHang) {
        this.idDonHang = idDonHang;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(tenKH);
        dest.writeString(email);
        dest.writeInt(tongTienSP);
        dest.writeString(phone);
        dest.writeString(adress);
        dest.writeParcelable(timestamp, flags);
        dest.writeString(idDonHang);
        dest.writeString(trangThai);
    }
}
