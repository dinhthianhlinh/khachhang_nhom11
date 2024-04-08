package com.example.khachhang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.Adapter.HoaDonAdapter;
import com.example.khachhang.DTO.HoaDon;
import com.example.khachhang.DTO.HoaDonChiTiet;
import com.example.khachhang.DTO.User;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

public class fragment_QuanLyDonHang extends Fragment {

    RecyclerView recyclerView;
    HoaDonAdapter adapter;
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__quan_ly_don_hang,container,false);
        recyclerView = view.findViewById(R.id.rcyView);
        DocumentReference userDocumentRef = Utility.currentUserDetails();
        TextView tvEmail = view.findViewById(R.id.tvEmail1);
        userDocumentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ tài liệu người dùng
                    String userData = documentSnapshot.getString("email"); // Thay "fieldName" bằng tên trường cần lấy dữ liệu
                    // Hiển thị dữ liệu trong TextView
                    tvEmail.setText(userData);
                    Query query = Utility.HoaDonChiTiet1().whereEqualTo("email", userData);
                    FirestoreRecyclerOptions<HoaDonChiTiet> options = new FirestoreRecyclerOptions.Builder<HoaDonChiTiet>()
                            .setQuery(query, HoaDonChiTiet.class).build();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new HoaDonAdapter(options, getContext());
                    recyclerView.setAdapter(adapter);
                    adapter.startListening();

                } else {
                    // Tài liệu không tồn tại
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý lỗi khi truy vấn dữ liệu
            }
        });
        String email = tvEmail.getText().toString();

//123
        return view;
    }

//    public void onStart() {
//        super.onStart();
//        if (adapter != null) {
//            adapter.startListening();
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (adapter != null) {
//            adapter.stopListening();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (adapter != null) {
//            adapter.notifyDataSetChanged();
//        }
//    }
}