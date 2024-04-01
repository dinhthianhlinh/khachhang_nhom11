package com.example.khachhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.khachhang.DTO.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ThanhToanMuaNgayActivity extends AppCompatActivity {
    TextView tvTenSDtThanhToan , tvDiaChiThanhToan , tvTongThanhToanHoaDon ;
    LinearLayout btnMuaHang;
    RadioButton rdNhanHangThanhToan,  rdBankingThanhToan ;
    ThanhToanAdapter thanhToanAdapter;
    ImageView img_backToMain;
    FirebaseFirestore firestore;
    GioHangAdapter adapter;

     TextView tvTenSP, tvHangSP, tvMoTaSP, tvGiaSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_mua_ngay);
        anhxa();
        firestore = FirebaseFirestore.getInstance();

        tvTenSP = findViewById(R.id.tvTenSP);
        tvHangSP = findViewById(R.id.tvHangSP);
        tvMoTaSP = findViewById(R.id.tvMoTaSP);
        tvGiaSP = findViewById(R.id.tvGiaSP);

        Intent intent = getIntent();
        String tenSP = null;
        int giaSP = 0;
        String hangSP = null;
        String moTaSP = null;
        if (intent != null) {
            tenSP = intent.getStringExtra("TEN_SP");
            hangSP = intent.getStringExtra("HANG_SP");
            moTaSP = intent.getStringExtra("MOTA_SP");
            giaSP = intent.getIntExtra("GIA_SP", 0);

            if (tenSP != null && hangSP != null && moTaSP != null && giaSP != 0) {
                // Hiển thị thông tin sản phẩm trên giao diện
                tvTenSP.setText(tenSP);
                tvHangSP.setText(hangSP);
                tvMoTaSP.setText(moTaSP);
                tvGiaSP.setText(String.valueOf(giaSP));
            } else {
                // Xử lý khi dữ liệu không hợp lệ
                Toast.makeText(this, "Không thể tìm thấy thông tin sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
                finish(); // Kết thúc activity nếu không có dữ liệu hợp lệ
            }
        } else {
            // Xử lý khi Intent không tồn tại
            Toast.makeText(this, "Không thể nhận dữ liệu sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
            finish(); // Kết thúc activity nếu không có Intent
        }
        HoaDon hoaDon = new HoaDon(tenSP, giaSP, hangSP, moTaSP);
        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference;
                documentReference = Utility.HoaDon().document();
                documentReference.set(hoaDon).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Utility.showToast(ThanhToanMuaNgayActivity.this, "Mua Hàng Thành Công");
                            finish();
                        } else {
                            Utility.showToast(ThanhToanMuaNgayActivity.this, "Mua Hàng Thất Bại");
                        }
                    }
                });
            }
        });
    }


    void anhxa(){
        img_backToMain = findViewById(R.id.img_backToMain);
        tvTenSDtThanhToan = findViewById(R.id.tvTenSDtThanhToan);
        tvDiaChiThanhToan = findViewById(R.id.tvDiaChiThanhToan);
        tvTongThanhToanHoaDon = findViewById(R.id.tvTongThanhToanHoaDon);
        btnMuaHang = findViewById(R.id.btnMuaHangThanhToan);
        rdBankingThanhToan = findViewById(R.id.rdBankingThanhToan);
        rdNhanHangThanhToan = findViewById(R.id.rdNhanHangThanhToan);
    }
    void muahang(){

    }
}