package com.example.khachhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.DTO.GioHang;
import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ThanhToanAdapter extends FirestoreRecyclerAdapter<GioHang, ThanhToanAdapter.ThanhToanViewHolder> {
    private final Context context;
    private TextView tv_tongtien;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    int totalCost = 0;
    private ThanhToanAdapter.OnItemClickListener listener;

    public ThanhToanAdapter(@NonNull FirestoreRecyclerOptions<GioHang> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ThanhToanAdapter.ThanhToanViewHolder holder, int position, @NonNull GioHang model) {
        int tongTien = model.getGiaSP() * model.getSoLuongSP();
        holder.tongTienSP.setText(String.valueOf(tongTien));
        totalCost += tongTien;

        holder.tenSP.setText(model.getTenSP());
        holder.soluongSP.setText(String.valueOf(model.getSoLuongSP()));
        holder.giaSP.setText("Giá Sản Phẩm :  " +String.valueOf(model.getGiaSP()));
        holder.tongTienSP.setText("Tổng :  " + String.valueOf(model.getTinhTongTien()));
        holder.tongTienSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(model);
                }
            }
        });

    }

    @NonNull
    @Override
    public ThanhToanAdapter.ThanhToanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thanhtoan_item, parent, false);
        return new ThanhToanAdapter.ThanhToanViewHolder(view);
    }

    public static class ThanhToanViewHolder extends RecyclerView.ViewHolder {
        TextView tenSP, giaSP, soluongSP,tongTienSP;
        // Khai báo các thành phần giao diện còn lại ở đây

        public ThanhToanViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSP = itemView.findViewById(R.id.tv_tensp);
            giaSP = itemView.findViewById(R.id.tv_giatien);
            soluongSP = itemView.findViewById(R.id.tv_sl);
            tongTienSP = itemView.findViewById(R.id.tv_tongtien2);

            // Khởi tạo các thành phần giao diện còn lại ở đây
        }
    }
    public interface OnItemClickListener {
        void onItemClick(GioHang gioHang);
    }
    public void setOnItemClickListener(ThanhToanAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}