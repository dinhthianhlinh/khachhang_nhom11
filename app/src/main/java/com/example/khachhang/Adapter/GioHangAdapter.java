package com.example.khachhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.DTO.GioHang;
import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class GioHangAdapter extends FirestoreRecyclerAdapter<GioHang, GioHangAdapter.GioHangViewHolder> {
    private final Context context;
    private TextView tv_tongtien;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public GioHangAdapter(@NonNull FirestoreRecyclerOptions<GioHang> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull GioHangViewHolder holder, int position, @NonNull GioHang model) {
        holder.tenSP.setText(model.getTenSP());
        holder.soluongSP.setText(model.getSoluongSP());
        holder.giaSP.setText(model.getGiaSP());

        holder.btnCongSoLuong_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thêm logic tăng số lượng sản phẩm ở đây
            }
        });

        holder.btnTruSoLuong_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thêm logic giảm số lượng sản phẩm ở đây
            }
        });

//        holder.imgDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Thêm logic xóa sản phẩm khỏi giỏ hàng ở đây
//            }
//        });
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gio_hang_item, parent, false);
        return new GioHangViewHolder(view);
    }

    public static class GioHangViewHolder extends RecyclerView.ViewHolder {
        TextView tenSP, giaSP, soluongSP, sizeSP, btnTruSoLuong_cart, btnCongSoLuong_cart;
        // Khai báo các thành phần giao diện còn lại ở đây

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSP = itemView.findViewById(R.id.tv_tensp);
            giaSP = itemView.findViewById(R.id.tv_giatien);
            soluongSP = itemView.findViewById(R.id.tv_sl);
            // Khởi tạo các thành phần giao diện còn lại ở đây
        }
    }
}
