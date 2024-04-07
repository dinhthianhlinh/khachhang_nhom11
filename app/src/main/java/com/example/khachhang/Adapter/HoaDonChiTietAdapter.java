package com.example.khachhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.DTO.HoaDonChiTiet;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class HoaDonChiTietAdapter extends FirestoreRecyclerAdapter<HoaDonChiTiet,HoaDonChiTietAdapter.HolderHoaDonChiTietAdapter> {
    Context context;
    public HoaDonChiTietAdapter(@NonNull FirestoreRecyclerOptions<HoaDonChiTiet> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull HolderHoaDonChiTietAdapter holder, int position, @NonNull HoaDonChiTiet model) {
        holder.tvTenKH.setText(model.tenKH);
        holder.tvTenSP.setText(model.tenSP);
        holder.tvGiaSP.setText(String.valueOf(model.giaSP));
        holder.tvMoTaSP.setText(String.valueOf(model.soLuongSP));
        holder.tvTongtien.setText(String.valueOf(model.tongTienSP));
        holder.tvTimeStamp.setText(Utility.timestampToString(model.timestamp));
    }

    @NonNull
    @Override
    public HolderHoaDonChiTietAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoa_don_chi_tiet, parent, false);
        return new HoaDonChiTietAdapter.HolderHoaDonChiTietAdapter(view);
    }

    class HolderHoaDonChiTietAdapter extends RecyclerView.ViewHolder {

        TextView tvTenSP, tvGiaSP, tvMoTaSP, tvTongtien,tvTenKH,tvTimeStamp;



        public HolderHoaDonChiTietAdapter(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaSP = itemView.findViewById(R.id.tvGiaSP);
            tvMoTaSP = itemView.findViewById(R.id.tvMoTaSP);
            tvTongtien = itemView.findViewById(R.id.tvTongTien);
            tvTenKH = itemView.findViewById(R.id.tvTenKH);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
        }
    }
}
