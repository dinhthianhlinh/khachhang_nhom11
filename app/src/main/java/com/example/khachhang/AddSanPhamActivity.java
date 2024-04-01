//package com.example.khachhang;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.example.khachhang.DTO.SanPham;
//import com.example.khachhang.fragment.fragment_QuanLySanPham;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.Timestamp;
//import com.google.firebase.firestore.DocumentReference;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AddSanPhamActivity extends AppCompatActivity {
//    EditText edtTenSP, edtGiaSP, edtMoTaSP;
//    Spinner spinnerHangSP;
//    ImageButton btnAddSP,btnBackSP;
//    TextView tvAddSP,tvDelete;
//    boolean isEditMode = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_san_pham);
//
//        edtTenSP = findViewById(R.id.edtTenSP);
//        edtGiaSP = findViewById(R.id.edtGiaSP);
//        edtMoTaSP = findViewById(R.id.edtMoTaSP);
//        spinnerHangSP = findViewById(R.id.spinnerHangSP); // Thay thế EditText bằng Spinner
//        btnAddSP = findViewById(R.id.btnimgAddSP);
//        tvAddSP = findViewById(R.id.tvAddSP);
//        tvDelete = findViewById(R.id.tvDelete);
//        btnBackSP = findViewById(R.id.btnBack);
//
//        String tenSP = getIntent().getStringExtra("TenSP");
//        int giaSP = getIntent().getIntExtra("giaSP", 0); // Sử dụng getIntExtra() để lấy số nguyên
//        String motaSP = getIntent().getStringExtra("moTaSP");
//        String hangSP = getIntent().getStringExtra("hangSP");
//        String docID = getIntent().getStringExtra("docID");
//
//        edtTenSP.setText(tenSP);
//        edtGiaSP.setText(String.valueOf(giaSP)); // Chuyển số nguyên thành chuỗi trước khi đặt giá trị
//        edtMoTaSP.setText(motaSP);
//
//
//
//        btnAddSP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DocumentReference documentReference;
//                if(isEditMode){
//                    documentReference = Utility.getCollectionReference().document(docID);
//
//                }else{
//                    documentReference = Utility.getCollectionReference().document();
//
//                }
//                documentReference.set(sanPham).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Utility.showToast(AddSanPhamActivity.this, "Thêm Sản Phẩm Thành Công");
//                            finish();
//                        } else {
//                            Utility.showToast(AddSanPhamActivity.this, "Thêm Sản Phẩm Thất Bại");
//                        }
//                    }
//                });
//            }
//        });
//    }
//}
