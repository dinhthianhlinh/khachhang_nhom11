package com.example.khachhang.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

public class SanPham implements Parcelable {
    public String tenSP;
    public int giaSP;
    public String hangSP;
    public String moTaSP;
    public Timestamp timestamp;

    public SanPham() {
    }
    //123
    public SanPham(String tenSP, int giaSP, String hangSP, String moTaSP, Timestamp timestamp) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hangSP = hangSP;
        this.moTaSP = moTaSP;
        this.timestamp = timestamp;
    }

    protected SanPham(Parcel in) {
        tenSP = in.readString();
        giaSP = in.readInt();
        hangSP = in.readString();
        moTaSP = in.readString();
        timestamp = in.readParcelable(Timestamp.class.getClassLoader());
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(tenSP);
        dest.writeInt(giaSP);
        dest.writeString(hangSP);
        dest.writeString(moTaSP);
        dest.writeParcelable(timestamp, flags);
    }
}

