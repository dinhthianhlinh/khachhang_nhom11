package com.example.khachhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
    HoaDon hoaDon;
    RecyclerView rcvHoaDon;
    HoaDonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        TextView tvTimeStamp = findViewById(R.id.tvTimeStamp);
        rcvHoaDon = findViewById(R.id.rcvHoaDon);
        if (getIntent().hasExtra("GioHang")) {
            hoaDon = getIntent().getParcelableExtra("GioHang");

            if (hoaDon != null) {
                // Hiển thị dữ liệu sản phẩm

                tvTimeStamp.setText(Utility.timestampToString(hoaDon.timestamp));
                Query query = Utility.HoaDon()
                        .whereEqualTo("idHoaDon", hoaDon.idHoaDon);
                FirestoreRecyclerOptions<HoaDon> options = new FirestoreRecyclerOptions.Builder<HoaDon>()
                        .setQuery(query, HoaDon.class)
                        .build();


                rcvHoaDon.setLayoutManager(new LinearLayoutManager(this));
                adapter = new HoaDonAdapter(options, this);
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
//    @Override
//    public void onStart(){
//        super.onStart();
//        adapter.startListening();
//    }
//    @Override
//    public void onStop(){
//        super.onStop();
//        adapter.stopListening();
//    }
//    @Override
//    public void onResume(){
//        super.onResume();
//        adapter.notifyDataSetChanged();
//    }
}