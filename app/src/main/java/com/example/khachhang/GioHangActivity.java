package com.example.khachhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.Adapter.GioHangAdapter;
import com.example.khachhang.Adapter.SanPhamAdapter;
import com.example.khachhang.DTO.GioHang;
import com.example.khachhang.DTO.SanPham;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class GioHangActivity extends AppCompatActivity {
    private GioHangAdapter adapter;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        RecyclerView recyclerView = findViewById(R.id.rcGioHang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageView imageView = findViewById(R.id.img_backToMain);
        // Khởi tạo FirebaseFirestore
        firestore = FirebaseFirestore.getInstance();

        // Tạo Query để truy vấn dữ liệu từ Firestore
        Query query = firestore.collection("your_collection_name"); // Thay "your_collection_name" bằng tên collection chứa dữ liệu GioHang

        // Tạo FirestoreRecyclerOptions từ Query
        FirestoreRecyclerOptions<GioHang> options = new FirestoreRecyclerOptions.Builder<GioHang>()
                .setQuery(query, GioHang.class)
                .build();

        // Khởi tạo adapter với FirestoreRecyclerOptions
        adapter = new GioHangAdapter(options, this);

        // Đặt adapter cho RecyclerView
        recyclerView.setAdapter(adapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để mở màn hình mới
                onBackPressed();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
