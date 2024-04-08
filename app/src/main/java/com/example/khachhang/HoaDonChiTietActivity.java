package com.example.khachhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khachhang.Adapter.HoaDonAdapter;
import com.example.khachhang.Adapter.HoaDonChiTietAdapter;
import com.example.khachhang.Adapter.SanPhamAdapter;
import com.example.khachhang.DTO.HoaDon;
import com.example.khachhang.DTO.HoaDonChiTiet;
import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Query;

public class HoaDonChiTietActivity extends AppCompatActivity {
    HoaDonChiTiet hoaDon;
    RecyclerView rcvHoaDon;
    HoaDonChiTietAdapter adapter;
    TextView idDonHang,tenKH,diachi,phone,tongtien;
    ImageView img_backTo_hdct;
    Button btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        TextView tvTimeStamp = findViewById(R.id.tvTimeStamp);
        rcvHoaDon = findViewById(R.id.rcvHoaDon);
        idDonHang = findViewById(R.id.tvidHoaDon);
        tenKH = findViewById(R.id.tenKh);
        diachi = findViewById(R.id.adress);
        phone = findViewById(R.id.phone);
        tongtien = findViewById(R.id.tvTongTienThanhToan);
        img_backTo_hdct = findViewById(R.id.img_backTo_hdct);
        btnHuy = findViewById(R.id.btnHuy);
        img_backTo_hdct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (getIntent().hasExtra("GioHang")) {
            hoaDon = getIntent().getParcelableExtra("GioHang");

            if (hoaDon != null) {
                // Hiển thị dữ liệu sản phẩm
                idDonHang.setText(hoaDon.idDonHang);
                tenKH.setText(hoaDon.tenKH);
                diachi.setText(hoaDon.adress);
                phone.setText(hoaDon.phone);
                tvTimeStamp.setText(Utility.timestampToString(hoaDon.timestamp));
                Query query = Utility.HoaDon1()
                        .whereEqualTo("idHoaDon", hoaDon.idDonHang);
                FirestoreRecyclerOptions<HoaDon> options = new FirestoreRecyclerOptions.Builder<HoaDon>()
                        .setQuery(query, HoaDon.class)
                        .build();


                rcvHoaDon.setLayoutManager(new LinearLayoutManager(this));
                adapter = new HoaDonChiTietAdapter(options, this);
                rcvHoaDon.setAdapter(adapter);
                adapter.startListening();
//                adapter.stopListening();
//                adapter.notifyDataSetChanged();

            } else {
                // Đối tượng SanPham null, xử lý lỗi ở đây
                Toast.makeText(this, "Không có thông tin sản phẩm", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Intent không chứa đối tượng SanPham, xử lý lỗi ở đây
            Toast.makeText(this, "Intent không có đối tượng sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
}