package com.example.khachhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.Adapter.GioHangAdapter;
import com.example.khachhang.DTO.GioHang;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
        tvTongTien = findViewById(R.id.tv_tongtien2);
        btnMuaHangGioHang = findViewById(R.id.btnMuaHangGioHang);
        CollectionReference userDocumentRef = Utility.ThemSanPhamVaoGiohHang();

        userDocumentRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int totalTongTien = 0;
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    if (documentSnapshot.exists()) {

                        int userData = documentSnapshot.getLong("tinhTongTien").intValue(); // Thay "fieldName" bằng tên trường cần lấy dữ liệu
                        totalTongTien += userData;
                    } else {
                        // Tài liệu không tồn tại

                    }
                }
                tvTongTien.setText(String.valueOf(totalTongTien));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý lỗi khi truy vấn dữ liệu

            }
        });
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