package com.example.khachhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.khachhang.Adapter.GioHangAdapter;
import com.example.khachhang.Adapter.ThanhToanAdapter;
import com.example.khachhang.DTO.GioHang;
import com.example.khachhang.DTO.HoaDon;
import com.example.khachhang.DTO.HoaDonChiTiet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class ThanhToanMuaNgayActivity extends AppCompatActivity {
    //123
    TextView tvTenSDtThanhToan , tvDiaChiThanhToan , tvTongThanhToanHoaDon,tvSDtThanhToan  ;
    LinearLayout btnMuaHang;
    RadioButton rdNhanHangThanhToan,  rdBankingThanhToan ;
    ThanhToanAdapter thanhToanAdapter;
    ImageView img_backToMain;
    FirebaseFirestore firestore;
    GioHangAdapter adapter;
    TextView  soluongSP, btnTruSoLuong_cart, btnCongSoLuong_cart,tongTienSP;

    TextView tvTenSP, tvGiaSP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_mua_ngay);
        anhxa();
        firestore = FirebaseFirestore.getInstance();
        DocumentReference userDocumentRef = Utility.currentUserDetails();

        userDocumentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ tài liệu người dùng
                    String userData = documentSnapshot.getString("ten"); // Thay "fieldName" bằng tên trường cần lấy dữ liệu
                    // Hiển thị dữ liệu trong TextView
                    tvTenSDtThanhToan.setText(userData);
                } else {
                    // Tài liệu không tồn tại
                    tvTenSDtThanhToan.setText(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý lỗi khi truy vấn dữ liệu
                tvTenSDtThanhToan.setText(null);
            }
        });
        userDocumentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ tài liệu người dùng
                    String userData = documentSnapshot.getString("adress"); // Thay "fieldName" bằng tên trường cần lấy dữ liệu
                    // Hiển thị dữ liệu trong TextView
                    tvDiaChiThanhToan.setText(userData);
                } else {
                    // Tài liệu không tồn tại
                    tvDiaChiThanhToan.setText(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý lỗi khi truy vấn dữ liệu
                tvDiaChiThanhToan.setText(null);
            }
        });
        userDocumentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ tài liệu người dùng
                    String userData = documentSnapshot.getString("phone"); // Thay "fieldName" bằng tên trường cần lấy dữ liệu
                    // Hiển thị dữ liệu trong TextView
                    tvSDtThanhToan.setText(userData);
                } else {
                    // Tài liệu không tồn tại
                    tvSDtThanhToan.setText(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý lỗi khi truy vấn dữ liệu
                tvSDtThanhToan.setText(null);
            }
        });
        Intent intent = getIntent();
        String tenSP = null;
        int giaSP = 0;
        if (intent != null) {
            tenSP = intent.getStringExtra("TEN_SP");
            giaSP = intent.getIntExtra("GIA_SP", 0);

            if (tenSP != null &&  giaSP != 0) {
                // Hiển thị thông tin sản phẩm trên giao diện
                tvTenSP.setText(tenSP);
                soluongSP.setText(String.valueOf(1));
                tvGiaSP.setText(String.valueOf(giaSP));
                tongTienSP.setText(String.valueOf(giaSP*1));
                tvTongThanhToanHoaDon.setText(String.valueOf(giaSP*1));
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

        GioHang model = null ;
        btnCongSoLuong_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(soluongSP.getText().toString());


                int currentPrice = Integer.parseInt(tvGiaSP.getText().toString());

                // Tăng số lượng lên 1
                currentQuantity++;

                // Cập nhật số lượng và tổng tiền mới lên TextViews
                soluongSP.setText(String.valueOf(currentQuantity));
                tongTienSP.setText(String.valueOf(currentPrice * currentQuantity));
                tvTongThanhToanHoaDon.setText(String.valueOf(currentPrice * currentQuantity));

                // Gọi phương thức để trả về giá trị của currentQuantity
            }
        });

        btnTruSoLuong_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(soluongSP.getText().toString());
                int currentPrice = Integer.parseInt(tvGiaSP.getText().toString());

                // Giảm số lượng đi 1, nhưng không nhỏ hơn 0
                currentQuantity = Math.max(0, currentQuantity - 1);

                // Cập nhật số lượng và tổng tiền mới lên TextViews
                soluongSP.setText(String.valueOf(currentQuantity));
                tongTienSP.setText(String.valueOf(currentPrice * currentQuantity));
                tvTongThanhToanHoaDon.setText(String.valueOf(currentPrice * currentQuantity));
            }
        });
        int soLuong = Integer.parseInt(soluongSP.getText().toString());
        int giaTien = Integer.parseInt(tongTienSP.getText().toString());
        String ten = tvTenSDtThanhToan.getText().toString();
        String phone = tvSDtThanhToan.getText().toString();
        String adress = tvDiaChiThanhToan.getText().toString();
        String idHoaDon = UUID.randomUUID().toString();

        HoaDon hoaDon = new HoaDon(ten, tenSP,giaTien, soLuong, giaTien * soLuong, phone, adress, Timestamp.now(), idHoaDon, "Chờ Xác Nhận");
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
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
                DocumentReference documentReference1;
                documentReference1 = Utility.HoaDon1().document();
                documentReference1.set(hoaDon).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        btnTruSoLuong_cart = findViewById(R.id.btnTruSoLuong_cart1);
        btnCongSoLuong_cart = findViewById(R.id.btnCongSoLuong_cart1);
        tvTenSP = findViewById(R.id.tvTenSP1);
        tvGiaSP = findViewById(R.id.tvGiaSP1);
        soluongSP = findViewById(R.id.tvSoLuong1);
        tongTienSP = findViewById(R.id.tvTongTien1);
        tvSDtThanhToan = findViewById(R.id.tvSDtThanhToan);
    }
    private void soLuong(int soLuong ){

    }
}