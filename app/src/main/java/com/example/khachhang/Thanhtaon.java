package com.example.khachhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.khachhang.Adapter.GioHangAdapter;
import com.example.khachhang.Adapter.ThanhToanAdapter;
import com.example.khachhang.DTO.GioHang;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Thanhtaon extends AppCompatActivity {
    TextView tvTenSDtThanhToan , tvDiaChiThanhToan , tvTongThanhToanHoaDon ;
    LinearLayout btnMuaHang;
    RecyclerView rcSanPhamThanhToan ;
    RadioButton rdNhanHangThanhToan,  rdBankingThanhToan ;
    ThanhToanAdapter thanhToanAdapter;
    ImageView img_backToMain;
    FirebaseFirestore firestore;
    GioHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtaon);
        anhxa();
        listSanPham();
        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference documentReference;
                GioHang gioHang = new GioHang();
                documentReference = Utility.HoaDon().document();
                documentReference.set(gioHang).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Utility.showToast(Thanhtaon.this, "Đã Thêm Sản Phẩm Vào Giỏ Hàng");
                            finish();
                        } else {
                            Utility.showToast(Thanhtaon.this, "Thêm Sản Phẩm Vào Giỏ Hàng Thất Bại");
                        }
                    }
                });
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
    void anhxa(){
        img_backToMain = findViewById(R.id.img_backToMain);
        tvTenSDtThanhToan = findViewById(R.id.tvTenSDtThanhToan);
        tvDiaChiThanhToan = findViewById(R.id.tvDiaChiThanhToan);
        tvTongThanhToanHoaDon = findViewById(R.id.tvTongThanhToanHoaDon);
        btnMuaHang = findViewById(R.id.btnMuaHangThanhToan);
        rcSanPhamThanhToan = findViewById(R.id.rcSanPhamThanhToan);
        rdBankingThanhToan = findViewById(R.id.rdBankingThanhToan);
        rdNhanHangThanhToan = findViewById(R.id.rdNhanHangThanhToan);
    }
    void listSanPham(){
        firestore = FirebaseFirestore.getInstance();

        // Tạo Query để truy vấn dữ liệu từ Firestore
        Query query = Utility.ThemSanPhamVaoGiohHang().orderBy("tenSP",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<GioHang> options = new FirestoreRecyclerOptions.Builder<GioHang>()
                .setQuery(query,GioHang.class).build();
        rcSanPhamThanhToan.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GioHangAdapter(options,this);
        rcSanPhamThanhToan.setAdapter(adapter);

        // Khởi tạo adapter với FirestoreRecyclerOptions
        adapter = new GioHangAdapter(options, this);

        // Đặt adapter cho RecyclerView
        rcSanPhamThanhToan.setAdapter(adapter);
    }
}