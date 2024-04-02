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
import android.widget.Toast;

import com.example.khachhang.Adapter.GioHangAdapter;
import com.example.khachhang.Adapter.ThanhToanAdapter;
import com.example.khachhang.DTO.GioHang;
import com.example.khachhang.DTO.HoaDon;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class Thanhtaon extends AppCompatActivity implements GioHangAdapter.OnItemClickListener {
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
                List<GioHang> danhSachSanPham = new ArrayList<>();
                for (int i = 0; i < adapter.getItemCount(); i++) {
                    danhSachSanPham.add(adapter.getItem(i));
                }

                // Bây giờ bạn có thể xử lý danh sách sản phẩm theo nhu cầu của mình.
                // Ví dụ: thêm tất cả các sản phẩm vào hóa đơn và thực hiện thanh toán.
                for (GioHang gioHang : danhSachSanPham) {
                    onItemClick(gioHang);
                }
                danhSachSanPham.clear();
//                GioHang gioHang;
//                if (getIntent().hasExtra("Gio Hang")) {
//                    gioHang = getIntent().getParcelableExtra("Gio Hang");
//
//                    if (gioHang != null) {
//                        // Hiển thị dữ liệu sản phẩm
//                        DocumentReference documentReference;
////                        HoaDon hoaDon = new HoaDon(gioHang.getTenSP(), gioHang.getGiaSP(), gioHang.getSoLuongSP());
//                        documentReference = Utility.ThemSanPhamVaoGiohHang().document();
//                        documentReference.set(gioHang).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Utility.showToast(Thanhtaon.this, "Đã Thêm Sản Phẩm Vào Giỏ Hàng");
//                                    finish();
//                                } else {
//                                    Utility.showToast(Thanhtaon.this, "Thêm Sản Phẩm Vào Giỏ Hàng Thất Bại");
//                                }
//                            }
//                        });
//                    } else {
//                        // Đối tượng SanPham null, xử lý lỗi ở đây
//                        Toast.makeText(Thanhtaon.this, "Không có thông tin sản phẩm", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    // Intent không chứa đối tượng SanPham, xử lý lỗi ở đây
//                    Toast.makeText(Thanhtaon.this, "Intent không có đối tượng sản phẩm", Toast.LENGTH_SHORT).show();
//                }
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
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(GioHang gioHang) {
        HoaDon hoaDon = new HoaDon(gioHang.tenSP , gioHang.giaSP , gioHang.soLuongSP);
        hoaDon.setTenSP(gioHang.tenSP);
        hoaDon.setGiaSP(gioHang.giaSP);
        hoaDon.setSoLuongSP(gioHang.soLuongSP);
        DocumentReference documentReference;
        documentReference = Utility.HoaDon1().document();
        documentReference.set(hoaDon).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Utility.showToast(Thanhtaon.this, "Mua Thành Công");
                    finish();
                } else {
                    Utility.showToast(Thanhtaon.this, "Mua Thất Bại");
                }
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}