package com.example.khachhang.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

public class HoaDon implements Parcelable  {
    public String tenKH;
    public String tenSP;
    public int giaSP;
    public int soLuongSP;
    public int tongTienSP;
    public String phone;
    public String adress;
    public Timestamp timestamp;
    public String idHoaDon;
    public String trangThai;
    //123
    public HoaDon() {
    }

    public HoaDon(String tenKH, String tenSP, int giaSP, int soLuongSP, int tongTienSP, String phone, String adress, Timestamp timestamp, String idHoaDon, String trangThai) {
        this.tenKH = tenKH;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
        this.tongTienSP = tongTienSP;
        this.phone = phone;
        this.adress = adress;
        this.timestamp = timestamp;
        this.idHoaDon = idHoaDon;
        this.trangThai = trangThai;
    }

    protected HoaDon(Parcel in) {
        tenKH = in.readString();
        tenSP = in.readString();
        giaSP = in.readInt();
        soLuongSP = in.readInt();
        tongTienSP = in.readInt();
        phone = in.readString();
        adress = in.readString();
        timestamp = in.readParcelable(Timestamp.class.getClassLoader());
        idHoaDon = in.readString();
        trangThai = in.readString();
    }

    public static final Creator<HoaDon> CREATOR = new Creator<HoaDon>() {
        @Override
        public HoaDon createFromParcel(Parcel in) {
            return new HoaDon(in);
        }

        @Override
        public HoaDon[] newArray(int size) {
            return new HoaDon[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(tenKH);
        dest.writeString(tenSP);
        dest.writeInt(giaSP);
        dest.writeInt(soLuongSP);
        dest.writeInt(tongTienSP);
        dest.writeString(phone);
        dest.writeString(adress);
        dest.writeParcelable(timestamp, flags);
        dest.writeString(idHoaDon);
        dest.writeString(trangThai);
    }
}
