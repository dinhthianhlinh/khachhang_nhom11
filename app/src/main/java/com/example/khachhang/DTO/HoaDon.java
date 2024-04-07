package com.example.khachhang.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

public class HoaDon implements Parcelable {
    public String tenKH;
    public String tenSP;
    public int giaSP;
    public int soLuongSP;
    public int tongTienSP;
    public Timestamp timestamp;
    public String idHoaDon;

    protected HoaDon(Parcel in) {
        tenKH = in.readString();
        tenSP = in.readString();
        giaSP = in.readInt();
        soLuongSP = in.readInt();
        tongTienSP = in.readInt();
        timestamp = in.readParcelable(Timestamp.class.getClassLoader());
        idHoaDon = in.readString();
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

    public String getTenKH() {
        return tenKH;
    }
    //123
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public HoaDon(String tenKH, String tenSP, int giaSP, int soLuongSP, int tongTienSP, Timestamp timestamp, String idHoaDon) {
        this.tenKH = tenKH;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
        this.tongTienSP = tongTienSP;
        this.timestamp = timestamp;
        this.idHoaDon = idHoaDon;
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
        dest.writeParcelable(timestamp, flags);
        dest.writeString(idHoaDon);
    }
}
