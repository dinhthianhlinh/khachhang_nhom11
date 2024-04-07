package com.example.khachhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.DTO.HoaDon;
import com.example.khachhang.DTO.HoaDonChiTiet;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class HoaDonChiTietAdapter extends FirestoreRecyclerAdapter<HoaDon,HoaDonChiTietAdapter.HolderHoaDonChiTietAdapter> {
    Context context;
    public HoaDonChiTietAdapter(@NonNull FirestoreRecyclerOptions<HoaDon> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull HolderHoaDonChiTietAdapter holder, int position, @NonNull HoaDon model) {
        holder.tenSP.setText(model.tenSP);
        holder.giaSP.setText(String.valueOf(model.giaSP));
        holder.soluongSP.setText(String.valueOf(model.soLuongSP));
        holder.tongTienSP.setText(String.valueOf(model.tongTienSP));
    }

    @NonNull
    @Override
    public HolderHoaDonChiTietAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadonchitiet, parent, false);
        return new HoaDonChiTietAdapter.HolderHoaDonChiTietAdapter(view);
    }

    class HolderHoaDonChiTietAdapter extends RecyclerView.ViewHolder {

        TextView tenSP, giaSP, soluongSP,tongTienSP;
        ImageView eachCartItemDeleteBtn;
        // Khai báo các thành phần giao diện còn lại ở đây

        public HolderHoaDonChiTietAdapter(@NonNull View itemView) {
            super(itemView);
            tenSP = itemView.findViewById(R.id.tv_tensp);
            giaSP = itemView.findViewById(R.id.tv_giatien);
            soluongSP = itemView.findViewById(R.id.tv_sl);
            tongTienSP = itemView.findViewById(R.id.tv_tongtien2);

            // Khởi tạo các thành phần giao diện còn lại ở đây
        }
    }
}
