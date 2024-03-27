package com.example.khachhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.AddSanPhamActivity;
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
    @Override
    protected void onBindViewHolder(@NonNull SanPhamHolder holder, int position, @NonNull SanPham sanPham) {
        holder.tvTenSP.setText(sanPham.tenSP);
        holder.tvGiaSP.setText(Integer.toString(sanPham.giaSP));
        holder.tvMoTaSP.setText(sanPham.moTaSP);
        holder.tvHangSP.setText(sanPham.hangSP);
        holder.tvTimeStamp.setText(Utility.timestampToString(sanPham.timestamp));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, AddSanPhamActivity.class);
//                intent.putExtra("TenSP",sanPham.tenSP);
//                intent.putExtra("giaSP",sanPham.giaSP);
//                intent.putExtra("moTaSP",sanPham.moTaSP);
//                intent.putExtra("hangSP",sanPham.hangSP);
//                String docID = getSnapshots().getSnapshot(position).getId();
//                intent.putExtra("docID",docID);
//                context.startActivity(intent);
//            }
//        });
    }

    @NonNull
    @Override
    public SanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new SanPhamHolder(view);
    }

    public class SanPhamHolder extends RecyclerView.ViewHolder{
        TextView tvTenSP,tvGiaSP,tvMoTaSP,tvHangSP,tvTimeStamp;
        public SanPhamHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaSP = itemView.findViewById(R.id.tvGiaSP);
            tvMoTaSP = itemView.findViewById(R.id.tvMoTaSP);
            tvHangSP = itemView.findViewById(R.id.tvHangSP);
            tvTimeStamp = itemView.findViewById(R.id.tvTimestamp);
        }
    }
}
