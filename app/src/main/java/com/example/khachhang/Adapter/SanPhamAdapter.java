package com.example.khachhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.ChiTietSanPhamActivity;
import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SanPhamAdapter extends FirestoreRecyclerAdapter<SanPham, SanPhamAdapter.SanPhamHolder> {
    Context context;

    public SanPhamAdapter(@NonNull FirestoreRecyclerOptions<SanPham> options, Context context) {
        super(options);
        this.context = context;
    }
    //123
    @Override
    protected void onBindViewHolder(@NonNull SanPhamHolder holder, int position, @NonNull SanPham sanPham) {

        holder.tvTenSP.setText(sanPham.tenSP);
        holder.tvGiaSP.setText(String.valueOf(sanPham.giaSP));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển đến Activity chi tiết sản phẩm
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Chuyển dữ liệu của sản phẩm được chọn qua Intent
                intent.putExtra("SAN_PHAM", sanPham);

                // Mở Activity chi tiết sản phẩm
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public SanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false);
        return new SanPhamHolder(view);
    }

    public class SanPhamHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvGiaSP, tvMoTaSP, tvHangSP, tvTimeStamp;

        public SanPhamHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaSP = itemView.findViewById(R.id.tvGiaSP);
        }
    }
}
