package com.example.khachhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.ChiTietSanPhamActivity;
import com.example.khachhang.DTO.HoaDon;
import com.example.khachhang.DTO.HoaDonChiTiet;
import com.example.khachhang.HoaDonChiTietActivity;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class HoaDonAdapter extends FirestoreRecyclerAdapter<HoaDonChiTiet, HoaDonAdapter.HoaDonHolder> {
    Context context;
    public HoaDonAdapter(@NonNull FirestoreRecyclerOptions<HoaDonChiTiet> options,Context context) {
        super(options);
        this.context = context;
    }
    //123
    @Override
    protected void onBindViewHolder(@NonNull HoaDonHolder holder, int position, @NonNull HoaDonChiTiet model) {
        holder.tvTenSP.setText(model.tenKH);
        holder.tvGiaSP.setText(String.valueOf(model.phone));
        holder.tvMoTaSP.setText(model.adress);
        holder.tvTongtien.setText(String.valueOf(model.tongTienSP));
        holder.tvTenKH.setText(Utility.timestampToString(model.timestamp));
        holder.tvidHoaDon.setText(model.idDonHang);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HoaDonChiTietActivity.class);

                // Chuyển dữ liệu của sản phẩm được chọn qua Intent
                intent.putExtra("GioHang", model);

                // Mở Activity chi tiết sản phẩm
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public HoaDonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon, parent, false);
        return new HoaDonHolder(view);
    }

    public class HoaDonHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvGiaSP, tvMoTaSP, tvTongtien,tvTenKH,tvTimeStamp,tvidHoaDon;

        public HoaDonHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaSP = itemView.findViewById(R.id.tvGiaSP);
            tvMoTaSP = itemView.findViewById(R.id.tvMoTaSP);
            tvTongtien = itemView.findViewById(R.id.tvTongTien);
            tvTenKH = itemView.findViewById(R.id.tvTenKH);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            tvidHoaDon = itemView.findViewById(R.id.tvidHoaDon);
        }
    }
}
