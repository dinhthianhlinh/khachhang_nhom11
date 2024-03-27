package com.example.khachhang;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.fragment.fragment_QuanLySanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

public class AddSanPhamActivity extends AppCompatActivity {
    EditText edtTenSP, edtGiaSP, edtMoTaSP;
    Spinner spinnerHangSP;
    ImageButton btnAddSP,btnBackSP;
    TextView tvAddSP,tvDelete;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_san_pham);

        edtTenSP = findViewById(R.id.edtTenSP);
        edtGiaSP = findViewById(R.id.edtGiaSP);
        edtMoTaSP = findViewById(R.id.edtMoTaSP);
        spinnerHangSP = findViewById(R.id.spinnerHangSP); // Thay thế EditText bằng Spinner
        btnAddSP = findViewById(R.id.btnimgAddSP);
        tvAddSP = findViewById(R.id.tvAddSP);
        tvDelete = findViewById(R.id.tvDelete);
        btnBackSP = findViewById(R.id.btnBack);

        String tenSP = getIntent().getStringExtra("TenSP");
        int giaSP = getIntent().getIntExtra("giaSP", 0); // Sử dụng getIntExtra() để lấy số nguyên
        String motaSP = getIntent().getStringExtra("moTaSP");
        String hangSP = getIntent().getStringExtra("hangSP");
        String docID = getIntent().getStringExtra("docID");

        edtTenSP.setText(tenSP);
        edtGiaSP.setText(String.valueOf(giaSP)); // Chuyển số nguyên thành chuỗi trước khi đặt giá trị
        edtMoTaSP.setText(motaSP);
        btnBackSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ra FragmentManager
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Bắt đầu một FragmentTransaction
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // Thực hiện việc thêm hoặc thay thế fragment_QuanLySanPham
                fragment_QuanLySanPham fragment = new fragment_QuanLySanPham();
                transaction.replace(R.id.quanLySanPham, fragment); // R.id.fragment_container là id của layout chứa fragment

                // Kết thúc giao dịch và áp dụng các thay đổi
                transaction.commit();
            }
        });

        if(docID!= null && !docID.isEmpty()){
            isEditMode = true;
        }
        if(isEditMode){
            tvAddSP.setText("Sửa Sản Phẩm");
            tvDelete.setVisibility(View.VISIBLE);
        }
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference;
                documentReference = Utility.getCollectionReference().document(docID);
                documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Utility.showToast(AddSanPhamActivity.this, "Xóa Sản Phẩm Thành Công");
                            finish();
                        } else {
                            Utility.showToast(AddSanPhamActivity.this, "Xóa Sản Phẩm Thất Bại");
                        }
                    }
                });
            }
        });

        // Tạo danh sách hãng sản phẩm
        List<String> hangSPList = new ArrayList<>();
        hangSPList.add("iPhone");
        hangSPList.add("SamSung");
        hangSPList.add("Oppo");
        hangSPList.add("Xiaomi");

// Tạo ArrayAdapter và gán cho Spinner
        ArrayAdapter<String> hangSPAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hangSPList);
        hangSPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHangSP.setAdapter(hangSPAdapter);

// Tìm vị trí của hangSP trong danh sách và đặt vị trí đó cho Spinner
        int position = hangSPList.indexOf(hangSP);
        if (position != -1) {
            spinnerHangSP.setSelection(position);
        }


        btnAddSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenSP = edtTenSP.getText().toString();
                int giaSP = Integer.valueOf(edtGiaSP.getText().toString());
                String hangSP = spinnerHangSP.getSelectedItem().toString(); // Lấy giá trị từ Spinner
                String MoTaSP = edtMoTaSP.getText().toString();

                if (TenSP.equals("") || MoTaSP.equals("")) {
                    if (TenSP.equals("")) {
                        edtTenSP.setError("Hãy Điền Tên Sản Phẩm");
                    } else {
                        edtTenSP.setError(null);
                    }
                    if (giaSP == 0) {
                        edtGiaSP.setError("Hãy Điền Giá Sản Phẩm");
                    } else {
                        edtGiaSP.setError(null);
                    }
                    if (MoTaSP.equals("")) {
                        edtMoTaSP.setError("Hãy Điền Mô Tả Sản Phẩm");
                    } else {
                        edtMoTaSP.setError(null);
                    }
                }

                SanPham sanPham = new SanPham(TenSP, hangSP, MoTaSP);
                sanPham.setTenSP(TenSP);
                sanPham.setGiaSP(giaSP);
                sanPham.setHangSP(hangSP);
                sanPham.setMoTaSP(MoTaSP);
                sanPham.setTimestamp(Timestamp.now());

                DocumentReference documentReference;
                if(isEditMode){
                    documentReference = Utility.getCollectionReference().document(docID);

                }else{
                    documentReference = Utility.getCollectionReference().document();

                }
                documentReference.set(sanPham).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Utility.showToast(AddSanPhamActivity.this, "Thêm Sản Phẩm Thành Công");
                            finish();
                        } else {
                            Utility.showToast(AddSanPhamActivity.this, "Thêm Sản Phẩm Thất Bại");
                        }
                    }
                });
            }
        });
    }
}
