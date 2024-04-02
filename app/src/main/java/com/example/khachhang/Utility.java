package com.example.khachhang;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class Utility{
    static void showToast(Context context,String messenger){
        Toast.makeText(context, messenger, Toast.LENGTH_SHORT).show();
    }
    public static CollectionReference getCollectionReference(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("San Pham");
    }
    public static String timestampToString(Timestamp timestamp){
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }
    public static CollectionReference ThemSanPhamVaoGiohHang(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("Gio Hang 1")
                .document(firebaseUser.getUid()).collection("Gio Hang Cua Toi");
    }
    public static CollectionReference HoaDon(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("Hoa Don")
                .document(firebaseUser.getUid()).collection("HoaDon Cua Toi");
    }
    public static CollectionReference HoaDon1(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("Hoa Don 1");
    }
    public static String CurrentUserID(){
        return FirebaseAuth.getInstance().getUid();
    }
    public static DocumentReference CurrentUserDetail(){
        return FirebaseFirestore.getInstance().collection("User").document(CurrentUserID());
    }
}