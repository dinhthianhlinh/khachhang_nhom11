package com.example.khachhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.khachhang.fragment.fragment_QuanLySanPham;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment_QuanLySanPham myFragment = new fragment_QuanLySanPham();
        fragmentTransaction.replace(R.id.fragment_container, myFragment);
        fragmentTransaction.commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser == null){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this, trangchumenu.class));
                }
                finish();
            }
        },3000);
    }
}