package com.example.khachhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khachhang.DTO.GioHang;
import com.example.khachhang.DTO.SanPham;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.khachhang.DTO.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    private Button btnMua;
    private Button btnDatHang;
    private int soLuongSanPhamTrongGioHang = 0;
    private TextView cartItemCount;
    private SharedPreferences sharedPreferences;
    private TextView cartIcon;
    private SanPham sanPham;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE);
        soLuongSanPhamTrongGioHang = sharedPreferences.getInt("cartItemCount", 0);

        if (getIntent().hasExtra("SAN_PHAM")) {
            sanPham = getIntent().getParcelableExtra("SAN_PHAM");

            if (sanPham != null) {
                // Hiển thị dữ liệu sản phẩm
                TextView textViewTenSP = findViewById(R.id.tvTenSP);
                textViewTenSP.setText(sanPham.tenSP);
                TextView textViewHang = findViewById(R.id.tvHangSP);
                textViewHang.setText(sanPham.hangSP);
                TextView textViewMota = findViewById(R.id.tvMoTaSP);
                textViewMota.setText(sanPham.moTaSP);
                TextView tvGiaSP = findViewById(R.id.tvGiaSP);
                tvGiaSP.setText(String.valueOf(sanPham.giaSP));
            } else {
                // Đối tượng SanPham null, xử lý lỗi ở đây
                Toast.makeText(this, "Không có thông tin sản phẩm", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Intent không chứa đối tượng SanPham, xử lý lỗi ở đây
            Toast.makeText(this, "Intent không có đối tượng sản phẩm", Toast.LENGTH_SHORT).show();
        }

        cartItemCount = findViewById(R.id.cartItemCount);
        cartIcon = findViewById(R.id.cartIcon);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenSangGioHangActivity();
            }
        });
        capNhatIconGioHang();

        btnMua = findViewById(R.id.btnMua);
        btnDatHang = findViewById(R.id.btnDatHang);
        imgBack = findViewById(R.id.img_backToMain);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLoggedIn()) {
                    buyProduct();
                } else {
                    showLoginRequiredMessage();
                }
            }

            private void buyProduct() {
                Intent paymentIntent = new Intent(ChiTietSanPhamActivity.this, ThanhToanMuaNgayActivity.class);
                paymentIntent.putExtra("TEN_SP", sanPham.tenSP);
                paymentIntent.putExtra("HANG_SP", sanPham.hangSP);
                paymentIntent.putExtra("MOTA_SP", sanPham.moTaSP);
                paymentIntent.putExtra("GIA_SP", sanPham.giaSP);
                startActivity(paymentIntent);
                sendPaymentRequest();
                showOrderConfirmation();
            }

            private void sendPaymentRequest() {
                // Logic gửi yêu cầu thanh toán
            }

            private void showOrderConfirmation() {
                Toast.makeText(ChiTietSanPhamActivity.this, "Đã đặt hàng thành công", Toast.LENGTH_SHORT).show();
            }

            private boolean isUserLoggedIn() {
                // Kiểm tra trạng thái đăng nhập của người dùng
                return true;
            }

            private void showLoginRequiredMessage() {
                Toast.makeText(ChiTietSanPhamActivity.this, "Vui lòng đăng nhập để mua hàng", Toast.LENGTH_SHORT).show();
            }
        });

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSanPhamVaoGioHang(sanPham);
                capNhatIconGioHang();

                Toast.makeText(v.getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void themSanPhamVaoGioHang(SanPham sanPham) {

        soLuongSanPhamTrongGioHang++;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("cartItemCount", soLuongSanPhamTrongGioHang);
        editor.apply();

        DocumentReference documentReference;
        GioHang gioHang = new GioHang();
        documentReference = Utility.ThemSanPhamVaoGiohHang().document();
        documentReference.set(gioHang).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Utility.showToast(ChiTietSanPhamActivity.this, "Đã Thêm Sản Phẩm Vào Giỏ Hàng");
                    finish();
                } else {
                    Utility.showToast(ChiTietSanPhamActivity.this, "Thêm Sản Phẩm Vào Giỏ Hàng Thất Bại");
                }
            }
        });
    }

    private void capNhatIconGioHang() {

        if (cartItemCount != null) {
            if (soLuongSanPhamTrongGioHang > 0) {
                cartItemCount.setText(String.valueOf(soLuongSanPhamTrongGioHang));
                cartItemCount.setVisibility(View.VISIBLE);

            } else {
                cartItemCount.setVisibility(View.GONE);
            }
        }
    }

    private void chuyenSangGioHangActivity() {
        Intent gioHangIntent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
        startActivity(gioHangIntent);
    }
}