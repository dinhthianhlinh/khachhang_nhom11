package com.example.khachhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ThanhToanAdapter extends FirestoreRecyclerAdapter<SanPham, ThanhToanAdapter.ThanhToanHolder> {
    Context context;
    public ThanhToanAdapter(@NonNull FirestoreRecyclerOptions<SanPham> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ThanhToanHolder holder, int position, @NonNull SanPham sanPham) {
        holder.tvTenSP.setText(sanPham.tenSP);
        holder.tvGiaSP.setText(String.valueOf(sanPham.giaSP));
        holder.tvMoTaSP.setText(sanPham.moTaSP);
        holder.tvHangSP.setText(sanPham.hangSP);
    }

    @NonNull
    @Override
    public ThanhToanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanhtoan, parent, false);
        return new ThanhToanAdapter.ThanhToanHolder(view);
    }

    public class ThanhToanHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvGiaSP, tvMoTaSP, tvHangSP, tvTimeStamp;

        public ThanhToanHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaSP = itemView.findViewById(R.id.tvGiaSP);
            tvMoTaSP = itemView.findViewById(R.id.tvMoTaSP);
            tvHangSP = itemView.findViewById(R.id.tvHangSP);
        }
    }
}
