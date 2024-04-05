package com.example.khachhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.Adapter.GioHangAdapter;
import com.example.khachhang.DTO.GioHang;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class GioHangActivity extends AppCompatActivity {
    boolean isEditMode = false;
    private GioHangAdapter adapter;
    private FirebaseFirestore firestore;
    TextView tvTongTien;
    GioHang gioHang;
    //123
    int totalCost = 0;
    LinearLayout btnMuaHangGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        tvTongTien = findViewById(R.id.tv_tongtien1);
        btnMuaHangGioHang = findViewById(R.id.btnMuaHangGioHang);
        btnMuaHangGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this,Thanhtaon.class);
                // Chuyển dữ liệu của sản phẩm được chọn qua Intent

                // Mở Activity chi tiết sản phẩm
                startActivity(intent);
            }
        });
        totalCost = getIntent().getIntExtra("totalCost", 0);
        // Hiển thị tổng tiền trên TextView
        tvTongTien.setText(String.valueOf(totalCost));
        RecyclerView recyclerView = findViewById(R.id.rcGioHang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageView imageView = findViewById(R.id.img_backToMain);

// Trong một phương thức nào đó, bạn có thể lấy giá trị totalCost

        // Khởi tạo FirebaseFirestore
        firestore = FirebaseFirestore.getInstance();

        // Tạo Query để truy vấn dữ liệu từ Firestore
        Query query = Utility.ThemSanPhamVaoGiohHang().orderBy("tenSP",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<GioHang> options = new FirestoreRecyclerOptions.Builder<GioHang>()
                .setQuery(query,GioHang.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GioHangAdapter(options,this);
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
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
    void muaHang(){

    }


}
