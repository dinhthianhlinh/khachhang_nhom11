package com.example.khachhang;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.Adapter.GioHangAdapter;
import com.example.khachhang.Adapter.ThanhToanAdapter;
import com.example.khachhang.DTO.GioHang;
import com.example.khachhang.DTO.HoaDon;
import com.example.khachhang.DTO.HoaDonChiTiet;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Thanhtaon extends AppCompatActivity implements GioHangAdapter.OnItemClickListener {
    //123
    TextView tvTenSDtThanhToan , tvDiaChiThanhToan , tvTongThanhToanHoaDon,tvSDtThanhToan,tvEmail ;
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
        String idHoaDon = UUID.randomUUID().toString();
        img_backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        User user = new User(ten,Utility.currentUserId());
//        tvTenSDtThanhToan.setText(user.getTen());
        DocumentReference userDocumentRef = Utility.currentUserDetails();
        CollectionReference userDocumentRef1 = Utility.ThemSanPhamVaoGiohHang();

        userDocumentRef1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                tvTongThanhToanHoaDon.setText(String.valueOf(totalTongTien));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý lỗi khi truy vấn dữ liệu

            }
        });
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
                    String userData = documentSnapshot.getString("email"); // Thay "fieldName" bằng tên trường cần lấy dữ liệu
                    // Hiển thị dữ liệu trong TextView
                    tvEmail.setText(userData);
                } else {
                    // Tài liệu không tồn tại
                    tvEmail.setText(null);
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
//        Query query1 = Utility.AllUser();
//        FirestoreRecyclerOptions<GioHang> options = new FirestoreRecyclerOptions.Builder<GioHang>()
//                .setQuery(query,GioHang.class).build();
//        rcSanPhamThanhToan.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new GioHangAdapter(options,this);
//        rcSanPhamThanhToan.setAdapter(adapter);
//        adapter.setOnItemClickListener(this);


        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tvTenSDtThanhToan.getText().toString();
                String phone = tvSDtThanhToan.getText().toString();
                String adress = tvDiaChiThanhToan.getText().toString();
                String email = tvEmail.getText().toString();
                int tontieng = Integer.parseInt(tvTongThanhToanHoaDon.getText().toString());
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(ten,email,tontieng,phone,adress,Timestamp.now(),idHoaDon,"Chờ Xác Nhận" );
                DocumentReference documentReference3;
                documentReference3 = Utility.HoaDonChiTiet1().document();
                documentReference3.set(hoaDonChiTiet).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();
                        } else {
                        }
                    }
                });

                List<GioHang> danhSachSanPham = new ArrayList<>();
                for (int i = 0; i < adapter.getItemCount(); i++) {
                    danhSachSanPham.add(adapter.getItem(i));
                }

                // Bây giờ bạn có thể xử lý danh sách sản phẩm theo nhu cầu của mình.
                // Ví dụ: thêm tất cả các sản phẩm vào hóa đơn và thực hiện thanh toán.
                for (GioHang gioHang : danhSachSanPham) {
                    gioHang.setIdHoaDon(idHoaDon);
                    onItemClick(gioHang);
                }
                List<HoaDon> danhSachSanPham1 = new ArrayList<>();
                danhSachSanPham.clear();
                if(ten.equals("") || phone.equals("") || adress.equals("")){
                    Utility.showToast(Thanhtaon.this,"Hãy cập nhật thông tin của bạn");
                }

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
        tvSDtThanhToan = findViewById(R.id.tvSDtThanhToan);
        tvEmail = findViewById(R.id.tvEmail);
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

        String ten = tvTenSDtThanhToan.getText().toString();
        String phone = tvSDtThanhToan.getText().toString();
        String adress = tvDiaChiThanhToan.getText().toString();

        HoaDon hoaDon = new HoaDon(ten, gioHang.tenSP, gioHang.giaSP, gioHang.soLuongSP, gioHang.giaSP * gioHang.soLuongSP, phone, adress, Timestamp.now(), gioHang.idHoaDon, "Chờ Xác Nhận");
        hoaDon.setTenSP(gioHang.tenSP);
        hoaDon.setGiaSP(gioHang.giaSP);
        hoaDon.setSoLuongSP(gioHang.soLuongSP);
        DocumentReference documentReference;
        DocumentReference documentReference1;

        documentReference1 = Utility.HoaDon1().document();

        documentReference1.set(hoaDon).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    finish();
                } else {
                }
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}