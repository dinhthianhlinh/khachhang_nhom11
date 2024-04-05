package com.example.khachhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.DTO.HoaDon;
import com.example.khachhang.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class HoaDonAdapter extends FirestoreRecyclerAdapter<HoaDon, HoaDonAdapter.HoaDonHolder> {
    Context context;
    public HoaDonAdapter(@NonNull FirestoreRecyclerOptions<HoaDon> options,Context context) {
        super(options);
        this.context = context;
    }
    //123
    @Override
    protected void onBindViewHolder(@NonNull HoaDonHolder holder, int position, @NonNull HoaDon model) {
        holder.tvTenKH.setText(model.tenKH);
        holder.tvTenSP.setText(model.tenSP);
        holder.tvGiaSP.setText(String.valueOf(model.giaSP));
        holder.tvMoTaSP.setText(String.valueOf(model.soLuongSP));
        holder.tvTongtien.setText(String.valueOf(model.tongTienSP));
    }

    @NonNull
    @Override
    public HoaDonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon, parent, false);
        return new HoaDonHolder(view);
    }

    public class HoaDonHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvGiaSP, tvMoTaSP, tvTongtien,tvTenKH;

        public HoaDonHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaSP = itemView.findViewById(R.id.tvGiaSP);
            tvMoTaSP = itemView.findViewById(R.id.tvMoTaSP);
            tvTongtien = itemView.findViewById(R.id.tvTongTien);
            tvTenKH = itemView.findViewById(R.id.tvTenKH);
        }
    }
}
